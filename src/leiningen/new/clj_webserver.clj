(ns leiningen.new.clj-webserver
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "clj-webserver"))

(defn clj-webserver
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' clj-webserver project.")
    (->files data
             ["data/.placeholder" ""]
             [".gitignore" (render "gitignore")]
             ["project.clj" (render "project.clj" data)]
             ["dev/user.clj" (render "dev/user.clj" data)]
             ["config.clj" (render "config.clj" data)]
             ["src/clj/{{sanitized}}/routing.clj" (render "routing.clj" data)]
             ["src/clj/{{sanitized}}/storage.clj" (render "storage.clj" data)]
             ["src/clj/{{sanitized}}/views.clj" (render "views_base.clj" data)]
             ["src/clj/{{sanitized}}/views/index.clj" (render "views_index.clj" data)]
             ["src/clj/{{sanitized}}/system.clj" (render "system.clj" data)])))
