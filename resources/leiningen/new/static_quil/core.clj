(ns {{name}}.core
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [clojure.java.shell :refer [sh]]))

; utility functions for scaling height and width, and for saving images
; in sequence
;
; based on: https://tylerxhobbs.com/essays/2015/using-quil-for-artwork
;
(defn h   ([] (h 1.0))   ([value] (* (q/height) value)))
(defn w   ([] (w 1.0))   ([value] (* (q/width) value)))

(defn parse-int [s]
  (Integer. (re-find #"\d+" s)))

(def imgnum (atom nil))

(defn get-or-set-imgnum []
  (let [imgnum (slurp "imgnum")]
    (if (= imgnum "") 0 (parse-int imgnum))))

(defn load-imgnum-to-state []
  (reset! imgnum (get-or-set-imgnum)))

; relies on imagemagick command line utility ("convert")
(defn save-img []
  (let [img-num   (load-imgnum-to-state)
        filename (str "output/sketch-" img-num ".tif")
        thumb    (str "output/sketch-" img-num "-1000.tif")]
    (q/save filename)
    (sh "convert" "-LZW" filename filename)
    (sh "convert" "-scale" "1000x1000" filename thumb)
    (println "Saved image #" img-num)
    (swap! imgnum inc)
    (spit "imgnum" @imgnum)))

; end utility functions

(defn setup []
  (q/no-loop)
  (q/color-mode :rgb))

(defn draw [state]
  (doseq [img-num (range 0 100)]
    "FIXME: Draw things here!"
    (save-img)))

(q/defsketch {{name}}
  :title "FIXME"
  :size [500 500]
  :setup setup
  :draw draw
  :features [:keep-on-top]
  :middleware [m/fun-mode])
