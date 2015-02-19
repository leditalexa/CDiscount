<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html >

<head>
	<title>BestWines - Select Your Meal</title>
	
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
	<link href='css/style.css' rel='stylesheet'>
	
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.13/angular.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.13/angular-resource.min.js"></script>
	<script type="text/javascript" src="js/main_app.js"></script>

	<base href="/">
</head>
<body ng-app="BestWinesApp">


	<div id="central" ng-controller="MealsCtrl">
			
		<div id="mealtoolbar">
			<h1>{{locale_string.YOUR_MEAL_IDEA}}</h1>
			
			<p>
				<label for="Meal">{{locale_string.SEARCH_MEAL}}</label>
				<input type="text" 
					id="Meal"
					name="Meal"
					ng-model="mymeal"
					/>
				
				<input type="button" value="{{locale_string.SEARCH_ACTION}}" 
					ng-click="search()"/>
			</p>	
			
			<div id="mealideas">
		
			</div>
		

		</div>
		
		<hr/>
		
		<div id="mealtrials" class="mealrow">
		
			<h2>{{locale_string.MEALS_HIGHLISTS}}</h2>
			<div ng-repeat="meal in trials"  class="meal">
				
				
				<p>
					{{meal.title}}<br/>
					{{meal.desc}}
				</p>
				<p ng-click="goToWines($index)">
					{{locale_string.SEE_WINES}}
				</p>
				<p>
					{{locale_string.GO_TO_RECIPE}} <a href="{{meal.url}}">{{locale_string.URL_RECIPE}}</a>
				</p>
			
				
			</div>
		</div>
		
		
	</div>
	
</body>
</html>