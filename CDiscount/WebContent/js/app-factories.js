'use strict';

var app = angular.module("BestWinesApp")





/* Wine Functions */
.factory("Wines", function ($window) {  
	
	var Wine = {};
	

	Wine.getWineDifficulty = function(wine){
		return new Array(wine.difficulty);
	};
	
	Wine.getWinePrice = function(wine){
		return new Array(wine.cost);
	};
	
	Wine.getWineIcon = function(wine){
		if(wine.mainImageUrl){
			return wine.mainImageUrl; 
		}else{
			return home_url+"img/not_available_icon.jpg";
		}
		
	};
	
	Wine.getWineRating = function(wine){
		return new Array(wine.rating);
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
