(ns com.zeddworks.utilities.core
  (:require [clojure.string :as string]
            [clojure.walk :as walk])
  (:gen-class))

(def not-nil?
  "Returns a function that checks if a parameter is nil."
  (complement nil?))

(defn get-current-date []
  "Returns the current date as a string formatted as MM/dd/yyyy."
    (-> (new java.text.SimpleDateFormat "MM/dd/yyyy")
        (.format (new java.util.Date))))

(defn flatten-coll-to-seq
  "Takes a coll and an optional fn map-fn and returns a seq of the
  return type of fn or identity."
  ([coll]
    (flatten-coll-to-seq coll identity))

  ([coll map-fn]
    (->> coll
         seq
         flatten
         (map map-fn))))

(defn contains-any?
  "Takes an fn f and applies it to each element in coll until one returns
  true, or the coll is exhausted, which would return false. An optional
  mapping-fn parameter can be used to create a seq of a certain type, ex:
  str creates a sequence of strings. See flatten-coll-to-seq for more details"
  ([f coll]
    (contains-any? f identity coll))

  ([f mapping-fn coll]
    (->> mapping-fn
         (flatten-coll-to-seq coll)
         (some f)
         not-nil?)))

(defn query-string-to-map
  "Takes a query string from a url and returns a map of keys and values."
   [query]
   (walk/keywordize-keys
     (apply hash-map
       (string/split query #"(&|=)"))))

(defn normalize-keys
  "Takes a map and returns a map with Clojure keywords converted
  to the more universal 'key:' syntax"
  [s]
  (let [string (str s)]
  (str (subs string 1) (subs string 0 1))))

(defn query-string-to-keyword
  "Takes a query string and turns it into a keyword"
  [s]
  (let [s-map (query-string-to-map s)]
    (string/join " "
                 (interleave
                   (map normalize-keys
                     (keys s-map))
                     (vals s-map)))))

(defn url-to-keyword
  "Takes a url and manipulates the query string, turning it into a single 
  keyword."
  [url]
    (if
      (< 1 (count (string/split url #"\?")))
      (query-string-to-keyword (last (string/split url #"\?")))
      "(Direct)"))

(def contains-any-empty-strings?
  "Takes a seq and tests if any strings are empty."
  (partial contains-any? string/blank? str))


(defn truncate
  "Truncates a string to the length specified by n."
  [s n]
    (apply str (take n s)))
