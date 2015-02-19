'use strict';

var home_url = "http://localhost:8080/CDiscount/";

var lang_dir = "locale/";
var lang = "fr-FR";

var app = angular.module("BestWinesApp", ["ngResource"])

 .config(function($locationProvider) {
    $locationProvider.html5Mode(true); 
  })

.service("translationService", function ($http) {  
    this.getTranslation = function($scope, language) {
        var languageFilePath = lang_dir + language + '.json';
        $http.get(home_url+languageFilePath).success(function(data) {
            $scope.locale_string = data;     
        });
    };
})


.controller("LoginCtrl", ["$scope", "$window", "translationService", function($scope, $window, translationService) {
		
	translationService.getTranslation($scope, lang); 

	$scope.indexPaneShown = 1;
	$scope.setPaneShown = function(index){
		$scope.indexPaneShown = index;
	};
	$scope.isPaneShown = function(index){
		return index == $scope.indexPaneShown;
	};
	
	
	$scope.onLogin = function(){
		$window.location.href = home_url+"meals.jsp";
	};
}])


.controller("MealsCtrl", ["$scope", "$window", "$location", "$http", "translationService", function($scope, $window, $location, $http, translationService) {	
	
	
	$scope.exemples = [
		        		{title:"Lasagnes à la crème",desc:"Lasagnes classiques avec une pointe de creme",url:"http://www.marmiton.org/lasagnes_a_la_creme"},
		        		{title:"Lasagnes aux champignons",desc:"Lasagnes de ouf avec des champis",url:"http://www.marmiton.org/lasagnes_aux_champignons"},
		        		{title:"Lasagnes aux épinards",desc:"Une recette de lasagne pour vos enfants",url:"http://www.marmiton.org/lasagnes_aux_epinards"},	
		        		{title:"Lasagnes crémeuses",desc:"Lasagnes à la crème avec plus de creme",url:"http://www.marmiton.org/lasagnes_cremeuses"},
		        		{title:"Lasagnes champêtres",desc:"Lasagnes de champis magiques",url:"http://www.marmiton.org/lasagnes_champetres"},
		        		{title:"Lasagnes aux épines",desc:"Une recette de lasagne pour vos masos",url:"http://www.marmiton.org/lasagnes_aux_epines"},	
		        		{title:"Lasagnes Tagada",desc:"Parce que Haribo quoi",url:"http://www.marmiton.org/lasagnes_tagada"},
		        	];

	
	$scope.rows = 2;
	$scope.mealsByRow = 4;
	
	
	translationService.getTranslation($scope, lang); 
	
	$scope.mymeal = $location.search().search;
	

	$scope.meals = [];
	$scope.trials = $scope.exemples;
		
	
	
	$scope.search = function(){
		$scope.meals = $scope.trial;
	};
	
	
	$scope.goToWines = function(url){
		$window.location.href = home_url+"wines.jsp?meal="+url;
	};
	
}]);






