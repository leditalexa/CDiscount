'use strict';
var app = angular.module("BestWinesApp");


app.service("UrlProvider", function(){
	
	this.base = document.getElementById("home_url").href;
	
	this.HOME        = "/";
	this.WINES       = "/wines.jsp#winemarker";
	this.LANG_DIR	 = this.base + "locale/";
		
	this.SERVICE_FIND_RECIPE     = this.base+"rest/recipe/find/";
	this.SERVICE_USER            = this.base+"rest/user";
	this.SERVICE_FIND_WINE       = this.base+"rest/wine/find/";
	this.SERVICE_ASSOCIATE_WINE  = this.base+"rest/wine/associated/";
	this.SERVICE_RECOMMEND_WINE  = this.base+"rest/recommend/";

});

 


app.service("translationService", ["$http", "UrlProvider", function ($http, UrlProvider) {  
	
	this.lang = "fr-FR";
	this.lang_dir = UrlProvider.LANG_DIR;
	
    this.getTranslation = function($scope, language) {
        $http.get(this.lang_dir+language+'.json').success(function(data) {
            $scope.locale_string = data;     
        });
    };
}]);



  
app.service("carrouselService", ["$http","UrlProvider", function ($http, UrlProvider) {  
	
    this.getCarrouselUtils = function($scope) {
    	
    	
    	$scope.carrousel_url = UrlProvider.SERVICE_FIND_RECIPE;
    	$scope.carrouselSearch = "";
    	$scope.carrousel_request = $scope.carrousel_url+$scope.carrouselSearch;
    	
    	
    	$scope.carrousel = [];
    	
    	$scope.carrouselStart = 0;
    	
    	$scope.carrouselEnd = 6;
    	
    	
    	$scope.getCarrouselFrom = function(url){
    		$scope.carrousel = [];
        	
        	$scope.carrouselStart = 0;
        	
        	$scope.carrouselEnd = 6;
    		$http.get(url).success(function(data){
    			
    			$scope.carrousel = data.content;
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
}]);
  
  