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
				<!-- AngularJS RangeSlider Local Integration -->
				<link rel="stylesheet" href="js/libs/angularJS/rangeSlider/rangeSlider.css" >
				<script type="text/javascript" src="js/libs/angularJS/rangeSlider/rangeSlider.js"></script>
			
				
			
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
		
	
		
		 <div ng-controller="WinesCtrl">
		  
		  	  	<!-- BEGIN  Meal Toolbar -->
				<div class="producttoolbar row">
			
						<!-- BEGIN  Meal Toolbar - Image-->
						<div class="mealToolbarIcon col-md-2 col-md-offset-1" >
							<img src="img/icone-repas.jpg" class="img-circle" />
						</div><!-- END  Meal Toolbar - Image-->
						
						
						<!-- BEGIN Meal Toolbar - Form part -->
						<div class="mealForm col-md-6 col-md-offset-1">
						
						
							<div class="row">
								
								<form role="form" ng-submit="doMealCarrouselSearch()" novalidate>
												
									<div class="mealToolbarInput  input-group">								
									 	<input type="text" class="form-control rounded-input input-group-lg"  value="{{mealSearch}}" ng-model="mealSearch" placeholder="Trouver un plat, une recette, une idée de repas..."/>			
									    <span class="input-group-addon rounded-input" ng-click="doMealCarrouselSearch()"><i class="fa fa-search"></i></span>
									</div>
										
								</form>
										
							</div>
						</div><!-- END Meal Toolbar - Form part -->
									
			  	 </div><!-- END  Meal Toolbar -->				
		  
		  
		  
				<!-- BEGIN Meal Carrousel -->			
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
					
					
				</div><!-- END Meal Carrousel -->	

		
	







		
		
		  		<!-- BEGIN Wine Toolbar -->
				<div class="producttoolbar row">
			
						<!-- BEGIN Wine Toolbar - Image-->
						<div class="wineToolbarIcon col-md-2 col-md-offset-1" >
							<img src="img/icone-vin.jpg" class="img-circle" />
						</div><!-- END Wine Toolbar - Image-->
						
						
						<!-- BEGIN Wine Toolbar - Form part -->
						<div class="wineForm col-md-6 col-md-offset-1">
						
						
						
							<form role="form" ng-submit="doWineKeywordSearch()" novalidate>
							
								<div class="wineInputGroup row">
									<label for="wineSearchInput">Recherche par mots clés :</label>
								</div>
									
								<div class="row">
								
									<div class="input-group">								
									 	<input id="wineSearchInput" type="text" class="form-control rounded-input input-group-lg" id="Wine" ng-model="wineSearch" placeholder="Trouver un vin par nom, appellation, médaille..."/>			
									    <span class="input-group-addon rounded-input" ng-click="doWineKeywordSearch()"><i class="fa fa-search"></i></span>
									</div>
									
								</div>
								
								
								
								
								<div class="wineInputGroup row">
									<label for="slider-range">Les vins entre {{wine_price.min}}€ et {{wine_price.max}}€</label>
								</div>	
								<div class="row">
									<div id="wineRangeSlider" range-slider min="0" max="300" model-min="wine_price.min" model-max="wine_price.max"></div>
								</div>
								
							
							
								
								 	<!-- Multiple Radios -->
							 	
								<div class="row">
								  <label class="" for="radios">Trier par : </label>
								</div>
								<div class="row">
								  <div class=" col-md-6">
								    <label class="radio" for="radios-0">
								      <input name="radios-criterion" id="radios-0" value="Option one" type="radio">
								      Prix
								    </label>
								    <label class="radio" for="radios-1">
								      <input name="radios-criterion" id="radios-1" value="Option two" type="radio">
								      Avis
								    </label>
								  </div>
								   <div class=" col-md-6">
								    <label class="radio" for="radios-2">
								      <input name="radios-order-by" id="radios-2" value="Option three" type="radio">
								      Croissant
								    </label>
								    <label class="radio" for="radios-3">
								      <input name="radios-order-by" id="radios-3" value="Option four" checked="checked" type="radio">
								      Décroissant
								    </label>
								  </div>
								</div>
							
								
							</form>
							 
						</div><!-- END Wine Toolbar - Form part -->	 
							 	 
				 </div><!-- END Wine Toolbar -->	
				 
				 
				<!-- BEGIN Results Wine -->			
				<div class="productGrid row" >
							
				
					<!-- BEGIN product Element -->
					<div class="product col-lg-3 col-md-4 col-sm-6 col-xs-12" 
						ng-repeat="product in wines| limitTo:wineSearchNumber" >
												
												
							<!-- BEGIN product Element - header -->
							<div class="productHeader">
									
								
										<!-- BEGIN product Element icon - for meals -->
									<div class="productIcon">
										<img src="{{Wines.getWineIcon(product)}}"/>
									</div><!-- END product Element icon - for meals -->
										
							</div><!-- END product Element - header -->
							
							<!-- BEGIN product Element - center -->
							<div class="productCenter">
							
									<div class="productTitle">
										{{product.name}}
									</div>	
									
								
							
									<!-- BEGIN product Element infos - for meals -->
									<div class="productInfos">
									

											<i class="cutlery fa fa-star productRating" ng-repeat="t in Wines.getWineRating(product) track by $index"></i>
											
											<br/>	
									
											
											<i class="fa fa-eur cutlery" title="{{locale_string.MEAL_PRICE}}"
												ng-repeat="t in Wines.getWinePrice(product) track by $index"></i>
												
											<br/>	
											
																
									</div><!-- END product Element infos - for meals -->
									
								
									
							</div><!-- END product Element - center -->
							
							<!-- BEGIN product Element - footer -->
							<div class="productFooter">
								
								
								<a ng-click="Meals.goToWines(product)">
								Ajouter au panier
									<i class="fa fa-shopping-cart cutlery" title="{{locale_string.SEE_WINES}}"></i>
								</a>	
									
			
							</div><!-- END product Element - footer -->
							
							
						
					</div><!-- END product Element -->		
					
					<footer class="col-md-12">
						<input type="button" value="load more" style="width:100%; margin-top:3%;margin-bottom:3%;"
							ng-show="showLoadMoreWineSearchResult()"
							ng-click="loadMoreWineSearchResult()" />
					</footer>		
						
				</div><!-- BEGIN Results Wine -->	
		
				
				<div class="mealNoResults" style="text-align:center" ng-show="!showWineSearchResult()">
					Aucun résultat
				</div>
				 
				 
				 
				 
				 
				 
				 
				 
				 
				 
				 
				 
				 
				 
				 
				 
				 
				 
				 	
		</div><!-- END My Meal Search Toolbar -->
				  
		

	</div><!-- END Central part -->

	
		
</body>
</html>