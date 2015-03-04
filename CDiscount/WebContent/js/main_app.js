'use strict';

var home_url = document.getElementById("home_url").href;

var lang_dir = "locale/";
var lang = "fr-FR";

var app = angular.module("BestWinesApp", ["ngRoute","ngResource","ui-rangeSlider"])

 .config(function ($routeProvider, $locationProvider, $httpProvider) {
    	$locationProvider.html5Mode(true); 
    	
        $routeProvider.when('/',
        {
            templateUrl:    'html/home.html',
            controller:     'HomeCtrl'
        });
        $routeProvider.when('/wines',
        {
          templateUrl:    'html/wines.html',
          controller:     'WinesCtrl'
        });
        $routeProvider.otherwise(
        {
          redirectTo:     '/',
          controller:     'HomeCtrl'
        });

  })

  
.filter('slice', function() {
   return function(arr, start, end) {
   return arr.slice(start, end);
  };
});


