(ns clojure-graphql-api.schema
  "Contains custom resolvers and a function to provide the full schema."
  (:require
   [clojure.java.io :as io]
   [com.walmartlabs.lacinia.util :as util]
   [com.walmartlabs.lacinia.schema :as schema]
   [com.stuartsierra.component :as component]
   [clojure.edn :as edn]
   [clojure-graphql-api.db :as db])) 

(defn companies
  [db]
  (fn [_ _ _]
    (hash-map :items (db/list-companies db))))

(defn company
  [db]
  (fn [_ args _]
    (db/get-company db (:uuid args))))

(defn resolver-map
  [component]
  (let [db (:db component)]
    {:query/companies (companies db)
     :query/company-by-uuid (company db)}))

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
