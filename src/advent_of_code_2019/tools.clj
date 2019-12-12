(ns advent-of-code-2019.tools
  (:require [clojure.java.io :as io]))

(defn read-file-lines [file]
  (with-open [rdr (io/reader (io/resource (str "advent_of_code_2019/" file)))]
    (doall (line-seq rdr))))