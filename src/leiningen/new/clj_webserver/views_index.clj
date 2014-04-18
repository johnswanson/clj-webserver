(ns {{name}}.views.index
  (:require [{{name}}.views :as views]
            [hiccup.core]
            [hiccup.page :refer [html5 include-css include-js]]))

(def index-content
  [:h1 "Howdy hey!"])

(defn get-page [{:keys []}]
  (views/base {:content index-content}))
