(ns jepsen.hazelcast
  "Hazelcast test."
  (:use
    jepsen.util
    jepsen.set-app
    jepsen.load)
  (:import
    [edu.ucr.cs.jepsen]))

(defn noop 
  "Nooping"
  []
  (.writeToMap (new edu.ucr.cs.jepsen.HazelcastApp)))

(defn hazelcast-app
  "Description"
  [opts]
  (let [test-map "map"]
    (reify SetApp
      (setup [app]
        (teardown app))

      (add [app element]
        
          (noop) ok)

      (results [app]
        (noop))

      (teardown [app]
        (noop)))))
