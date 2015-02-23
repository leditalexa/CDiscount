<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
	<base href="/">
	
	<title>BestWines - Select Your Meal</title>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	<link href='CDiscount/css/style.css' rel='stylesheet'>
	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
	<link rel="stylesheet"	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
	

	<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.13/angular.min.js"></script>
	<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.13/angular-resource.min.js"></script>
	<script type="text/javascript" src="CDiscount/js/main_app.js"></script>

	
</head>
<body ng-app="BestWinesApp">

	<!-- BEGIN Central part -->
	<div id="central" ng-controller="MealsCtrl">
	
	
		<!-- BEGIN My Meal Search Toolbar -->
		<div id="mealtoolbar">
		
			<div class="row title">
				<h1 class="col-md-6 col-md-offset-3" style="text-align:center">{{locale_string.YOUR_MEAL_IDEA}}</h1>
			</div>
			
			<div class="row">
				<div class="col-md-6 col-md-offset-3">
						<div class="form-inline input-group">
							<input type="text" class="form-control rounded-input" 
								id="Meal" 
								name="Meal"
								ng-model="mymealSearch"
							 />		
							<div class="input-group-addon rounded-input"
								ng-click="search()">
								<i class="fa fa-search"></i>
							</div>
						</div>
				</div>
			</div>
				
		</div><!-- END My Meal Search Toolbar -->
		
		
		
		
		
		<!-- BEGIN My Meal Search Results -->	
		<div id="mealResults" ng-show="showMealResult()">

		<hr/>


			<h2>{{locale_string.MEALS_IDEAS}}</h2>	
			

				<div class="productGrid">
				
					<!-- BEGIN Meal Element -->
					<div class="meal" 
						ng-repeat="meal in ideas | limitTo : 8" 
						>
						
						<div class="mealExtras">
							<p>
								"{{meal.title}}" <br/> {{locale_string.HAVE_INTEREST}}
							</p>
							<p>
								{{locale_string.GO_TO_RECIPE}} <br/>
								<a ng-click="goToRecipe(meal)">
									<i class="fa fa-file-text-o cutlery" title="{{locale_string.GO_TO_RECIPE}}"></i>
								</a>
							</p>
							<p>
								{{locale_string.SEE_WINES}} <br/>
								<a ng-click="goToWines($index)">
									<i class="fa fa-glass cutlery" title="{{locale_string.SEE_WINES}}"></i>
								</a>
							</p>
						</div>	
		
						<div class="mealTitle">{{meal.title}}</div>
						
						<div class="mealCenter">
							<div class="mealInfos">
									<i class="fa fa-cutlery cutlery" title="{{locale_string.MEAL_DIFFICULTY}}"
										 ng-repeat="t in getMealDifficulty(meal) track by $index"></i><br />
									<i class="fa fa-eur cutlery" title="{{locale_string.MEAL_PRICE}}"
										ng-repeat="t in getMealPrice(meal) track by $index"></i>
							</div>
							
							<div class="mealIcon">
								<img src="{{getMealIcon(meal)}}"/>
							</div>
						</div>
						
						<div class="mealRating">
							<i class="fa fa-star cutlery" ng-repeat="t in getMealRating(meal) track by $index"></i>
						</div>
						
					</div><!-- END Meal Element -->	
				</div>
		</div><!-- END My Meal Search Results -->	
						
				
		<hr/>
	
		<!-- BEGIN Meals Highlist -->
		<div id="mealHighlist">

			<h2>{{locale_string.MEALS_HIGHLISTS}}</h2>			
			<div class="productGrid">
			
				<!-- BEGIN Meal Element -->
				<div class="meal" 
					ng-repeat="meal in highlist | limitTo : 4" 
					 >
						
					<div class="mealExtras">
						<p>
							"{{meal.title}}" <br/> {{locale_string.HAVE_INTEREST}}
						</p>
						<p>
							{{locale_string.GO_TO_RECIPE}} <br/>
							<a ng-click="goToRecipe(meal)">
								<i class="fa fa-file-text-o cutlery" title="{{locale_string.GO_TO_RECIPE}}"></i>
							</a>
						</p>
						<p>
							{{locale_string.SEE_WINES}} <br/>
							<a ng-click="goToWines($index)">
								<i class="fa fa-glass cutlery" title="{{locale_string.SEE_WINES}}"></i>
							</a>
						</p>
					</div>		
						
					
						
					<div class="mealTitle">{{meal.title}}</div>
					
					<div class="mealCenter">
						<div class="mealInfos">
								<i class="fa fa-cutlery cutlery" title="{{locale_string.MEAL_DIFFICULTY}}"
									 ng-repeat="t in getMealDifficulty(meal) track by $index"></i>
								<br/>
								<i class="fa fa-eur cutlery" title="{{locale_string.MEAL_PRICE}}"
									ng-repeat="t in getMealPrice(meal) track by $index"></i>
						</div>
						
						<div class="mealIcon">
							<img src="{{getMealIcon(meal)}}"/>
						</div>
					</div>
					
					<div class="mealRating">
						
						<i class="cutlery fa fa-star " ng-repeat="t in getMealRating(meal) track by $index"></i>
					</div>
					
				
								
				</div><!-- END Meal Element -->
			</div>	
		</div><!-- END Meals Highlist -->

	
	</div><!-- END Central part -->

</body>
</html>