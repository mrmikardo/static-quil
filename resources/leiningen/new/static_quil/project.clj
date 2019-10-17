(defproject {{name}} "0.1.0-SNAPSHOT"
  :description "My example sketch."
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [quil "3.0.0"]
                 [org.apache.commons/commons-math3 "3.3"]]
  :jvm-opts ["-Xms1100m" "-Xmx1100M" "-server"] ; increase heap for large images
  :java-source-paths ["src/java"])

; :aot [{{name}}.core {{name}}.runcore]) -> this just causes trouble!

; to generate large images, add ":main {{name}}.runcore" to the bottom of the
; project definition

; based on: https://tylerxhobbs.com/essays/2015/using-quil-for-artwork
