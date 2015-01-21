(ns functional-kats.luhn)

(defn- digit? [c] (Character/isDigit c))
(defn- value [c] (Character/getNumericValue c))
(defn- rule3 [digit]
  (reduce + (map value (str (* 2 digit)))))

(def digit-lookup
  "Compute a lookup-table for the possible
  results of rule 3.
  This is the vector [0 2 4 6 8 1 3 5 7 9]"
  (vec (map rule3 (range 10))))

(defn valid
  "The Luhn test is used by some credit card companies to distinguish valid
  credit card numbers from what could be a random selection of digits.
  Those companies using credit card numbers that can be validated by the Luhn
  test have numbers that pass the following test:
  1. Reverse the order of the digits in the number.
  2. Take the first, third, ... and every other odd digit in the reversed digits
  and sum them to form the partial sum s1
  3. Taking the second, fourth ... and every other even digit in the reversed
  digits:
  - 1. Multiply each digit by two and sum the digits if the answer is greater
  than nine to form partial sums for the even digits
  - 2. Sum the partial sums of the even digits to form s2
  - 4. If s1 + s2 ends in zero then the original number is in the form of a valid
  credit card number as verified by the Luhn test."
  [numbers]
  {:pre [(string? numbers)
         (every? digit? numbers)]}
  (let [digits (reverse (map value numbers))
        odds (take-nth 2 digits)
        evens (take-nth 2 (rest digits))
        s1 (reduce + odds)
        s2 (reduce + (map digit-lookup evens))]
    (zero? (mod (+ s1 s2) 10))))


