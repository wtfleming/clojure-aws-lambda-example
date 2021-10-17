(ns clojure-aws-lambda-example.core
  (:gen-class
   :implements [com.amazonaws.services.lambda.runtime.RequestStreamHandler]))

(defn -handleRequest
  [_ _input-stream _output-stream _context]
  (println "-handleRequest called!"))
