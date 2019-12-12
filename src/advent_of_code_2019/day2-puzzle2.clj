(ns advent-of-code-2019.day2.puzzle2
  (:require [advent-of-code-2019.tools :as tools])
  (:require [advent-of-code-2019.day2.puzzle1 :as d1p1]))

(defn prepare [v, verb, noun]
  (assoc (assoc v 1 verb) 2 noun))

(defn solve [input]
  (for [verb (range 100) noun (range 100)
        :when (= (nth (d1p1/process (prepare input verb noun)) 0) 19690720)]
    [verb noun (+ noun (* 100 verb))]))

(defn solve-for-file []
  (solve(vec (map read-string (clojure.string/split (first (tools/read-file-lines "day2/puzzle1-input.txt")) #"[,]")))))