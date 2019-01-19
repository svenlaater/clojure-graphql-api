(ns clojure-graphql-api.system
  (:require
    [com.stuartsierra.component :as component]
    [clojure-graphql-api.schema :as schema]
    [clojure-graphql-api.server :as server]
    [clojure-graphql-api.db :as db]))

(defn new-system
  []
  (merge (component/system-map)
         (server/new-server)
         (schema/new-schema-provider)
         (db/new-db)))
