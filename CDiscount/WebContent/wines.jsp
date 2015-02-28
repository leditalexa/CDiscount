<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
	<base href="http://localhost:8080/CDiscount/">
	
	<title>BestWines - Select Your Meal</title>
	
	<!-- Metas -->

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!-- Styles And Themes -->
			<!-- JQuery Local Integration -->
			<link rel="stylesheet" href="css/jquery/jquery-ui.css">
	
			<!-- Bootstrap Local Integration -->
			<link href="css/bootstrap/css/bootstrap.min.css" rel="stylesheet">
				<!--  Bootstrap via CDN
					<link rel="stylesheet"	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
				-->
			
			<!-- FontAwesome Local Integration -->
			<link href="css/fontawesome/css/font-awesome.min.css" rel="stylesheet">
				<!-- FontAwesome via CDN 
					<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
				-->
			
			<!-- Main style file -->
			<link href='css/style.css' rel='stylesheet'>
		
	<!-- Scripts -->
			<!-- JQuery Local Integration -->
				<script src="js/libs/jquery/jquery.min.js"></script>
				<script src="js/libs/jquery/jquery_ui.js"></script>
								
			<!-- AngularJS Local Integration -->
			<script type="text/javascript" src="js/libs/angularJS/angular.min.js"></script>
			<script type="text/javascript" src="js/libs/angularJS/angular-resource.min.js"></script>
				<!-- AngularJS Via CDN
					<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.13/angular.min.js"></script>
					<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.13/angular-resource.min.js"></script>
				-->
			
			<!-- Main script files -->
			<script type="text/javascript" src="js/main_app.js"></script>

	
</head>
<body ng-app="BestWinesApp">

	<div id="hautdepage"></div>
	
	
	
	
	
	

	<!-- BEGIN Central part -->
	<div id="central"  class="container">
	
		
		<!-- Navigation bar -->
		<nav id="appnavbar" class="navbar row">
	        <div class="container-fluid">
	          <ul class="nav navbar-nav">
	            <li class="active"> <a href="http://localhost:8080/CDiscount/meals.jsp">Accueil</a> </li>
	            <li> <a href="#">Mon compte</a> </li>
	            <li> <a href="#">Mon panier</a> </li>
	          </ul>
	        </div>
	     </nav><!-- END Navigation bar -->
		
	
		
		 <div ng-controller="MealCarrouselCtrl2">
		  
		  		<!-- BEGIN My Meal Search Toolbar -->
				<div id="mealtoolbar" class="row">
			
						<!-- BEGIN My Meal Search Toolbar - Image-->
						<div class="col-md-2 col-md-offset-2" >
							<img src="img/icone-repas.jpg" class="img-circle" />
						</div><!-- END My Meal Search Toolbar - Image-->
						<!-- BEGIN My Meal Search Toolbar - Form part -->
						<div class="col-md-6">
							<form role="form" ng-submit="doCarrouselSearch()" novalidate>
								<p class="input-group inputSearch">	
								 	<input type="text" class="form-control rounded-input input-group-lg" id="Meal" name="Meal" value="{{nom_marmiton}}" ng-model="carrouselSearch" placeholder="Trouver un plat, une recette, une idée de repas..."/>			
								    <span class="input-group-addon rounded-input" ng-click="doCarrouselSearch()"><i class="fa fa-search"></i></span>
								</p>
							 </form>
						 </div><!-- END My Meal Search Toolbar - Form part -->			
				</div><!-- END My Meal Search Toolbar -->
						  
			  
				<!-- BEGIN Block of Products -->			
				<div id="myCarousel"  ng-show="showCarrouselResult()"
					class="productCarrouselGrid slide carousel row">
					
					       
	           		<a class="left carousel-control" ng-click="loadLessCarrousel()">‹</a>
	
	               	<a class="right carousel-control" ng-click="loadMoreCarrousel()">›</a>
				
			
					
				
					<!-- BEGIN product Element col-lg-2 col-md-4 col-sm-6 col-xs-12 -->
					<div class="productCarrousel col-md-2" 
						ng-repeat="product in carrousel | slice:carrouselStart:carrouselEnd" >
												
												
							
							<!-- BEGIN product Element - center -->
							<div class="productCarrouselCenter">
							
										<!-- BEGIN product Element icon - for meals -->
									<div class="productCarrouselIcon">
										<img src="{{Meals.getMealIcon(product)}}"/>
									</div><!-- END product Element icon - for meals -->
									
									<div class="productCarrouselExtras">
										<div class="productCarrouselTitle" title="{{product.title}}">
											{{product.title}}
										</div>	
								
								
										<!-- BEGIN product Element infos - for meals -->
										<div class="productCarrouselInfos">
										
										
												<i class="cutlery fa fa-star " title="Avis"
													 ng-repeat="t in Meals.getMealRating(product) track by $index"></i>
												<br/>
												<i class="fa fa-cutlery cutlery" title="{{locale_string.MEAL_DIFFICULTY}}"
													 ng-repeat="t in Meals.getMealDifficulty(product) track by $index"></i>
												<br/>
												<i class="fa fa-eur cutlery" title="{{locale_string.MEAL_PRICE}}"
													 ng-repeat="t in Meals.getMealPrice(product) track by $index"></i>
						
				
										</div><!-- END product Element infos - for meals -->
										
									</div>	
										
									
							</div><!-- END product Element - center -->
							
							
							<!-- BEGIN product Element - footer -->
							<div class="productCarrouselFooter">
								
						
									<table id="productCarrouselLinks">
										<tr>
											<td  ng-click="Meals.goToRecipe(product)" title="{{locale_string.GO_TO_RECIPE}}">
												<a class="productCarrouselLink" ng-click="Meals.goToRecipe(product)">
													<i class="fa fa-file-text-o cutlery" title="{{locale_string.GO_TO_RECIPE}}"></i>
												</a>
											</td>
											<td ng-click="Meals.goToWines(product)" title="{{locale_string.SEE_WINES}}">
												<a class="productCarrouselLink" ng-click="Meals.goToWines(product)">
													<i class="fa fa-glass cutlery" title="{{locale_string.SEE_WINES}}"></i>
												 </a>
											</td>
										</tr>
									</table>
									
								 
							</div><!-- END product Element - footer -->
									
						
						
					</div><!-- END product Element -->		
					
					
				</div><!-- END Block of products -->	

		</div><!-- END Meal Carroussel -->
	







		<div ng-controller="WinesCtrl">
		
		  		<!-- BEGIN My Meal Search Toolbar -->
				<div id="winetoolbar" class="row">
			
						<!-- BEGIN My Meal Search Toolbar - Image-->
						<div class="col-md-2 col-md-offset-2" >
							<img src="img/icone-vin.jpg" class="img-circle" />
						</div><!-- END My Meal Search Toolbar - Image-->
						<!-- BEGIN My Meal Search Toolbar - Form part -->
						<div class="col-md-6">
							<form role="form" ng-submit="" novalidate>
							
							
								<p class="input-group inputSearch">	
								Par mot clé :
								 	<input type="text" class="form-control rounded-input input-group-lg" id="Wine" ng-model="wineSearch" placeholder="Trouver un vin par nom, appellation, médaille..."/>			
								    <span class="input-group-addon rounded-input" ng-click="doCarrouselSearch()"><i class="fa fa-search"></i></span>
								</p>
								
								 <script>
$(function() {
$( "#slider-range" ).slider({
range: true,
min: 0,
max: 500,
values: [ 75, 300 ],
slide: function( event, ui ) {
$( "#amount" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
}
});
$( "#amount" ).val( "$" + $( "#slider-range" ).slider( "values", 0 ) +
" - $" + $( "#slider-range" ).slider( "values", 1 ) );
});
</script>
<p>
<label for="amount">Price range:</label>
<input type="text" id="amount" readonly style="border:0; color:#f6931f; font-weight:bold;">
</p>
<div id="slider-range"></div>
			
								
								 	<!-- Multiple Radios -->
							<div class="control-group">
							  <label class="control-label" for="radios">Trier par : </label>
								  <div class="controls">
								    <label class="radio" for="radios-0">
								      <input name="radios" id="radios-0" value="Option one" type="radio">
								      Prix
								    </label>
								    <label class="radio" for="radios-1">
								      <input name="radios" id="radios-1" value="Option two" type="radio">
								      Avis
								    </label>
								  </div>
							</div>
								
								
							 </form>
						 </div><!-- END My Meal Search Toolbar - Form part -->			
				</div><!-- END My Meal Search Toolbar -->
						  
		</div>  

























		<div ng-controller="MealsCtrl" >

		
		
	
		<!-- BEGIN Meals Search Result -->
		

								
				<!-- BEGIN Block of Products -->			
				<div class="productGrid row" ng-show="showMealSearchResult()">
							
				
					<!-- BEGIN product Element -->
					<div class="product col-lg-3 col-md-4 col-sm-6 col-xs-12" 
						ng-repeat="product in mealSearchResult | limitTo:mealSearchNumber" >
												
												
							<!-- BEGIN product Element - header -->
							<div class="productHeader">
									
								
										<!-- BEGIN product Element icon - for meals -->
									<div class="productIcon">
										<img src="{{Meals.getMealIcon(product)}}"/>
									</div><!-- END product Element icon - for meals -->
										
							</div><!-- END product Element - header -->
							
							<!-- BEGIN product Element - center -->
							<div class="productCenter">
							
									<div class="productTitle">
										{{product.title}}
									</div>	
									
								
							
									<!-- BEGIN product Element infos - for meals -->
									<div class="productInfos">
									

											<i class="cutlery fa fa-star productRating" ng-repeat="t in Meals.getMealRating(product) track by $index"></i>
											
											<br/>	
									
											<i class="fa fa-cutlery cutlery" title="{{locale_string.MEAL_DIFFICULTY}}"
												 ng-repeat="t in Meals.getMealDifficulty(product) track by $index"></i>
												 
											<br/>
											
											<i class="fa fa-eur cutlery" title="{{locale_string.MEAL_PRICE}}"
												ng-repeat="t in Meals.getMealPrice(product) track by $index"></i>
												
											<br/>	
											
											<a ng-click="Meals.goToRecipe(product)" style="cursor:pointer;">
												<i class="fa fa-file-text-o cutlery" title="{{locale_string.GO_TO_RECIPE}}"></i>
											</a>
							
										
									</div><!-- END product Element infos - for meals -->
									
								
									
							</div><!-- END product Element - center -->
							
							<!-- BEGIN product Element - footer -->
							<div class="productFooter">
								
								
								<a ng-click="Meals.goToWines(product)">
								La sélection des vins 
									<i class="fa fa-glass cutlery" title="{{locale_string.SEE_WINES}}"></i>
								</a>	
									
			
							</div><!-- END product Element - footer -->
							
							
						
					</div><!-- END product Element -->		
					
					<footer class="col-md-12">
						<input type="button" value="load more" style="width:100%; margin-top:3%;margin-bottom:3%;"
							ng-show="showLoadMoreMealSearchResult()"
							ng-click="loadMoreMealSearchResult()" />
					</footer>		
						
				</div><!-- BEGIN Block of products -->	
		
				
				<div class="mealNoResults" style="text-align:center" ng-show="!showMealSearchResult()">
					Aucun résultat
				</div>
				
				
				
			
			
		
	
					
		<div class="row" style="position:fixed;bottom:0px;background-color:black">
			<a href="#hautdepage"
			 style="font-weight:bold;color:white" title="{{locale_string.GO_UP}}">
			 Remonter</a>			
		</div>		
		
		</div>

	</div><!-- END Central part -->

	
		
</body>
</html>