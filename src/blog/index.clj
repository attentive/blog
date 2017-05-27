(ns blog.index
  (:require [hiccup.page :refer [html5 include-css]]
            [blog.layout :refer [with-navbar card]]))

(defn render [{global-meta :meta posts :entries :as opts}]
  (with-navbar opts
    #_[:div.row
     (card opts [:a {:href "/about.html"} "About Page"])
     (card opts [:a {:href "/feed.rss"} "RSS"])
     (card opts [:a {:href "/atom.xml"} "Atom Feed"])]
    [:div.row
     (for [post posts]
       (card opts 
        [:h4 [:a {:href (:permalink post)}(:title post)]]))]))
