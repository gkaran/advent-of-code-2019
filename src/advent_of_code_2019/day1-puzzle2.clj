(ns advent-of-code-2019.day1
  (:require [clojure.java.io :as io]))

(defn fuel [mass]
  (- (Math/floor (/ mass 3)) 2))

(defn fuelr 
  ([mass] (fuelr (fuel mass) 0))
  ([mass acc]
   (if (or (zero? mass) (neg? mass))
     acc
     (fuelr (fuel mass) (+ acc mass)))))

(defn solve [input]
  (reduce + (map fuelr input)))

(defn read-file-lines [file]
  (with-open [rdr (io/reader (io/resource (str "advent_of_code_2019/" file)))]
    (doall (map read-string (line-seq rdr)))))

(defn solve-for-file [] 
  (solve (tools/read-file-lines "day1/puzzle2-input.txt")))