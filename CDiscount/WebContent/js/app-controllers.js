'use strict';

var app = angular.module("BestWinesApp")

  

.controller("HomeCtrl", ["$scope", "$window", "$location", "$http", "$cookieStore", "translationService", "UrlProvider", "UserService",
                         function($scope, $window, $location, $http, $cookieStore, translationService, UrlProvider, User) {
	

	
	translationService.getTranslation($scope, translationService.lang); 
	$scope.$watch(function(){return translationService.lang;}, function(newValue, oldValue) {
		translationService.getTranslation($scope, translationService.lang); 
		
	}, true);

	$scope.isSubscribing = false;
	
	$scope.subscribe = function(bool){
		$scope.isSubscribing = bool;
	};
	
	$scope.User = User;
	
	$scope.userDatas = {
	  	identifiant : "",
	  	password : "",
	  	age : "",
	  	firstname : "",
	  	lastname : "",
	  	zipCode : "",
	  	lang : translationService.lang
	  	
  	};
	
	
	
	$scope.onLogin = function(){
		$http.post(UrlProvider.SERVICE_USER,$scope.userDatas).success(function(data){
			if(data.code==0){
				$scope.User.isLogged = true;
				$scope.User.datas = data.content;
				$scope.User.datas.password = $scope.userDatas.password;
				$scope.setUserCookies();
				$location.url(UrlProvider.WINES);
			}else{
				alert(data.message);
			}
		});
	};
	
	$scope.loginByCookie = function(){
		var cookie = $cookieStore.get("UserCookie");
		if(cookie){
			$http.post(UrlProvider.SERVICE_USER,cookie).success(function(data){
				if(data.code==0){
				
					$scope.User.isLogged = true;
					$scope.User.datas = cookie;
					$scope.userDatas = cookie;
					translationService.lang = cookie.lang;

				}
			});
			
		}
	};
	
	$scope.onSignup = function(){
		$http.put(UrlProvider.SERVICE_USER,$scope.userDatas).success(function(data){
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
		$scope.userDatas = {
			  	identifiant : "",
			  	password : "",
			  	age : "",
			  	firstname : "",
			  	lastname : "",
			  	zipCode : "",
			  	lang : translationService.lang
		  	};
		$scope.User.isLogged = false;
		$scope.rmUserCookies();
	};
	
	
	

	
	
	
	
	$scope.setUserCookies = function(){
		$cookieStore.put("UserCookie",$scope.User.datas);
	};
	
	$scope.rmUserCookies = function(){
		$cookieStore.remove("UserCookie");
	};
	
	$scope.loginByCookie();
}])


    
.controller("WinesCtrl", ["$scope",  "$window", "$location", "$http", "$anchorScroll", "translationService", 
                          "Meals", "Wines", "carrouselService", "UserService", "UrlProvider",
                          function($scope, $window, $location, $http, $anchorScroll, translationService,
                        		  Meals, Wines, carrouselService, User, UrlProvider) {
	
	$scope.scrollTo = function(id) {
	    $location.hash(id);
	    $anchorScroll();
	  };

	
	translationService.getTranslation($scope, translationService.lang); 
	$scope.$watch(function(){return translationService.lang;}, function(newValue, oldValue) {
		translationService.getTranslation($scope, translationService.lang); 
	}, true);

	
	$scope.Meals = Meals;
	$scope.Wines = Wines;
	$scope.User = User;
	
	/* Requests */
	$scope.getWineFrom = function(url){
		$scope.wines = [];
		$scope.showNoResult = false;
		$scope.showGIF = true;
		$scope.wineSearchNumber = $scope.WINESEARCHNUMBER;
		$http.get(url).success(function(data){
			$scope.wines = data.content;
			if($scope.wines.length==0){
				$scope.showNoResult = true;
			}
			$scope.showGIF = false;
			$scope.scrollTo("winemarker");
		}).error(function (data){
console.log(data);
			$scope.error = data;
			$scope.showGIF = false;
		});
	};
	
	$scope.doMealCarrouselSearch = function(){
		$scope.getCarrouselFrom(UrlProvider.SERVICE_FIND_RECIPE+$scope.mealSearch);
	};
	
	$scope.doWineKeywordSearch = function(){
		$scope.getWineFrom(UrlProvider.SERVICE_FIND_WINE+$scope.wineSearch);

		
	};
	
	$scope.doWineFromMealCarrousel = function(meal){
		$scope.mealSearch = meal.title;
		$scope.getWineFrom(UrlProvider.SERVICE_ASSOCIATE_WINE+$scope.mealSearch);
		
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

	
	$scope.showNoResult = false;
	$scope.showGIF = false;
	
	$scope.wines = [];
	
	$scope.WINESEARCHNUMBER = 12;
	$scope.wineSearchNumber = $scope.WINESEARCHNUMBER;
	
	$scope.wineSearchNumberIncrement = 4;
	
	$scope.loadMoreWineSearchResult = function(){
		$scope.wineSearchNumber += $scope.wineSearchNumberIncrement;
	};
	

	
	$scope.showLoadMoreWineSearchResult = function(){
		return ($scope.wineSearchNumber < $scope.wines.length);	
	};
	/* wine output */
	
	$scope.showGoUp = function(){
		return $scope.wineSearchNumber > 12;
	};
	
	$scope.getRecommended = function(){
		$scope.wines = [];

		$scope.wineSearchNumber = 6;
				
		$http.post(UrlProvider.SERVICE_RECOMMEND_WINE,{userId:$scope.User.identifiant,num:$scope.wineSearchNumber}).success(function(data){
			$scope.wines = data.content;

		}).error(function (data){
console.log(data);
			$scope.error = data;
	
		});
		
	};
	
	
	$scope.getRecommended();
}])





















.controller("NavCtrl", ["$scope", "$location","$cookieStore", "UrlProvider","translationService", "UserService",
                        function($scope,$location,$cookieStore, UrlProvider , translationService, User) {	
	
	translationService.getTranslation($scope, translationService.lang); 
	$scope.$watch(function(){return translationService.lang;}, function(newValue, oldValue) {
		translationService.getTranslation($scope, translationService.lang); 
	}, true);

	
	$scope.setLang = function(lang){
		translationService.lang = lang;
		$scope.User.datas.lang = lang;
		$cookieStore.put("UserCookie",$scope.User.datas);
	};
	
	
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

