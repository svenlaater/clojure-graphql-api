(ns clojure-graphql-api.schema
  "Contains custom resolvers and a function to provide the full schema."
  (:require
    [clojure.java.io :as io]
    [com.walmartlabs.lacinia.util :as util]
    [com.walmartlabs.lacinia.schema :as schema]
    [com.stuartsierra.component :as component]
    [clojure.edn :as edn]))

(defn resolve-companies
  [companies-vec context args value]
  (hash-map :items companies-vec))

(defn resolve-company-by-uuid
  [companies-map context args value]
  (let [{:keys [uuid]} args]
    (get companies-map uuid)))

(defn entity-map
  [data k]
  (reduce #(assoc %1 (:uuid %2) %2)
          {}
          (get data k)))

(defn resolver-map
  [component]
  (let [data (-> (io/resource "data.edn")
                 slurp
                 edn/read-string)
        companies-map (entity-map data :companies)]
    {:query/companies (partial resolve-companies (:companies data))
     :query/company-by-uuid (partial resolve-company-by-uuid companies-map)}))

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
  {:schema-provider (map->SchemaProvider {})})
