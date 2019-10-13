(ns {{name}}.runcore
  (:require [quil.core :as q])
  (:require [{{name}}.core :as core])
  (:gen-class))

(defn -main [& args]
  (q/sketch
            :title "FIXME"
            :setup core/setup
            :draw core/draw
            :size [10800 10800]
            :features [:exit-on-close]))
