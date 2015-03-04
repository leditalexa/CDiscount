'use strict';

var app = angular.module("BestWinesApp")

  





.controller("HomeCtrl", ["$scope", "$window", "$location", "$http", "translationService", "UrlProvider", "User",
                         function($scope, $window, $location, $http, translationService, UrlProvider, User) {
	
	$scope.User = User;
	
	translationService.getTranslation($scope, lang); 

	$scope.indexPaneShown = 1;
	
	$scope.setPaneShown = function(index){
		$scope.indexPaneShown = index;
	};
	
	$scope.isPaneShown = function(index){
		return index == $scope.indexPaneShown;
	};
	
	
	$scope.onLogin = function(){
		$location.url(UrlProvider.WINES);
	};
	
	$scope.onSignup = function(){
		console.log(User.datas);
		$http.put(User.url,User.datas).error(function(){
			User.isConnected = true;
		});
		
		if(User.isConnected){alert("yooo");}
	};
}])








  
  
.controller("WinesCtrl", ["$scope",  "$window", "$location", "$http", "translationService", "Meals", "Wines", "carrouselService",
                          function($scope, $window, $location, $http, translationService, Meals, Wines, carrouselService) {	
	 

	
	translationService.getTranslation($scope, lang);

	
	$scope.Meals = Meals;
	$scope.Wines = Wines;
	
	/* Requests */
	$scope.getWineFrom = function(url){
		$scope.wineSearchNumber = 4;
		$http.get(url).success(function(data){
			$scope.wines = data.content;
		}).error(function (data){
			$scope.error = data;
		});
	};
	
	$scope.doMealCarrouselSearch = function(){
		$scope.getCarrouselFrom(home_url+"rest/recipe/find/"+$scope.mealSearch);
	};
	
	$scope.doWineKeywordSearch = function(){
		$scope.getWineFrom(home_url+"rest/wine/find/"+$scope.wineSearch);
	};
	
	$scope.doWineFromMealSearch = function(){
		$scope.getWineFrom(home_url+"rest/wine/associated/"+$scope.mealSearch);
	};
	/* Requests */
	
	
	/* Meal form */
	$scope.mealSearch = $location.search().name;
	/* Meal form */
	
	/* carrousel for meal search */
	carrouselService.getCarrouselUtils($scope);
	/* carrousel for meal search */
	
	/* wine form */
	$scope.wineSearch = "";
	
	$scope.wine_price = {
			min:15,
			max:200
	};
	/* wine form */
	
	
	/* wine output */
	$scope.wines = [];
	
	$scope.wineSearchNumber = 4;
	
	$scope.wineSearchNumberIncrement = 4;
	
	$scope.loadMoreWineSearchResult = function(){
		$scope.wineSearchNumber += $scope.wineSearchNumberIncrement;
	};
	
	$scope.showWineSearchResult = function(){
		return ($scope.wines.length!=0);	
	};
	
	
	$scope.showLoadMoreWineSearchResult = function(){
		return ($scope.wineSearchNumber < $scope.wines.length);	
	};
	/* wine output */
	
	
	if($scope.mealSearch != ""){
		$scope.doWineFromMealSearch();
	}
	
	
}])


  
.controller("NavCtrl", ["$scope", "$location", "UrlProvider", "User", function($scope,$location,UrlProvider , User) {	
	
	$scope.User = User;
	
	$scope.loadHome = function () {
	    $location.url(UrlProvider.HOME);
	};
	
	
	$scope.loadWines = function () {
	    $location.url(UrlProvider.WINES);
	};
		
	
	 $scope.isActive = function (viewLocation) { 
		 return viewLocation === $location.path();
	 };
	       
}])

;

