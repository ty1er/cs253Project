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

(defn hazelcast-syncMap-repl2-app
  "Description"
  [opts]
  (let 
    [
     host (get opts :host)
        hz-app (new edu.ucr.cs.jepsen.HazelcastApp host)
        name "syncMap2"]
    (reify SetApp
      
       (setup [app]
        (teardown app)
        (.initMap hz-app name))

      (add [app element]
        (.writeToMap hz-app name element element) ok)

      (results [app]
        (.readKeysFromMap hz-app name))

      (teardown [app]
        (.clearMap hz-app name)
        ))
    ))

(defn hazelcast-syncMap-repl3-app
  "Description"
  [opts]
  (let 
    [
     host (get opts :host)
        hz-app (new edu.ucr.cs.jepsen.HazelcastApp host)
        name "syncMap3"]
    (reify SetApp
      
       (setup [app]
        (teardown app)
        (.initMap hz-app name))

      (add [app element]
        (.writeToMap hz-app name element element) ok)

      (results [app]
        (.readKeysFromMap hz-app name))

      (teardown [app]
        (.clearMap hz-app name)
        ))
    ))

(defn hazelcast-asyncMap-repl2-app
  "Description"
  [opts]
  (let 
    [
     host (get opts :host)
        hz-app (new edu.ucr.cs.jepsen.HazelcastApp host)
        name "asyncMap2"]
    (reify SetApp
      
       (setup [app]
        (teardown app)
        (.initMap hz-app name))

      (add [app element]
        (.writeToMap hz-app name element element) ok)

      (results [app]
        (.readKeysFromMap hz-app name))

      (teardown [app]
        (.clearMap hz-app name)
        ))
    ))

(defn hazelcast-asyncMap-repl3-app
  "Description"
  [opts]
  (let 
    [
     host (get opts :host)
        hz-app (new edu.ucr.cs.jepsen.HazelcastApp host)
        name "asyncMap3"]
    (reify SetApp
      
       (setup [app]
        (teardown app)
        (.initMap hz-app name))

      (add [app element]
        (.writeToMap hz-app name element element) ok)

      (results [app]
        (.readKeysFromMap hz-app name))

      (teardown [app]
        (.clearMap hz-app name)
        ))
    ))

(defn hazelcast-mixedMap-repl2-app
  "Description"
  [opts]
  (let 
    [
     host (get opts :host)
        hz-app (new edu.ucr.cs.jepsen.HazelcastApp host)
        name "mixedMap2"]
    (reify SetApp
      
       (setup [app]
        (teardown app)
        (.initMap hz-app name))

      (add [app element]
        (.writeToMap hz-app name element element) ok)

      (results [app]
        (.readKeysFromMap hz-app name))

      (teardown [app]
        (.clearMap hz-app name)
        ))
    ))

(defn hazelcast-mixedMap-repl3-app
  "Description"
  [opts]
  (let 
    [
     host (get opts :host)
        hz-app (new edu.ucr.cs.jepsen.HazelcastApp host)
        name "mixedMap3"]
    (reify SetApp
      
       (setup [app]
        (teardown app)
        (.initMap hz-app name))

      (add [app element]
        (.writeToMap hz-app name element element) ok)

      (results [app]
        (.readKeysFromMap hz-app name))

      (teardown [app]
        (.clearMap hz-app name)
        ))
    ))

(defn hazelcast-mixedMap-repl33-app
  "Description"
  [opts]
  (let 
    [
     host (get opts :host)
        hz-app (new edu.ucr.cs.jepsen.HazelcastApp host)
        name "mixedMap33"]
    (reify SetApp
      
       (setup [app]
        (teardown app)
        (.initMap hz-app name))

      (add [app element]
        (.writeToMap hz-app name element element) ok)

      (results [app]
        (.readKeysFromMap hz-app name))

      (teardown [app]
        (.clearMap hz-app name)
        ))
    ))

(defn hazelcast-syncSet-repl2-app
  "Description"
  [opts]
  (let 
    [
     host (get opts :host)
        hz-app (new edu.ucr.cs.jepsen.HazelcastApp host)
        name "syncSet2"]
    (reify SetApp
      
       (setup [app]
        (teardown app)
        (.initSet hz-app name))

      (add [app element]
        (.writeToSet hz-app name element) ok)

      (results [app]
        (.readFromSet hz-app name))

      (teardown [app]
        (.clearSet hz-app name)
        ))
    ))

(defn hazelcast-syncSet-repl3-app
  "Description"
  [opts]
  (let 
    [
     host (get opts :host)
        hz-app (new edu.ucr.cs.jepsen.HazelcastApp host)
        name "syncSet3"]
    (reify SetApp
      
       (setup [app]
        (teardown app)
        (.initSet hz-app name))

      (add [app element]
        (.writeToSet hz-app name element) ok)

      (results [app]
        (.readFromSet hz-app name))

      (teardown [app]
        (.clearSet hz-app name)
        ))
    ))

(defn hazelcast-asyncSet-repl2-app
  "Description"
  [opts]
  (let 
    [
     host (get opts :host)
        hz-app (new edu.ucr.cs.jepsen.HazelcastApp host)
        name "asyncSet2"]
    (reify SetApp
      
       (setup [app]
        (teardown app)
        (.initSet hz-app name))

      (add [app element]
        (.writeToSet hz-app name element) ok)

      (results [app]
        (.readFromSet hz-app name))

      (teardown [app]
        (.clearSet hz-app name)
        ))
    ))

(defn hazelcast-asyncSet-repl3-app
  "Description"
  [opts]
  (let 
    [
     host (get opts :host)
        hz-app (new edu.ucr.cs.jepsen.HazelcastApp host)
        name "asyncSet3"]
    (reify SetApp
      
       (setup [app]
        (teardown app)
        (.initSet hz-app name))

      (add [app element]
        (.writeToSet hz-app name element) ok)

      (results [app]
        (.readFromSet hz-app name))

      (teardown [app]
        (.clearSet hz-app name)
        ))
    ))

(defn hazelcast-mixedSet-repl2-app
  "Description"
  [opts]
  (let 
    [
     host (get opts :host)
        hz-app (new edu.ucr.cs.jepsen.HazelcastApp host)
        name "mixedSet2"]
    (reify SetApp
      
       (setup [app]
        (teardown app)
        (.initSet hz-app name))

      (add [app element]
        (.writeToSet hz-app name element) ok)

      (results [app]
        (.readFromSet hz-app name))

      (teardown [app]
        (.clearSet hz-app name)
        ))
    ))

(defn hazelcast-mixedSet-repl3-app
  "Description"
  [opts]
  (let 
    [
     host (get opts :host)
        hz-app (new edu.ucr.cs.jepsen.HazelcastApp host)
        name "mixedSet3"]
    (reify SetApp
      
       (setup [app]
        (teardown app)
        (.initSet hz-app name))

      (add [app element]
        (.writeToSet hz-app name element) ok)

      (results [app]
        (.readFromSet hz-app name))

      (teardown [app]
        (.clearSet hz-app name)
        ))
    ))

(defn hazelcast-mixedSet-repl33-app
  "Description"
  [opts]
  (let 
    [
     host (get opts :host)
        hz-app (new edu.ucr.cs.jepsen.HazelcastApp host)
        name "mixedSet33"]
    (reify SetApp
      
       (setup [app]
        (teardown app)
        (.initSet hz-app name))

      (add [app element]
        (.writeToSet hz-app name element) ok)

      (results [app]
        (.readFromSet hz-app name))

      (teardown [app]
        (.clearSet hz-app name)
        ))
    ))

(defn hazelcast-syncList-repl2-app
  "Description"
  [opts]
  (let 
    [
     host (get opts :host)
        hz-app (new edu.ucr.cs.jepsen.HazelcastApp host)
        name "syncList2"]
    (reify SetApp
      
       (setup [app]
        (teardown app)
        (.initList hz-app name))

      (add [app element]
        (.writeToList hz-app name element) ok)

      (results [app]
        (.readFromList hz-app name))

      (teardown [app]
        (.clearList hz-app name)
        ))
    ))

(defn hazelcast-syncList-repl3-app
  "Description"
  [opts]
  (let 
    [
     host (get opts :host)
        hz-app (new edu.ucr.cs.jepsen.HazelcastApp host)
        name "syncList3"]
    (reify SetApp
      
       (setup [app]
        (teardown app)
        (.initList hz-app name))

      (add [app element]
        (.writeToList hz-app name element) ok)

      (results [app]
        (.readFromList hz-app name))

      (teardown [app]
        (.clearList hz-app name)
        ))
    ))

(defn hazelcast-asyncList-repl2-app
  "Description"
  [opts]
  (let 
    [
     host (get opts :host)
        hz-app (new edu.ucr.cs.jepsen.HazelcastApp host)
        name "asyncList2"]
    (reify SetApp
      
       (setup [app]
        (teardown app)
        (.initList hz-app name))

      (add [app element]
        (.writeToList hz-app name element) ok)

      (results [app]
        (.readFromList hz-app name))

      (teardown [app]
        (.clearList hz-app name)
        ))
    ))

(defn hazelcast-asyncList-repl3-app
  "Description"
  [opts]
  (let 
    [
     host (get opts :host)
        hz-app (new edu.ucr.cs.jepsen.HazelcastApp host)
        name "asyncList3"]
    (reify SetApp
      
       (setup [app]
        (teardown app)
        (.initList hz-app name))

      (add [app element]
        (.writeToList hz-app name element) ok)

      (results [app]
        (.readFromList hz-app name))

      (teardown [app]
        (.clearList hz-app name)
        ))
    ))

(defn hazelcast-mixedList-repl2-app
  "Description"
  [opts]
  (let 
    [
     host (get opts :host)
        hz-app (new edu.ucr.cs.jepsen.HazelcastApp host)
        name "mixedList2"]
    (reify SetApp
      
       (setup [app]
        (teardown app)
        (.initList hz-app name))

      (add [app element]
        (.writeToList hz-app name element) ok)

      (results [app]
        (.readFromList hz-app name))

      (teardown [app]
        (.clearList hz-app name)
        ))
    ))

(defn hazelcast-mixedList-repl3-app
  "Description"
  [opts]
  (let 
    [
     host (get opts :host)
        hz-app (new edu.ucr.cs.jepsen.HazelcastApp host)
        name "mixedList3"]
    (reify SetApp
      
       (setup [app]
        (teardown app)
        (.initList hz-app name))

      (add [app element]
        (.writeToList hz-app name element) ok)

      (results [app]
        (.readFromList hz-app name))

      (teardown [app]
        (.clearList hz-app name)
        ))
    ))

(defn hazelcast-mixedList-repl33-app
  "Description"
  [opts]
  (let 
    [
     host (get opts :host)
        hz-app (new edu.ucr.cs.jepsen.HazelcastApp host)
        name "mixedList33"]
    (reify SetApp
      
       (setup [app]
        (teardown app)
        (.initList hz-app name))

      (add [app element]
        (.writeToList hz-app name element) ok)

      (results [app]
        (.readFromList hz-app name))

      (teardown [app]
        (.clearList hz-app name)
        ))
    ))

(defn hazelcast-syncQueue-repl2-app
  "Description"
  [opts]
  (let 
    [
     host (get opts :host)
        hz-app (new edu.ucr.cs.jepsen.HazelcastApp host)
        name "syncQueue2"]
    (reify SetApp
      
       (setup [app]
        (teardown app)
        (.initQueue hz-app name))

      (add [app element]
        (.writeToQueue hz-app name element) ok)

      (results [app]
        (.readFromQueue hz-app name))

      (teardown [app]
        (.clearQueue hz-app name)
        ))
    ))

(defn hazelcast-syncQueue-repl3-app
  "Description"
  [opts]
  (let 
    [
     host (get opts :host)
        hz-app (new edu.ucr.cs.jepsen.HazelcastApp host)
        name "syncQueue3"]
    (reify SetApp
      
       (setup [app]
        (teardown app)
        (.initQueue hz-app name))

      (add [app element]
        (.writeToQueue hz-app name element) ok)

      (results [app]
        (.readFromQueue hz-app name))

      (teardown [app]
        (.clearQueue hz-app name)
        ))
    ))

(defn hazelcast-asyncQueue-repl2-app
  "Description"
  [opts]
  (let 
    [
     host (get opts :host)
        hz-app (new edu.ucr.cs.jepsen.HazelcastApp host)
        name "asyncQueue2"]
    (reify SetApp
      
       (setup [app]
        (teardown app)
        (.initQueue hz-app name))

      (add [app element]
        (.writeToQueue hz-app name element) ok)

      (results [app]
        (.readFromQueue hz-app name))

      (teardown [app]
        (.clearQueue hz-app name)
        ))
    ))

(defn hazelcast-asyncQueue-repl3-app
  "Description"
  [opts]
  (let 
    [
     host (get opts :host)
        hz-app (new edu.ucr.cs.jepsen.HazelcastApp host)
        name "asyncQueue3"]
    (reify SetApp
      
       (setup [app]
        (teardown app)
        (.initQueue hz-app name))

      (add [app element]
        (.writeToQueue hz-app name element) ok)

      (results [app]
        (.readFromQueue hz-app name))

      (teardown [app]
        (.clearQueue hz-app name)
        ))
    ))

(defn hazelcast-mixedQueue-repl2-app
  "Description"
  [opts]
  (let 
    [
     host (get opts :host)
        hz-app (new edu.ucr.cs.jepsen.HazelcastApp host)
        name "mixedQueue2"]
    (reify SetApp
      
       (setup [app]
        (teardown app)
        (.initQueue hz-app name))

      (add [app element]
        (.writeToQueue hz-app name element) ok)

      (results [app]
        (.readFromQueue hz-app name))

      (teardown [app]
        (.clearQueue hz-app name)
        ))
    ))

(defn hazelcast-mixedQueue-repl3-app
  "Description"
  [opts]
  (let 
    [
     host (get opts :host)
        hz-app (new edu.ucr.cs.jepsen.HazelcastApp host)
        name "mixedQueue3"]
    (reify SetApp
      
       (setup [app]
        (teardown app)
        (.initQueue hz-app name))

      (add [app element]
        (.writeToQueue hz-app name element) ok)

      (results [app]
        (.readFromQueue hz-app name))

      (teardown [app]
        (.clearQueue hz-app name)
        ))
    ))