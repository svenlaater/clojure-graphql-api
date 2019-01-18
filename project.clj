(defproject clojure-graphql-api "0.1.0-SNAPSHOT"
  :description "GraphQL API implemented in Clojure"
  :url "https://github.com/svenlaater/clojure-graphql-api.git"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [com.stuartsierra/component "0.3.2"]
                 ;;[com.walmartlabs/lacinia "0.30.0"]
                 [com.walmartlabs/lacinia-pedestal "0.5.0"]
                 [io.aviso/logging "0.2.0"]
                 ;; fix issue w/ jdk-11
                 ;; java.lang.IllegalArgumentException: Must hint overloaded method: toArray,
                 ;; compiling:(flatland/ordered/set.clj:19:1)
                 ;; e.g. https://github.com/owainlewis/yaml/issues/28
                 [org.flatland/ordered "1.5.7"]])
