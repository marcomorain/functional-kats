(ns functional-kats.luhn-test
  (:require [clojure.test :refer :all]
            [functional-kats.luhn :as luhn]))

(deftest it-works
  (is (= [true false false true]
         (map luhn/valid  ["49927398716"
                           "49927398717"
                           "1234567812345678"
                           "1234567812345670"]))))
