<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>

	<!-- Base -->
	<jsp:include page="html/head/base.html" />
	
	<title>BestWines - Select Your Wine</title>
	
	<!-- Metas -->
	<jsp:include page="html/head/metas.html" />
		
	<!-- Librairies -->
	<jsp:include page="html/head/librairies.html" />
	
	<!-- Style-->
	<jsp:include page="html/head/style.html" />

	<!-- Scripts -->
	<jsp:include page="html/head/scripts.html" />
		
	
</head>

<body ng-app="BestWinesApp">

	<div  ng-controller="NavCtrl">
		<!-- Navigation bar -->
		<nav id="appnavbar" class="navbar row">
	        <div class="container-fluid">
	        
	        <a ng-click="loadWines()">
	        	<img id="mainImage" src="img/main.png"/>
	       	</a>

	          
			  <div id="navtool">
				  <span id="navName" ng-show="User.isLogged">
				  {{locale_string.WELCOME}} {{User.datas.identifiant}} (<a ng-click="loadHome()">{{locale_string.MY_ACCOUNT}}</a>) |</span> 
				  <span id="navFlag">
				  	<img src="img/flags/fr.png" ng-click="setLang('fr-FR')" >
				  	<img src="img/flags/gb.png" ng-click="setLang('en-US')">
				  </span>
			  </div>
	        </div>
	     </nav><!-- END Navigation bar -->
		
	</div>
	
	<div id="central" class="container">
	
	

		
			
		<!-- The view -->	
		<div ng-view=""></div>
			

	</div>
	
	
</body>

</html>