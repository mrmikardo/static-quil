(ns leiningen.new.static-quil
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]
            [clojure.java.shell :refer [sh]]))

(def render (renderer "static-quil"))

(defn static-quil
  "A project template for static-quil artworks, with version control."
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' static-quil project.")
    (->files data
             ["src/{{sanitized}}/core.clj" (render "core.clj" data)]
             ["src/{{sanitized}}/runcore.clj" (render "runcore.clj" data)]
             ["project.clj" (render "project.clj" data)]
             [".gitignore" (render ".gitignore" data)]))
    (sh "git" "init"))
