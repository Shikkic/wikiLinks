(ns wikilinks.core
    (:require
      [net.cgrand.enlive-html :as html]
      [com.rpl.specter :as s])
    )

(defn fetch-url [url]
      (html/html-resource (java.net.URL. url)))

(defn get-links [url]
  (s/select [s/ALL :attrs :href] (html/select (fetch-url url) [[:a (html/attr? :href)]])))


(defn -main []
  (get-links "https://www.wikipedia.org/wiki/hot_dog"))

