'use strict';

var app = angular.module("BestWinesApp")

  

.controller("HomeCtrl", ["$scope", "$window", "$location", "$http", "$cookieStore", "translationService", "UrlProvider", "UserService",
                         function($scope, $window, $location, $http, $cookieStore, translationService, UrlProvider, User) {
	

	
	translationService.getTranslation($scope, lang); 

	$scope.isSubscribing = false;
	
	$scope.subscribe = function(bool){
		$scope.isSubscribing = bool;
	};
	
	$scope.User = User;
	
	$scope.userDatas = {
	  	identifiant : "",
	  	password : "",
	  	age : 0,
	  	firstname : "",
	  	lastname : "",
	  	zip : ""
  	};
	
	
	
	$scope.onLogin = function(){
		console.log("Login");
		console.log($scope.userDatas);
		$http.post("http://localhost:8080/CDiscount/rest/user",$scope.userDatas).success(function(data){
			if(data.code==0){
				$scope.User.isLogged = true;
				$scope.User.datas = data.content;
				$scope.setUserCookies();
				$location.url(UrlProvider.WINES);
			}else{
				alert(data.message);
			}
		});
	};
	
	$scope.onSignup = function(){
		console.log("Signup");
		console.log($scope.userDatas);
		$http.put("http://localhost:8080/CDiscount/rest/user",$scope.userDatas).success(function(data){
			if(data.code==0){
				$scope.User.isLogged = true;
				$scope.User.datas = $scope.userDatas;
				$scope.setUserCookies();
				$location.url(UrlProvider.WINES);
			}else{
				alert(data.message);
			}
		});
	};
	
	$scope.onLogout = function(){
		console.log("Logout");
		$scope.userDatas = {
			  	identifiant : "",
			  	password : "",
			  	age : 0,
			  	firstname : "",
			  	lastname : "",
			  	zip : ""
		  	};
		$scope.User.isLogged = false;
		$scope.rmUserCookies();
	};
	
	
	

	
	
	$scope.getUserCookies = function(){
		console.log("Retrieving cookie");
		var cookie = $cookieStore.get("UserCookie");
		console.log(cookie);
		if(cookie){
			$scope.User.isLogged = true;
			$scope.User.datas = cookie;
			$scope.userDatas = cookie;
		}
	};
	
	
	$scope.setUserCookies = function(){
		console.log("Putting cookie");
		$cookieStore.put("UserCookie",$scope.User.datas);
	};
	
	$scope.rmUserCookies = function(){
		console.log("Removing cookie");
		$cookieStore.remove("UserCookie");
	};
	
	$scope.getUserCookies();
}])


    
.controller("WinesCtrl", ["$scope",  "$window", "$location", "$http", "translationService", 
                          "Meals", "Wines", "carrouselService", "UserService", 
                          function($scope, $window, $location, $http, translationService,
                        		  Meals, Wines, carrouselService, User) {	
	 

	
	translationService.getTranslation($scope, lang);

	
	$scope.Meals = Meals;
	$scope.Wines = Wines;
	$scope.User = User;
	
	/* Requests */
	$scope.getWineFrom = function(url){
		$scope.wines = [];
		$scope.init = false;
		$scope.showGIF = true;	
		$scope.wineSearchNumber = $scope.WINESEARCHNUMBER;
		$http.get(url).success(function(data){
			$scope.wines = data.content;
			$scope.showGIF = false;
		}).error(function (data){
			$scope.error = data;
			$scope.showGIF = false;
		});
	};
	
	$scope.doMealCarrouselSearch = function(){
		$scope.getCarrouselFrom(home_url+"rest/recipe/find/"+$scope.mealSearch);
	};
	
	$scope.doWineKeywordSearch = function(){
		$scope.getWineFrom(home_url+"rest/wine/find/"+$scope.wineSearch);
		
	};
	
	$scope.doWineFromMealCarrousel = function(meal){
		$scope.mealSearch = meal.title;
		$scope.getWineFrom(home_url+"rest/wine/associated/"+$scope.mealSearch);
	};
	
	/* Requests */
	
	
	/* Meal form */
	$scope.mealSearch = "";
	/* Meal form */
	
	/* carrousel for meal search */
	carrouselService.getCarrouselUtils($scope);
	/* carrousel for meal search */
	
	/* wine form */
	$scope.wineSearch = "";
	
	$scope.wine_price = {
			min:0,
			max:300
	};
	/* wine form */
	

	/* tri des vins */
	$scope.wineCriterion = "price";
	$scope.wineOrdering = "";
	
	
	/* wine output */
	$scope.init = true;
	
	$scope.showGIF = false;
	
	$scope.wines = [];
	
	$scope.WINESEARCHNUMBER = 12;
	$scope.wineSearchNumber = $scope.WINESEARCHNUMBER;
	
	$scope.wineSearchNumberIncrement = 4;
	
	$scope.loadMoreWineSearchResult = function(){
		$scope.wineSearchNumber += $scope.wineSearchNumberIncrement;
	};
	
	$scope.noWineSearchResult = function(){
		return ($scope.wines.length==0);	
	};
	
	
	$scope.showLoadMoreWineSearchResult = function(){
		return ($scope.wineSearchNumber < $scope.wines.length);	
	};
	/* wine output */
	

	
}])





















.controller("NavCtrl", ["$scope", "$location", "UrlProvider", "UserService", function($scope,$location,UrlProvider , User) {	
	
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

