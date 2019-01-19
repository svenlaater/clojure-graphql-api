(ns clojure-graphql-api.db
  (:require
    [clojure.edn :as edn]
    [clojure.java.io :as io]
    [com.stuartsierra.component :as component]))

(defrecord Database [data]

  component/Lifecycle

  (start [this]
    (assoc this :data (-> (io/resource "data.edn")
                          slurp
                          edn/read-string
                          atom)))

  (stop [this]
    (assoc this :data nil)))

(defn new-db
  []
  {:db (map->Database {})})

(defn list-companies
  [db]
  (->> db :data deref :companies))

(defn get-company
  [db uuid]
  (->> db
       :data
       deref
       :companies
       (filter #(= uuid (:uuid %)))
       first))
