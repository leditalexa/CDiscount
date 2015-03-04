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

	<div id="central" class="container" ng-controller="NavCtrl">
	
		<div id="hautdepage"></div>
	
		<!-- Navigation bar -->
		<nav id="appnavbar" class="navbar row">
	        <div class="container-fluid">
	          <ul class="nav navbar-nav">
	             <li ng-class="{ active: isActive('/') }"><a ng-click="loadHome()">Home</a></li>
       			 <li ng-class="{ active: isActive('/wines')}"><a ng-click="loadWines()">Les vins</a></li>
       			 <li ng-show="User.isConnected">Bienvenue {{User.datas.identifiant}}</li>
	          </ul>
	        </div>
	     </nav><!-- END Navigation bar -->
		
		
			
		<!-- The view -->	
		<div ng-view=""></div>
			
			
			
		<!-- Go Up Button -->					
		<div class="row" style="position:fixed;bottom:0px;background-color:black">
			<a href="#hautdepage" style="font-weight:bold;color:white" title="Remonter">Remonter</a>			
		</div>	
		
	</div>
	
	
</body>

</html>