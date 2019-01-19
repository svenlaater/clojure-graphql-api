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
(defn uuid [] (.toString (java.util.UUID/randomUUID)))

(defn new-db [] {:db (map->Database {})})

(defn list-companies [db] (-> db :data deref vals))

(defn get-company [db uuid] (-> db :data deref (get uuid)))

(defn insert-company
  [db args]
  (let [uuid (uuid)
        args-with-uuid (assoc args :uuid uuid)]
    (-> (swap! (-> db :data) assoc uuid args-with-uuid)
        (get uuid))))

;; TODO make update smarter so that it would retain existing values
(defn update-company
  [db  {uuid :uuid :as args}]
  (prn uuid)
  (prn args)
  (-> (swap! (-> db :data) update-in [uuid] merge args)
      (get uuid)))

(defn delete-company
  [db uuid]
  (-> (swap-vals! (-> db :data) dissoc uuid)
      first
      (get uuid)))
