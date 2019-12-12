(ns advent-of-code-2019.day2.puzzle1
   (:require [advent-of-code-2019.tools :as tools]))

(defn elof [v position offset]
  (nth v (nth v (+ position offset))))

(defn compute [v position op]
  (assoc v (nth v (+ position 3)) (op (elof v position 1) (elof v position 2))))

(defn process 
  ([v] (process v 0))
  ([v position] 
   (case (nth v position)
     99 v
     1 (process (compute v position +) (+ position 4))
     2 (process (compute v position *) (+ position 4))
     )))


(defn solve [input]
  (clojure.string/join ", " (process input)))

(defn prepare [v]
  (assoc (assoc v 1 12) 2 2))

(defn solve-for-file []
  (solve (prepare (vec (map read-string (clojure.string/split (first (tools/read-file-lines "day2/puzzle1-input.txt")) #"[,]"))))))