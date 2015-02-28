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

 
  
  
 .service("carrouselService", function ($http) {  
    this.getCarrouselUtils = function($scope) {
    	
    	
    	$scope.carrousel_url = home_url+"rest/recipe/find/";
    	$scope.carrouselSearch = "";
    	$scope.carrousel_request = $scope.carrousel_url+$scope.carrouselSearch;
    	
    	
    	$scope.carrousel = [];
    	
    	$scope.carrouselStart = 0;
    	
    	$scope.carrouselEnd = 6;
    	
    	
    	$scope.getCarrouselFrom = function(url){
    		$http.get(url).success(function(data){
    			$scope.carrousel = data.data.items;
    		}).error(function (data){
    			
    		});
    	};
    		
    	$scope.loadMoreCarrousel = function(){
    		if($scope.carrouselEnd+1<$scope.carrousel.length){
    			$scope.carrouselStart += 1;
    			$scope.carrouselEnd += 1;
    		}
    	};
    	
    	$scope.loadLessCarrousel = function(){
    		if($scope.carrouselStart>0){
    			$scope.carrouselStart -= 1;
    			$scope.carrouselEnd -= 1;
    		}
    	};
    		
    	$scope.showCarrouselResult = function(){
    		return ($scope.carrousel.length>0);	
    	};  	

    };
}) 
  
  

.factory("Meals", function ($http, $window) {  
	/* Meal Functions */
	var Meal = {};
	
	Meal.getMealDifficulty = function(meal){
		return new Array(meal.difficulty);
	};
	
	Meal.getMealPrice = function(meal){
		return new Array(meal.cost);
	};
	
	Meal.getMealIcon = function(meal){
		if(meal.pictures && meal.pictures[2] && meal.pictures[2].url){
			return meal.pictures[2].url; 
		}else{
			return home_url+"img/not_available_icon.jpg";
		}
		
	};
	
	Meal.getMealRating = function(meal){
		return new Array(meal.rating);
	};
	
	
	Meal.goToRecipe = function(meal){
		$window.location.href = "http://www.marmiton.org/recettes/recette_"+(meal.title.replace(" ","-"))+"_"+meal.id+".aspx";
	};
	

	Meal.goToWines = function(meal){
		$window.location.href = home_url+"wines.jsp?name="+encodeURIComponent(meal.title)+"&id="+meal.id;
	};
	
	return Meal;
	
})


.filter('slice', function() {
   return function(arr, start, end) {
   return arr.slice(start, end);
  };
})





  
  
.controller("WinesCtrl", ["$scope",  "$window", "$location", "$http", "translationService", "Meals",
                          function($scope, $window, $location, $http, translationService, Meals) {	
	 
	$scope.Meals = Meals;
	
	$scope.nom_marmiton = $location.search().name;
	$scope.id_marmiton = $location.search().id;
	
	$scope.request = home_url+"rest/wine/find/"+$scope.nom_marmiton+"_"+$scope.id_marmiton;
	
	$scope.result = [];	
	$scope.error = "";
	
	$scope.sendRequest = function(request){
		$http.get(request).success(function(data){
			$scope.result = data;
		}).error(function (data){
			$scope.error = data;
		});
	};

	
	$scope.onclickSendRequest = function(){
		$scope.result = [];
		$scope.error = "";
		$scope.sendRequest($scope.request);
	};
	
}])
  
  
  
  
  
  
  
  
  
  
  
  
  
 
  
  








.controller("MealCarrouselCtrl1", ["$scope",  "$http", "translationService", "Meals", "carrouselService",
                                  function($scope, $http, translationService, Meals, carrouselService) {	
	translationService.getTranslation($scope, lang);

	$scope.Meals = Meals;
	
	carrouselService.getCarrouselUtils($scope);
	
	$scope.carrouselSearch = "thon";
	$scope.carrousel_request = $scope.carrousel_url+$scope.carrouselSearch;
	$scope.getCarrouselFrom($scope.carrousel_request);


}])


.controller("MealCarrouselCtrl2", ["$scope",  "$http", "$location", "translationService", "Meals", "carrouselService",
                                  function($scope, $http, $location, translationService, Meals, carrouselService) {	
	translationService.getTranslation($scope, lang);

	$scope.Meals = Meals;
	
	carrouselService.getCarrouselUtils($scope);
	
	$scope.nom_marmiton = $location.search().name;
	
	$scope.carrouselSearch = $scope.nom_marmiton;

	$scope.doCarrouselSearch = function(){
		var carrousel_request =  $scope.carrousel_url+$scope.carrouselSearch;
		$scope.getCarrouselFrom(carrousel_request);
	};
	


}])









.controller("MealsCtrl", ["$scope",  "$window", "$location", "$http", "translationService", "Meals",
                          function($scope, $window, $location, $http, translationService, Meals) {	
	
	translationService.getTranslation($scope, lang);
	$scope.Meals = Meals;

	/* My meal search result */
	
	$scope.mealSearch = "";
	
	$scope.mealSearchNumber = 4;
	
	
	$scope.mealSearchResult = [];
	
	
	$scope.doMealSearch = function(){
		$scope.mealSearchNumber = 4;
		$scope.getMealSearchResultFrom(home_url+"rest/recipe/find/"+encodeURIComponent($scope.mealSearch));
	};
	
	
	$scope.getMealSearchResultFrom = function(url){
		$http.get(url).success(function(data){
			$scope.mealSearchResult = data.data.items;
		}).error(function (data){
			
		});
	};
		
	$scope.mealSearchNumberIncrement = 4;
	
	$scope.loadMoreMealSearchResult = function(){
		$scope.mealSearchNumber += $scope.mealSearchNumberIncrement;
	};
	
	$scope.showMealSearchResult = function(){
		return ($scope.mealSearchResult.length!=0);	
	};
	
	
	$scope.showLoadMoreMealSearchResult = function(){
		return ($scope.mealSearchNumber < $scope.mealSearchResult.length);	
	};
	
	$scope.getMealSearchResultFrom(home_url+"rest/recipe/find/thon");
	
	
}])




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
}]);