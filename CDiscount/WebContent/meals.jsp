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
	
	
	
		<!-- BEGIN Meals Carrousel -->
		<div id="mealCarrousel">


			
			<!-- BEGIN Meals Carrousel Row -->
			<div class="mealCarrouselRow">	
		
				<!-- BEGIN Buttons navigation - to navigate in the displayed list -->
				<button type="button" class="navCarrouselButtons" style="float:left;"
					 ng-click="loadLessMealCarrousel()"
					 ng-disabled="loadLessMealCarrouselIsDisabled()">
					<img src="img/arrow_prev.jpg">
				</button>
				<button type="button" class="navCarrouselButtons" style="float:right"
					ng-click="loadMoreMealCarrousel()"
					ng-disabled="loadMoreMealCarrouselIsDisabled()">
					<img src="img/arrow_next.jpg">
				</button><!-- END Buttons navigation  -->		
					
				<!-- BEGIN Block of Products -->			
				<div class="productCarrouselGrid">
							
				
					<!-- BEGIN product Element -->
					<div class="productCarrousel" ng-repeat="product in mealCarrousel | slice:mealCarrouselStart:mealCarrouselEnd" >
												
												
							
							<!-- BEGIN product Element - center -->
							<div class="productCarrouselCenter">
							
										<!-- BEGIN product Element icon - for meals -->
									<div class="productCarrouselIcon">
										<img src="{{getMealIcon(product)}}"/>
									</div><!-- END product Element icon - for meals -->
									
									<div class="productCarrouselExtras">
										<div class="productCarrouselTitle" title="{{product.title}}">
											{{product.title}}
										</div>	
								
								
										<!-- BEGIN product Element infos - for meals -->
										<div class="productCarrouselInfos">
										
										
												<i class="cutlery fa fa-star " title="Avis"
													 ng-repeat="t in getMealRating(product) track by $index"></i>
												<br/>
												<i class="fa fa-cutlery cutlery" title="{{locale_string.MEAL_DIFFICULTY}}"
													 ng-repeat="t in getMealDifficulty(product) track by $index"></i>
												<br/>
												<i class="fa fa-eur cutlery" title="{{locale_string.MEAL_PRICE}}"
													 ng-repeat="t in getMealPrice(product) track by $index"></i>
						
				
										</div><!-- END product Element infos - for meals -->
										
									</div>	
										
									
							</div><!-- END product Element - center -->
							
							
							<!-- BEGIN product Element - footer -->
							<div class="productCarrouselFooter">
							
								
									<a ng-click="goToRecipe(product)">
										<i class="fa fa-file-text-o cutlery" title="{{locale_string.GO_TO_RECIPE}}"></i>
									</a>
									
									<a ng-click="goToWines(product)">
										<i class="fa fa-glass cutlery" title="{{locale_string.SEE_WINES}}"></i>
									 </a>
								 
								
							</div><!-- END product Element - footer -->
							
							
						
					</div><!-- END product Element -->		
					
						
					
				</div><!-- BEGIN Block of products -->	
		
			</div><!-- END Meals Carrousel Row -->
			
		</div><!-- END Meals Carrousel -->








	
	
		<hr/>
	
	
	
	
	
	
	
	
		<!-- BEGIN My Meal Search Toolbar -->
		<div id="mealtoolbar">
		
			<div class="row title">
				<h1 class="col-md-6 col-md-offset-3" style="text-align:center">{{locale_string.YOUR_MEAL_IDEA}}</h1>
			</div>
			
			<div class="row">
				<div class="col-md-6 col-md-offset-3">
						<div class="form-inline input-group">
							<form ng-submit="doMealSearch()" novalidate>
								<input type="text" class="form-control rounded-input" 
									id="Meal" 
									name="Meal"
									ng-model="mealSearch"
								 />		
								 <div class="input-group-addon rounded-input" ng-click="doMealSearch()"><i class="fa fa-search"></i></div>
							 </form>
							
								
						</div>
				</div>
			</div>
				
		</div><!-- END My Meal Search Toolbar -->
		
		
		<hr/>
		
		
	
		<!-- BEGIN Meals Search Result -->
		<div id="mealSearchResult" ng-show="showMealSearchResult()">

									
				<!-- BEGIN Block of Products -->			
				<div class="productGrid">
							
				
					<!-- BEGIN product Element -->
					<div class="product" ng-repeat="product in mealSearchResult | limitTo:mealSearchNumber" >
												
												
							<!-- BEGIN product Element - header -->
							<div class="productHeader">
									
								
										<!-- BEGIN product Element icon - for meals -->
									<div class="productIcon">
										<img src="{{getMealIcon(product)}}"/>
									</div><!-- END product Element icon - for meals -->
										
							</div><!-- END product Element - header -->
							
							<!-- BEGIN product Element - center -->
							<div class="productCenter">
							
									<div class="productTitle">
										{{product.title}}
									</div>	
									
							
									<!-- BEGIN product Element infos - for meals -->
									<div class="productInfos">
									

											<i class="cutlery fa fa-star productRating" ng-repeat="t in getMealRating(product) track by $index"></i>
											
											<br/>	
									
											<i class="fa fa-cutlery cutlery" title="{{locale_string.MEAL_DIFFICULTY}}"
												 ng-repeat="t in getMealDifficulty(product) track by $index"></i>
												 
											<br/>
											
											<i class="fa fa-eur cutlery" title="{{locale_string.MEAL_PRICE}}"
												ng-repeat="t in getMealPrice(product) track by $index"></i>
												
											<br/>	
											
											<a ng-click="goToRecipe(product)">
												<i class="fa fa-file-text-o cutlery" title="{{locale_string.GO_TO_RECIPE}}"></i>
											</a>
							
										
									</div><!-- END product Element infos - for meals -->
									
								
									
							</div><!-- END product Element - center -->
							
							<!-- BEGIN product Element - footer -->
							<div class="productFooter">
								
								La sélection des vins 
								<a ng-click="goToWines(product)">
									<i class="fa fa-glass cutlery" title="{{locale_string.SEE_WINES}}"></i>
								</a>	
									
			
							</div><!-- END product Element - footer -->
							
							
						
					</div><!-- END product Element -->		
					
						<input type="button" value="load more" style="width:100%"
							ng-show="showLoadMoreMealSearchResult()"
							ng-click="loadMoreMealSearchResult()" />
								
							
				</div><!-- BEGIN Block of products -->	
		
		
			
			
		</div><!-- END Meals SearchResult -->
	
					
		
		<a href="#hautdepage" style="position:fixed;bottom:0px">{{locale_string.GO_UP}}</a>		
	
		
	
	</div><!-- END Central part -->

</body>
</html>