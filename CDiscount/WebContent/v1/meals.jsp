<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
	<base href="http://localhost:8080/CDiscount/">
	
	<title>BestWines - Select Your Meal</title>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	
	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
	<link rel="stylesheet"	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
	<link href='css/style.css' rel='stylesheet'>

	<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.13/angular.min.js"></script>
	<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.13/angular-resource.min.js"></script>
	<script type="text/javascript" src="js/main_app.js"></script>

	
</head>
<body ng-app="BestWinesApp">

	<div id="hautdepage"></div>

	<!-- BEGIN Central part -->
	<div id="central" ng-controller="MealsCtrl">
	
	
			<hr/>
	
		<!-- BEGIN Meals Highlist -->
		<div id="mealHighlist">

			<h2>{{locale_string.MEALS_HIGHLISTS}}</h2>	
			
			<div class="mealHighlistRow">	
		
				<!-- BEGIN Button  -->
				<button type="button" class="navButtons" style="float:left;"
					 ng-click="loadLessHighlist()"
					 ng-disabled="loadLessHighlistIsDisabled()">
					<img src="img/arrow_prev.jpg">
				</button>
				<button type="button" class="navButtons" style="float:right"
					ng-click="loadMoreHighlist()"
					ng-disabled="loadMoreHighlistIsDisabled()">
					<img src="img/arrow_next.jpg">
				</button>		
					
				<!-- BEGIN Block of Meals -->			
				<div class="productGrid">
							
				
					<!-- BEGIN Meal Element -->
					<div class="meal" ng-repeat="meal in highlist | slice:highlistStart:highlistEnd" >
						
						
						
						<!-- BEGIN Meal Element - block of extra informations and related links -->	
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
						</div><!-- END Meal Element - extra -->			
						
						
						<!-- BEGIN Meal Element - main block - title, center, footer -->		
						
							
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
						
						</div><!-- END Meal Element - main block -->	
									
					
				</div><!-- BEGIN Block of Meals -->	
				
					
				
					
			</div>
		</div><!-- END Meals Highlist -->
	
	
	
	
	
		<hr/>
	
	
	
	
	
	
	
	
		<!-- BEGIN My Meal Search Toolbar -->
		<div id="mealtoolbar">
		
			<div class="row title">
				<h1 class="col-md-6 col-md-offset-3" style="text-align:center">{{locale_string.YOUR_MEAL_IDEA}}</h1>
			</div>
			
			<div class="row">
				<div class="col-md-6 col-md-offset-3">
						<div class="form-inline input-group">
							<form ng-submit="search()" novalidate>
								<input type="text" class="form-control rounded-input" 
									id="Meal" 
									name="Meal"
									ng-model="mymealSearch"
								 />		
								 <div class="input-group-addon rounded-input" ng-click="search()"><i class="fa fa-search"></i></div>
							 </form>
							
								
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
						ng-repeat="meal in ideas | limitTo : mealsIdeasLength" 
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
					
					<div>
						<input type="button" value="{{locale_string.LOAD_MORE_MEALS}}" style="width:100%"
							 ng-click="loadMoreIdeas()"
							 ng-disabled="loadMoreIdeasIsDisabled()"/>
					</div>	
					
				</div>
			
		</div><!-- END My Meal Search Results -->	
						
		
		<a href="#/hautdepage" style="position:fixed;bottom:0px">{{locale_string.GO_UP}}</a>		
	
		
	
	</div><!-- END Central part -->

</body>
</html>