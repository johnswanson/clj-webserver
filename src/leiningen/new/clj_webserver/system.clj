(ns {{name}}.system
  (:gen-class)
  (:require [{{name}}.storage :as storage]
            [{{name}}.routing]
            [ring.adapter.jetty :refer [run-jetty]])
  (:import [{{name}}.storage MemoryStorage]))

(defn config [& args]
  (let [data (read-string (slurp "config.clj"))]
    (get-in data args)))

(defn system []
  (let [storage (atom nil)
        handler ({{name}}.routing/create-handler storage)]
    {:storage {:store storage}
     :server {:handler handler
              :server nil}}))

(defn start!
  "Performs side effects to initialize the system, aquire resources, and start
  it running. Returns an updated instance of the system."
  [system]
  (let [store (case (config :storage :type)
                :memory (new MemoryStorage (atom nil))
                nil)
        server (run-jetty
                 ({{name}}.routing/create-handler store)
                 (config :server))]
    (reset! (get-in system [:storage :store]) store)
    (-> system
      (assoc-in [:storage :shutdown]
                (storage/init! store (config :storage)))
      (assoc-in [:server :server] server))))

(defn stop!
  "Performs side effects to shut down the system and release its resources.
  Returns an updated instance of the system."
  [system]

  (.stop (get-in system [:server :server]))
  ((get-in system [:storage :shutdown]))
  (reset! (get-in system [:storage :store]) nil)
  (-> system
    (assoc-in [:server :server] nil)
    (assoc-in [:storage :shutdown] nil)))

(defn -main [] (start! (system)))

