(ns {{name}}.views
  (:require [hiccup.core]
            [hiccup.page :refer [html5 include-css include-js]]))

(defn base [{:keys [content scripts]}]
  (html5 [:html {:lang "en"}
          [:head
           [:meta {:charset "UTF-8"}]
           [:title "{{name}}"]
           [:link {:href "/public/css/app.css"
                   :rel "stylesheet"
                   :type "text/css"}]]
          [:body
           [:div#content content]]
          (include-js "/public/js/main.js")
          (apply include-js scripts)]))
