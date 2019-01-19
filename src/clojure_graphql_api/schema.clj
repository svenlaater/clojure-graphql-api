(ns clojure-graphql-api.schema
  "Contains custom resolvers and a function to provide the full schema."
  (:require
   [clojure.java.io :as io]
   [com.walmartlabs.lacinia
    [util :as util]
    [schema :as schema]
    [resolve :as resolve]]
   [com.stuartsierra.component :as component]
   [clojure.edn :as edn]
   [clojure-graphql-api.db :as db])) 

(defn list-companies
  [db]
  (fn [_ _ _]
    (hash-map :items (db/list-companies db))))

(defn get-company
  [db]
  (fn [_ args _]
    (db/get-company db (:uuid args))))

(defn insert-company
  [db]
  (fn [_ args _]
    (db/insert-company db args)))

(defn update-company
  [db]
  (fn [_ args _]
    (db/update-company db args)))

(defn delete-company
  [db]
  (fn [_ args _]
    (db/delete-company db (:uuid args))))

(defn resolver-map
  [component]
  (let [db (:db component)]
    {:query/companies (list-companies db)
     :query/company-by-uuid (get-company db)
     :mutation/insert-company (insert-company db)
     :mutation/update-company (update-company db)
     :mutation/delete-company (delete-company db)}))

(defn load-schema
  [component]
  (-> (io/resource "schema.edn")
      slurp
      edn/read-string
      (util/attach-resolvers (resolver-map component))
      schema/compile))

(defrecord SchemaProvider [schema]

  component/Lifecycle

  (start [this]
    (assoc this :schema (load-schema this)))

  (stop [this]
    (assoc this :schema nil)))

(defn new-schema-provider
  []
  {:schema-provider (-> {}
                        map->SchemaProvider
                        (component/using [:db]))})
