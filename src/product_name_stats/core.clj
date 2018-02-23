(ns product-name-stats.core
  (:require [clojure.string :as string]
            [product-fetcher.core :refer [fetch-products]]))

(defn- name-stats
  [names]
  (let [term-counts (map #(count (string/split % #"\s+")) names)]
    {:average-term-count (float (/ (reduce + term-counts) (count term-counts)))
     :max-term-count (apply max term-counts)}))

(defn get-name-stats
  [site-id]
  (println "site-id" site-id)
  (let [products (fetch-products site-id)
        names (map #(get % "name") products)
        stats (name-stats names)]
    {:site-id site-id
     :names names
     :stats stats}))

