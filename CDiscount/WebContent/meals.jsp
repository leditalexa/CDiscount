<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
<title>BestWines - Select Your Meal</title>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link href='css/style.css' rel='stylesheet'>

<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.13/angular.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.13/angular-resource.min.js"></script>
<script type="text/javascript" src="js/main_app.js"></script>

<base href="/">
</head>
<body ng-app="BestWinesApp">


	<div id="central" ng-controller="MealsCtrl">
	<nav class="navbar navbar-default navbar-fixed-top">
		<div id="mealtoolbar" class="container head">
			<div class="row title">
				<h1 class="col-md-6 col-md-offset-3">{{locale_string.YOUR_MEAL_IDEA}}</h1>
			</div>
			<div class="row">
				<div class="col-md-6 col-md-offset-3">
				<!-- <div class="form-inline"> -->
					<div class="form-inline input-group">
					<input type="text" class="form-control rounded-input" id="Meal" name="Meal"
						ng-model="mymeal" ng-change="search()"/>
					<div class="input-group-addon rounded-input"><i class="fa fa-search"></i></div>
					</div>
				</button>
				<!-- </div> -->
</div>
				<div id="mealideas">
				
				</div>

			</div>

		</div>
		</nav>

		<hr />

		<div id="mealtrials" class="mealrow container content">
		<div class="row">
			<h2>{{locale_string.MEALS_HIGHLISTS}}</h2>
			<div ng-repeat="meal in trials" class="col-md-2 col-sm-3 col-xs-4 no-padding">

			<div class="meal">
				<div class="img-ico">
					<img src="{{meal.pictures[2].url}}"/>
				<div class="description">
					<div class="desc">
					<h4>{{meal.title}}</h4>
					<i class="fa fa-star cutlery" ng-repeat="t in getRating(meal) track by $index"></i><br />
					<i class="fa fa-cutlery cutlery" ng-repeat="t in getDifficulty(meal) track by $index"></i><br />
					<i class="fa fa-eur cutlery" ng-repeat="t in getPrice(meal) track by $index"></i><br />
					<a ng-click="goToRecipe(meal)">{{locale_string.URL_RECIPE}}</a>
					</div>
				</div>
				</div>
				<!--
				<p ng-click="goToWines($index)">{{locale_string.SEE_WINES}}</p>
				<p>
					{{locale_string.GO_TO_RECIPE}} <a ng-click="goToRecipe(meal)">{{locale_string.URL_RECIPE}}</a>
				</p> -->

			</div>
			</div>
			</div>
		</div>


	</div>

</body>
</html>