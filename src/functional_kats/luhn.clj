(ns functional-kats.luhn)

(defn- digit? [c] (Character/isDigit c))
(defn- value [c] (Character/getNumericValue c))

(defn rule3 [digit]
  (let [doubled (* 2 digit)]
   (if (< 9 doubled)
    (reduce + (map value (str doubled)))
    doubled)))

(def digit-lookup
  "This is the vector [0 2 4 6 8 1 3 5 7 9]"
  (vec (map rule3 (range 10))))

(defn valid
  [numbers]
  {:pre [(string? numbers)
         (every? digit? numbers)] }
  (let [digits (reverse (map value numbers))
        odds (take-nth 2 digits)
        evens (take-nth 2 (rest digits))
        partial-sums (map digit-lookup evens)
        s1 (reduce + odds)
        s2 (reduce + partial-sums)]
    (= 0 (mod (+ s1 s2) 10) )))


