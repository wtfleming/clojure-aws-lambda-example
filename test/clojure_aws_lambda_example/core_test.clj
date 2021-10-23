(ns clojure-aws-lambda-example.core-test
  (:require [clojure.test :refer [deftest is]])
  (:require [clojure-aws-lambda-example.core :as lambda-example]
            [clojure.java.io :as io]
            [clojure.spec.alpha :as spec]))

(deftest test-stream->scheduled-event
  (let [stream (io/input-stream "./test/eventbridge-scheduled-event.json")
        event (lambda-example/stream->scheduled-event stream)]
    (is (spec/valid? ::lambda-example/scheduled-event event))))
