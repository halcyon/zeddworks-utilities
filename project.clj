(defproject com.zeddworks/utilities "0.1.8"
  :description "ZeddWorks utilities library"
  :url "https://bitbucket.org/zeddworks/zeddworks-utilities"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :repositories ^:replace
    [["public" {:url "http://nexus-halcyonblue.rhcloud.com/nexus/content/groups/public" :username :env :password :env}]
     ["snapshots" {:url "http://nexus-halcyonblue.rhcloud.com/nexus/content/repositories/snapshots" :username :env :password :env}]
     ["releases" {:url "http://nexus-halcyonblue.rhcloud.com/nexus/content/repositories/releases" :username :env :password :env}]]
  :plugins [[codox "0.6.8"]]
  :dependencies  [[org.clojure/clojure "1.6.0"]])
