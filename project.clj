(defproject clojure-graphql-api "0.1.0-SNAPSHOT"
  :description "GraphQL API implemented in Clojure"
  :url "https://github.com/svenlaater/clojure-graphql-api.git"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main clojure-graphql-api.system
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [com.stuartsierra/component "0.4.0"]
                 [com.walmartlabs/lacinia-pedestal "0.11.0"]
                 [io.aviso/logging "0.3.1"]
                 ;; fix issue w/ jdk-11
                 ;; java.lang.IllegalArgumentException: Must hint overloaded method: toArray,
                 ;; compiling:(flatland/ordered/set.clj:19:1)
                 ;; e.g. https://github.com/owainlewis/yaml/issues/28
                 [org.flatland/ordered "1.5.7"]]
  :plugins [[jonase/eastwood "0.3.4"]
            [lein-kibit "0.1.6"]
            [lein-ancient "0.6.15"]
            [lein-cloverage "1.0.13"]])
