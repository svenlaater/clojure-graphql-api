(ns clojure-graphql-api.system
  (:gen-class)
  (:require
   [com.stuartsierra.component :as component]
   [clojure-graphql-api
    [schema :as schema]
    [server :as server]
    [db :as db]]))

(defn new-system
  []
  (merge (component/system-map)
         (server/new-server)
         (schema/new-schema-provider)
         (db/new-db)))


(defn -main
  [& args]
  (component/start (new-system)))
