'use strict';

var app = angular.module("BestWinesApp")





/* Wine Functions */
.factory("Wines", function ($window) {  
	
	var Wine = {};
	
	Wine.getWineTitle = function(wine){
		return new Array(wine.title);
	};
	
	Wine.getWineIcon = function(wine){
		if(wine.url_icon){
			return wine.url_icon; 
		}else{
			return home_url+"img/not_available_icon.jpg";
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
	
	
	return Wine;
	
})


/* Meal Functions */
.factory("Meals", function ($window) {  
	
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
		$window.location.href = home_url+"wines.jsp?name="+encodeURIComponent(meal.title);
	};
	
	return Meal;
	
});
