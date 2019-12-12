(ns advent-of-code-2019.day3.puzzle1
  (:require [advent-of-code-2019.tools :as tools]))

(defn lastX [acc] (first (last acc)))
(defn lastY [acc] (last (last acc)))
(defn zip [a b] (map vector a b))
(defn right [acc steps] (zip (take steps (iterate inc (inc (lastX acc)))) (repeat steps (lastY acc))))
(defn left [acc steps] (zip (take steps (iterate dec (dec (lastX acc)))) (repeat steps (lastY acc))))
(defn up [acc steps] (zip (repeat steps (lastX acc)) (take steps (iterate inc (inc (lastY acc))))))
(defn down [acc steps] (zip (repeat steps (lastX acc)) (take steps (iterate dec (dec (lastY acc))))))


; (manh-dist [2 3] [5 6]) => 6
(defn manh-dist [u v]
  (reduce +
          (map (fn [[a b]] (Math/abs (- a b)))
               (zip u v))))

(defn parse-path-instruction [acc instruction]
  (concat acc (case (subs instruction 0 1)
                "R" (right acc (read-string (subs instruction 1)))
                "U" (up acc (read-string (subs instruction 1)))
                "D" (down acc (read-string (subs instruction 1)))
                "L" (left acc (read-string (subs instruction 1))))))

(defn create-path-points [path] (set (reduce parse-path-instruction '([0 0]) path)))

(defn solve [input]
  (apply min 
         (map #(manh-dist % [0 0]) 
              (disj (apply clojure.set/intersection (map create-path-points input)) [0 0]))))

(defn comma-separated-list [line]
  (clojure.string/split line #"[,]"))

(defn solve-for-file []
  (solve (map comma-separated-list (tools/read-file-lines "day3/puzzle1-input.txt"))))