'use strict';


var app = angular.module("BestWinesApp", ["ngRoute","ngResource", "ngCookies", "ui-rangeSlider"])
.run(['$anchorScroll', function($anchorScroll) {
  $anchorScroll.yOffset = 50;   // always scroll by 50 extra pixels
}])

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