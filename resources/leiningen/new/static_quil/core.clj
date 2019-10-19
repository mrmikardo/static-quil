(ns {{name}}.core
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [clojure.java.shell :refer [sh]]))

; utility functions for scaling height and width, and for saving images
; based on: https://tylerxhobbs.com/essays/2015/using-quil-for-artwork
(defn h   ([] (h 1.0))   ([value] (* (q/height) value)))
(defn w   ([] (w 1.0))   ([value] (* (q/width) value)))

; relies on imagemagick command line utility
(defn save-img [img-num]
  (let [filename (str "output/sketch-" img-num ".tif")
        thumb (str "output/sketch-" img-num "-1000.tif")]
    (q/save filename)
    (sh "convert" "-LZW" filename filename)
    (sh "convert" "-scale" "1000x1000" filename thumb)
    (println "Saved image #" img-num)))

; end utility functions

(defn setup []
  (q/no-loop)
  (q/color-mode :rgb))

(defn draw [state]
  (doseq [img-num (range 0 100)]
    "FIXME: Draw things here!"
    (save-img img-num)))

(q/defsketch {{name}}
  :title "FIXME"
  :size [500 500]
  :setup setup
  :draw draw
  :features [:keep-on-top]
  :middleware [m/fun-mode])
