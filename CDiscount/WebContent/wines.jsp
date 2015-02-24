<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html >


<head>
	<base href="http://localhost:8080/CDiscount/">
	
	<title>BestWines - Select Your Wines</title>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	
	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
	<link rel="stylesheet"	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
	<link href='css/style.css' rel='stylesheet'>

	<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.13/angular.min.js"></script>
	<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.13/angular-resource.min.js"></script>
	<script type="text/javascript" src="js/main_app.js"></script>

	
</head>

<body ng-app="BestWinesApp">

	<!-- BEGIN Central part -->
	<div id="central" ng-controller="WinesCtrl">
	
	id_marmiton : {{id_marmiton}}  <br/>
	nom_marmiton : {{nom_marmiton}} <br/>
	
	request : <input type="text" ng-model="request" /> <br/>
	final_request : {{request}}    <br/>
	
	
	<input type="button" value="Envoyer"	ng-click="onclickSendRequest()" /> <br/>

	result : <br/><br/>
	
	 {{result}}<br/><br/>
	 
	 error : <br/><br/>
	 
	 {{error}}

	</div><!-- END Central part -->
	
</body>

</html>