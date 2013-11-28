(ns jepsen.hazelcast
  "Hazelcast test."
  (:use
    jepsen.util
    jepsen.set-app
    jepsen.load)
  (:import
    [edu.ucr.cs.jepsen]
    [java.util]
    [System.out]))

;(defn noop 
;  "Nooping"
;  []
;  (.print hz-app))

(defn hazelcast-app
  "Description"
  [opts]
  (let [host (get opts :host)
        hosts (hash-map "n1" 1, "n2" 2, "n3" 1, "n4" 2, "n5" 2)
        key (get opts :key "test")
        hz-app (new edu.ucr.cs.jepsen.HazelcastApp host hosts)
;        sync-set (get opts :set "syncSet")]
        sync-map (get opts :map "asyncMap")]
    (reify SetApp
      (setup [app]
        (.initMap hz-app sync-map)
        (.clearMap hz-app))

      (add [app element]
        
          (.writeToMap hz-app element element) ok)
;(.println (System/out) element) ok)

      (results [app]
        (.readKeysFromMap hz-app))

      (teardown [app]
;        (.clearMap hz-app)
        ))))
