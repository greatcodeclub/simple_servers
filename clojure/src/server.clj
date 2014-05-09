(ns server)

(use 'lamina.core 'aleph.tcp 'gloss.core)

(defn data-received [channel data]
  (println (str "Received: " data))
  (enqueue channel "Bye!\n")
  (close channel))

(defn handler [channel client-info]
  (println "New connection")
  (receive-all channel
    #(data-received channel %)))

(defn -main [& args]
  (start-tcp-server handler {:port 3000 :frame (string :utf-8)})
  (println "Listening on port 3000"))