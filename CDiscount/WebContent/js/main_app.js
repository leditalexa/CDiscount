'use strict';

var home_url = "http://localhost:8080/CDiscount/";

var lang_dir = "locale/";
var lang = "fr-FR";

var app = angular.module("BestWinesApp", ["ngResource"])

 .config(function($locationProvider) {
    $locationProvider.html5Mode(true); 
  })

    
.controller("WinesCtrl", ["$scope", "$location", "$http", function($scope, $location, $http) {
	 

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
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
.service("translationService", function ($http) {  
    this.getTranslation = function($scope, language) {
        var languageFilePath = lang_dir + language + '.json';
        $http.get(home_url+languageFilePath).success(function(data) {
            $scope.locale_string = data;     
        });
    };
})

.filter('slice', function() {
   return function(arr, start, end) {
   return arr.slice(start, end);
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
	
	translationService.getTranslation($scope, lang);
	
	$scope.exemples = [
		        		{title:"Lasagnes � la cr�me",desc:"Lasagnes classiques avec une pointe de creme",url:"http://www.marmiton.org/lasagnes_a_la_creme"},
		        		{title:"Lasagnes aux champignons",desc:"Lasagnes de ouf avec des champis",url:"http://www.marmiton.org/lasagnes_aux_champignons"},
		        		{title:"Lasagnes aux �pinards",desc:"Une recette de lasagne pour vos enfants",url:"http://www.marmiton.org/lasagnes_aux_epinards"},	
		        		{title:"Lasagnes cr�meuses",desc:"Lasagnes � la cr�me avec plus de creme",url:"http://www.marmiton.org/lasagnes_cremeuses"},
		        		{title:"Lasagnes champ�tres",desc:"Lasagnes de champis magiques",url:"http://www.marmiton.org/lasagnes_champetres"},
		        		{title:"Lasagnes aux �pines",desc:"Une recette de lasagne pour vos masos",url:"http://www.marmiton.org/lasagnes_aux_epines"},	
		        		{title:"Lasagnes Tagada",desc:"Parce que Haribo quoi",url:"http://www.marmiton.org/lasagnes_tagada"}
		        	];
	
	
	
	
	/* Meal Functions */
	
	$scope.getMealDifficulty = function(meal){
		return new Array(meal.difficulty);
	};
	
	$scope.getMealPrice = function(meal){
		return new Array(meal.cost);
	};
	
	$scope.getMealIcon = function(meal){
		if(meal.pictures && meal.pictures[2] && meal.pictures[2].url){
			return meal.pictures[2].url; 
		}else{
			return home_url+"img/not_available_icon.jpg";
		}
		
	};
	
	$scope.getMealRating = function(meal){
		return new Array(meal.rating);
	};
	
	
	
	
	
	
	
	
	
	/* Meal Carrousel */
	
	
	$scope.mealCarrouselSearch = home_url+"rest/recipe/find/thon";
	
	$scope.mealCarrousel = [];
	
	$scope.mealCarrouselStart = 0;
	
	$scope.mealCarrouselEnd = 8;
	
	$scope.getMealCarrouselFrom = function(url){
		$http.get(url).success(function(data){
			$scope.mealCarrousel = data.data.items;
		}).error(function (data){
			
		});
	};
		
	$scope.loadMoreMealCarrouselIsDisabled = function(){
		return ($scope.mealCarrouselEnd + 1)>$scope.mealCarrousel.length;
	};
	
	$scope.loadLessMealCarrouselIsDisabled = function(){
		return ($scope.mealCarrouselStart - 1)<0;
	};
	
	$scope.loadMoreMealCarrousel = function(){
		$scope.mealCarrouselStart += 1;
		$scope.mealCarrouselEnd += 1;
	};
	
	$scope.loadLessMealCarrousel = function(){
		$scope.mealCarrouselStart -= 1;
		$scope.mealCarrouselEnd -= 1;
	};
		
	$scope.getMealCarrouselFrom($scope.mealCarrouselSearch);
	
	
	
	
		
	
	
	
	
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
	
	
	
	
	
	
	
	/*
	$scope.rowsIdeas = 2;
	$scope.mealsByRowIdeas = 4;
	$scope.mealsIdeasLength = 8;
	$scope.ideas = [];
	$scope.getIdeasMealsFrom(home_url+"rest/recipe/find/thon");
	
	*/
	

	$scope.getHighListMealsFrom = function(url){
		$http.get(url).success(function(data){
			$scope.highlist = data.data.items;
		}).error(function (data){
			
		});
	};
	$scope.rowsHighlist = 2;
	$scope.mealsByRowHighlist = 4;
	$scope.highlist = [];
	$scope.getHighListMealsFrom(home_url+"rest/recipe/find/rouge");
	
	 
	
	$scope.showMealResult = function(){
		return $scope.ideas.length != 0;
	};
	
	//$scope.mymeal = $location.search().search;
	
	
	
	
	

	   //encodeURIComponent($scope.mymeal));
		
	
	
	
	
	
	
	$scope.goToRecipe = function(meal){
		$window.location.href = "http://www.marmiton.org/recettes/recette_"+(meal.title.replace(" ","_"))+"_"+meal.id+".aspx";
	};
	
	
	
	
	
	$scope.goToWines = function(url){
		$window.location.href = home_url+"wines.jsp?meal="+url;
	};
	
	

		
	$scope.loadMoreIdeas = function(){
		$scope.mealsIdeasLength+=4;
	};
	
	$scope.loadMoreIdeasIsDisabled = function(){
		return $scope.mealsIdeasLength>=$scope.ideas.length;
	};
	
	
	

	
	$scope.highlistStart = 0;
	$scope.highlistEnd = 4;
	
	$scope.loadMoreHighlistIsDisabled = function(){
		return ($scope.highlistEnd + 1)>$scope.highlist.length;
	};
	
	$scope.loadLessHighlistIsDisabled = function(){
		return ($scope.highlistStart - 1)<0;
	};
	
	$scope.loadMoreHighlist = function(){
		$scope.highlistStart += 1;
		$scope.highlistEnd += 1;
	};
	
	$scope.loadLessHighlist = function(){
		$scope.highlistStart -= 1;
		$scope.highlistEnd -= 1;
	};
	
}]);

