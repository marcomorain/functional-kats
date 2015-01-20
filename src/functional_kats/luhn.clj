(ns functional-kats.luhn)

(defn- digit? [c] (Character/isDigit c))
(defn- value [c] (Character/getNumericValue c))
(def twice (partial * 2))
(defn cap [digit]
  (if (> digit 9)
    (reduce + (map value (str digit)))
    digit))

(defn checksum
  [numbers]
  {:pre [(every? digit? numbers)] }
  (let [digits (reverse (map value numbers))
        odds (take-nth 2 digits)
        evens (take-nth 2 (rest digits))
        partial-sums (map cap (map twice evens))
        s1 (reduce + odds)
        s2 (reduce + partial-sums)]
  (= \0 (last (str (+ s1 s2))))))


(map checksum ["49927398716"
 "49927398717"
 "1234567812345678"
 "1234567812345670"])
