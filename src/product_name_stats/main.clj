(ns product-name-stats.main
  (:require [clojure.java.io :refer [writer]]
            [clojure.pprint :refer [pprint]]
            [clojure.tools.cli :refer [parse-opts]]
            [product-name-stats.core :refer [get-name-stats]]))

(def cli-opts
  [["-o" "--out FILE" "Output filename"
    :default "product-name-stats.out"]])

(defn -main
  [& args]
  (let [{:keys [options arguments]} (parse-opts args cli-opts)]
    (with-open [out (writer (:out options))]
      (doall (map (fn [site-id]
                    (println "processing" site-id)
                    (pprint (get-name-stats site-id) out)
                    (println "done processing" site-id))
                  arguments)))))

