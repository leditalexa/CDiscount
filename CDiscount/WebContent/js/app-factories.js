'use strict';

var app = angular.module("BestWinesApp")


.factory('UserService', [function() {
  var sdo = {
	  datas : {
		  	identifiant : "",
		  	password : "",
		  	age : "",
		  	firstname : "",
		  	lastname : "",
		  	zipCode : "",
		  	lang : ""
	  		},
      isLogged : false
  };
  return sdo;
  
}])





/* Wine Functions */
.factory("Wines", ["$window","UrlProvider", function ($window,UrlProvider) {  
	
	var Wine = {};
	
	Wine.getWineTitle = function(wine){
		return new Array(wine.title);
	};
	
	Wine.getWineIcon = function(wine){
		if(wine.url_icon){
			return wine.url_icon; 
		}else{
			return UrlProvider.base+"/TITI/img/not_available_icon.jpg";
		}
		
	};
		
	Wine.getWinePrice = function(wine){
		return new Array(wine.price);
	};
	
	Wine.getWineRating = function(wine){
		return new Array(wine.rating);
	};
	
	Wine.getWineDescription = function(wine){
		return new Array(wine.description);
	};	
	

	Wine.goToWines = function(wine){
		$window.open(wine.url);
	};
		
	return Wine;
	
}])


/* Meal Functions */
.factory("Meals",  ["$window", "UrlProvider", function ($window, UrlProvider) {  
	
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
			return UrlProvider.base+"img/not_available_icon.jpg";
		}
		
	};
	
	Meal.getMealRating = function(meal){
		return new Array(meal.rating);
	};
	
	
	Meal.goToRecipe = function(meal){
		$window.open("http://www.marmiton.org/recettes/recette_"+(meal.title.replace(" ","-"))+"_"+meal.id+".aspx");
	};
	

	return Meal;
	
}]);
