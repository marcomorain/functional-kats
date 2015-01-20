(ns functional-kats.luhn-test
  (:require [clojure.test :refer :all]
            [functional-kats.luhn :as luhn]))

(deftest it-works
  (is (= 70 (luhn/checksum "49927398716"))))
