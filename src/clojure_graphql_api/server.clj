(ns clojure-graphql-api.server
  (:require [clojure.data.json :as json]
            [com.stuartsierra.component :as component]
            [com.walmartlabs.lacinia.pedestal :as lp]
            [io.pedestal.http :as http]
            [io.pedestal.http.cors :as cors]
            [io.pedestal.http.route :as route]))


(defn health-check-response
  [request]          
  {:status 200 :body (json/write-str {:status "pass"})})

(def health-check-route ["/healthCheck" :get health-check-response :route-name :healthCheck])   

(defrecord Server [schema-provider server]

  component/Lifecycle
  (start [this]
    (assoc this :server (-> schema-provider
                            :schema
                            (lp/service-map {:graphiql true})
                            (update :io.pedestal.http/routes #(conj % health-check-route))
                            (merge {::http/allowed-origins {:creds true
                                                            :allowed-origins (constantly true)}})
                            http/default-interceptors
                            http/create-server
                            http/start)))

  (stop [this]
    (http/stop server)
    (assoc this :server nil)))

(defn new-server
  []
  {:server (component/using (map->Server {})
                            [:schema-provider])})
