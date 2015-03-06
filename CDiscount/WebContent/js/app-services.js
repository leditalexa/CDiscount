'use strict';
var app = angular.module("BestWinesApp")



.service("translationService", function ($http) {  
    this.getTranslation = function($scope, language) {
        var languageFilePath = lang_dir + language + '.json';
        $http.get(home_url+languageFilePath).success(function(data) {
            $scope.locale_string = data;     
        });
    };
})


.service("UrlProvider", function($location){
	return {
		HOME : "/",
		WINES : "/wines.jsp"
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
});
  
  