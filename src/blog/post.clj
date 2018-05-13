(ns blog.post
  (:require [hiccup.page :refer [html5 include-css]]
            [blog.layout :refer [with-navbar]]
            [clj-time format core coerce]))


(defn- date [fmt inst]
  "Create a span containing a date."
  (clj-time.format/unparse
    (clj-time.format/formatter fmt)
    (clj-time.coerce/from-date inst)))


(defn- formal-date [inst]
  (date "d MMMM, yyyy" inst))

(defn render [{global-meta :meta posts :entries post :entry :as opts}]
  (with-navbar opts
    [:div.pt-3
     [:div.row
      [:div.col-2]
      [:div.col-8
       [:h1 (:title post)]
       [:div.blog-post (:content post)]]
      [:div.col-2
       [:span.pull-right (formal-date (:date-published post))]]]]))
