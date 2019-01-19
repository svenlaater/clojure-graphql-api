(ns clojure-graphql-api.db
  (:require
    [clojure.edn :as edn]
    [clojure.java.io :as io]
    [com.stuartsierra.component :as component]))

(defrecord Database [data]

  component/Lifecycle

  (start [this]
    (assoc this :data (->> (io/resource "companies.edn")
                           slurp
                           edn/read-string
                           ;; convert vector to hashmap by uuid for simpler access
                           (reduce #(assoc %1 (:uuid %2) %2) {})
                           atom)))

  (stop [this]
    (assoc this :data nil)))

;; TODO move to utils later
(defn uuid [] (str (java.util.UUID/randomUUID)))

(defn new-db [] {:db (map->Database {})})

(defn list-companies [db] (-> db :data deref vals))

(defn get-company [db uuid] (-> db :data deref (get uuid)))

(defn insert-company
  [db args]
  (let [uuid (uuid)
        args-with-uuid (assoc args :uuid uuid)]
    (get (swap! (:data db) assoc uuid args-with-uuid) uuid)))

(defn update-company
  [db  {uuid :uuid :as args}]
  (get (swap! (:data db) update-in [uuid] merge args) uuid))


(defn delete-company
  [db uuid]
  (-> (swap-vals! (:data db) dissoc uuid)
      first
      (get uuid)))
