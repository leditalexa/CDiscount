'use strict';

var home_url = document.getElementById("home_url").href;

var lang_dir = "locale/";
var lang = "fr-FR";

var app = angular.module("BestWinesApp", ["ngRoute","ngResource", "ngCookies", "ui-rangeSlider"])

 .config(function ($routeProvider, $locationProvider, $httpProvider) {
    	$locationProvider.html5Mode(true); 
    	
        $routeProvider.when('/',
        {
            templateUrl:    'html/home.html',
            controller:     'HomeCtrl'
        });
        $routeProvider.when('/wines.jsp',
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
})




.filter('wineOrder', function() {
	   return function(arr, min, max) {
		   var retour = new Array();
		   for(var i=0;i<arr.length;++i){
			   if(arr[i].price >= min && arr[i].price <= max){ retour.push(arr[i]); }
		   }
	   return retour;
	  };
})
;