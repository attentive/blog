(ns blog.layout
  (:require [hiccup.page :refer [html5 include-css include-js]]))

(defn card [{global-meta :meta :as opts} & contents]
  [:div.card
   (into [] `(:img {:style "height: 280px; width: 100%; display: block;",
                    :alt "100%x280",
                    :data-src "holder.js/100px280/thumb"}
                   ~@contents))])

(defn bootstrap-deps []
  [[:css "/css/tomlynch.css"]
   [:js "//code.jquery.com/jquery-3.1.1.slim.min.js"]
   [:js "//cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"]
   [:js "//maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"]])

(defn lato-fonts []
  [[:css "http://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"]
   [:css "http://fonts.googleapis.com/css?family=Inconsolata:400,700&subset=latin,latin-ext"]])


;#_[:body
; [:div.container
;  [:div.row
;   [:div.left-bar.col-md-2
;    [:nav.navbar.navbar-default.nav-stacked
;     [:div.navbar-header
;      "<!--<button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-collapse\">\n\t    <span class=\"sr-only\">Toggle navigation</span>\n\t    <span class=\"glyphicon glyphicon-list\"></span>\n\t\t\t</button>-->"
;      [:h2 [:a {:href "/"} "tomlynch.io"]]]
;     "<!--<div class=\"collapse navbar-collapse\">\n\t  <ul class=\"nav navbar-nav\">\n\t    <li><h3><a href=\"/about\">about</a></h3></li>\n\t    <li><h3><a href=\"/\">archive</a></h3></li>\n\t    <li><h3><a href=\"/\">categories</a></h3></li>\n\t    <li><h3><a href=\"/\">tags</a></h3></li>\n\t  </ul>\n\t\t</div>-->"]
;    [:div.hidden-xs.hidden-sm
;     [:script
;      {:src
;       "http://pinboard.in//widgets/v1/linkroll/?user=attentive&count=50&tag=reading",
;       :language "javascript"}]]]
;   [:div.silkscreen-post
;    [:div.col-md-1]
;    [:div.main.col-md-9
;     [:div.body.col-md-10
;      [:h1.silkscreen-title]
;      [:h6.date.silkscreen-published.hidden-md.hidden-lg]
;      [:div.silkscreen-body]]
;     [:div.right-bar.col-md-2.hidden-xs.hidden-sm
;      [:h6.date.silkscreen-published]
;      "      \n\t"]]]]]
; [:script
;  {:type "text/x-mathjax-config"}
;  "\nMathJax.Hub.Config({\n\t    tex2jax: {\n\t      inlineMath: [ ['$','$'], [\"\\\\(\",\"\\\\)\"] ],\n\t      processEscapes: true\n\t    }\n\t  });\n"]
; [:script
;  {:src
;   "//cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS_HTML-full",
;   :type "text/javascript"}]
; [:script {:src "/js/jquery-2.1.1.min.js"}]
; [:script {:src "/js/bootstrap.min.js"}]]


(defn head [{global-meta :meta posts :entries :as opts} & deps]
  [:head
   [:meta {:charset "utf-8"}]
   [:meta {:content "width=device-width, initial-scale=1, shrink-to-fit=no", :name "viewport"}]
   [:meta {:content (:description global-meta), :name "description"}]
   [:meta {:content (:author global-meta), :name "author"}]
   [:link {:type "application/atom+xml", :title "the attentive worker", :rel "alternate", :href "/atom.xml"}]
   [:link {:href "/favicon.ico", :rel "icon"}]
   [:link {:sizes "32x32", :href "/images/blackcircle-malevich-32x32.png", :type "image/png", :rel "icon"}] 
   [:link {:sizes "16x16", :href "/images/blackcircle-malevich-16x16.png", :type "image/png", :rel "icon"}]
   [:title (:site-title global-meta)]
   (for [dep deps]
     (case (first dep)
       :css (include-css (second dep))
       :js (include-js (second dep))))])

(defn navbar [{global-meta :meta posts :entries :as opts}]
  [:nav.navbar.navbar-toggleable-md.fixed-top.navbar-inverse.raked
   [:button.navbar-toggler.navbar-toggler-right
    {:aria-label "Toggle navigation",
     :aria-expanded "false",
     :aria-controls "the-navbar",
     :data-target "#the-navbar",
     :data-toggle "collapse",
     :type "button"}
    [:span.navbar-toggler-icon]]
   [:a.navbar-brand {:href "/"} (:site-title global-meta)]
   [:div#the-navbar.collapse.navbar-collapse
    [:ul.navbar-nav.mr-auto
     #_[:li.nav-item.active
      [:a.nav-link {:href "about.html"} "about" [:span.sr-only "(current)"]]]
     #_[:li.nav-item [:a.nav-link {:href "#"} "Link"]]
     #_[:li.nav-item [:a.nav-link.disabled {:href "#"} "Disabled"]]
     #_[:li.nav-item.dropdown
        [:a#dropdown01.nav-link.dropdown-toggle
         {:aria-expanded "false",
          :aria-haspopup "true",
          :data-toggle "dropdown",
          :href "http://example.com"}
         "Dropdown"]
        #_[:div.dropdown-menu
           {:aria-labelledby "dropdown01"}
           [:a.dropdown-item {:href "#"} "Action"]
           [:a.dropdown-item {:href "#"} "Another action"]
           [:a.dropdown-item {:href "#"} "Something else here"]]]]
    #_[:form.form-inline.my-2.my-lg-0
       [:input.form-control.mr-sm-2
        {:placeholder "Search", :type "text"}]
       [:button.btn.btn-outline-success.my-2.my-sm-0
        {:type "submit"}
        "Search"]]]])

(defn with-sidebar [{global-meta :meta posts :entries :as opts} & contents]
  [:div.container-fluid
   [:div.row
    [:nav.col-sm-3.hidden-xs-down.bg-faded.sidebar
     [:ul.nav.nav-pills.flex-column
      #_[:li.nav-item
         [:a.nav-link.active {:href "/"} "tomlynch.io"
          [:span.sr-only "(current)"]]]
      [:li.nav-item [:a.nav-link {:href "about.html"} "about"]]
      [:li.nav-item [:span.nav-link "recent reading"]]]
     [:div.hidden-xs.hidden-sm
          (include-js "http://pinboard.in//widgets/v1/linkroll/?user=attentive&count=50&tag=reading")]]
     [:div.col-sm-1.hidden-xs-down.bg-white]
    (into [] `(:main.col-sm-8.top-spacer.bg-white ~@contents))]])

(defn with-navbar [{global-meta :meta posts :entries :as opts} & contents]
  (html5 {:lang "en" :itemtype "http://schema.org/Blog"}
         (apply head (cons opts (concat (bootstrap-deps) (lato-fonts))))    
         [:body
          (navbar opts)
          (apply (partial with-sidebar opts) contents)]))



