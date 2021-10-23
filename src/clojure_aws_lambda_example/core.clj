(ns clojure-aws-lambda-example.core
  (:require [clojure.data.json :as json]
            [clojure.java.io :as io]
            [clojure.spec.alpha :as spec])
  (:gen-class
   :implements [com.amazonaws.services.lambda.runtime.RequestStreamHandler]))

;; -------------------- Specs --------------------
;; The json for an inbound scheduled event should look like this
;; {
;;     "version": "0",
;;     "id": "53dc4d37-cffa-4f76-80c9-8b7d4a4d2eaa",
;;     "detail-type": "Scheduled Event",
;;     "source": "aws.events",
;;     "account": "123456789012",
;;     "time": "2015-10-08T16:53:06Z",
;;     "region": "us-east-1",
;;     "resources": [
;;         "arn:aws:events:us-east-1:123456789012:rule/my-scheduled-rule"
;;     ],
;;     "detail": {}
;; }
;;
;; See https://docs.aws.amazon.com/eventbridge/latest/userguide/eb-run-lambda-schedule.html
(spec/def ::version string?)
(spec/def ::id string?)
(spec/def ::detail-type string?)
(spec/def ::source string?)
(spec/def ::account string?)
(spec/def ::time string?)
(spec/def ::region string?)
(spec/def ::resources (spec/coll-of string?))
(spec/def ::detail map?)

(spec/def ::scheduled-event
  (spec/keys
   :req-un [::version ::id ::detail-type ::source ::account ::time ::region ::resources ::detail]))


;; -------------------- Request handling --------------------
(defn- stream->scheduled-event
  "Transforms an input stream into a scheduled event "
  [in]
  (json/read (io/reader in) :key-fn keyword))


(defn -handleRequest
  "Implementation for RequestStreamHandler that handles a Lambda Function request"
  [_ input-stream _output-stream _context]
  (println "-handleRequest called with event:" (stream->scheduled-event input-stream)))
