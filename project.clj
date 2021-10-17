(defproject clojure-aws-lambda-example "0.1.0-SNAPSHOT"
  :dependencies [[com.amazonaws/aws-lambda-java-core "1.2.1"]
                 [com.amazonaws/aws-lambda-java-runtime-interface-client "2.0.0"]

                 [org.clojure/clojure "1.10.1"]]
  :repl-options {:init-ns clojure-aws-lambda-example.core}
  :profiles {:uberjar {:aot :all}})
