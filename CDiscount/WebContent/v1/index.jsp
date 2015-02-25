<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html >

<head>
	<title>BestWines - Home</title>
	
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
	<link href='css/style.css' rel='stylesheet'>
	
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.13/angular.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.13/angular-resource.min.js"></script>
	<script type="text/javascript" src="js/main_app.js"></script>

	<base href="/">
</head>

<body ng-app="BestWinesApp">


	<div id="central" ng-controller="LoginCtrl">



		<div id="login_layout" ng-show="isPaneShown(1)">
		
			<form name="loginForm" ng-submit="onLogin()" novalidate>
			
				<p>
					<label for="loginLogin">{{locale_string.LOGIN}} :</label>
					<input type="text" class="text" 
						id="loginLogin"
						name="loginLogin"
						ng-model="loginLogin" 
						ng-minlength="6"
						required/>
				</p>
				
				<p>
					<label for="loginPass">{{locale_string.PASSWORD}} :</label>
					<input type="password" class="text" 
						id="loginPass"
						name="loginPass"
						ng-model="loginPass" 
						ng-minlength="6"
						required/>
				</p>	
				
				<p class="buttons">
					<input type="submit" value="{{locale_string.TO_LOG_IN}}"
					  ng-disabled="loginForm.$invalid"
					  />
					<input type="button" value="{{locale_string.TO_SIGN_UP}}"
					  ng-click=setPaneShown(2);  
					  />
				</p>		
				
			</form>
			
		</div>
	
	
		<div id="signup_layout" ng-show="isPaneShown(2)">
			<form id="signup_form">
			
				<p>
					<label for="signup_login">{{locale_string.LOGIN}} :</label>
					<input type="text" class="text" id="signup_login" name="signup_login"/>
					
				</p>
				
				<p>
					<label for="signup_pass">{{locale_string.PASSWORD}} :</label>
					<input type="password" class="text" id="signup_pass" name="signup_pass"/>
				</p>	
				
				<p>
					<label for="signup_pass_confirm">{{locale_string.PASSWORD_CONFIRM}} :</label>
					<input type="password" class="text" id="signup_pass" name="signup_pass"/>
				</p>
				
				<p>
					<label for="signup_age">{{locale_string.YOUR_AGE}} :</label>
					<input type="number" class="text" id="signup_age" name="signup_age"/>
				</p>
				
				<p>
					<label for="signup_cp">{{locale_string.ZIP_CODE}} :</label>
					<input type="text" class="text" id="signup_cp" name="signup_cp"/>
				</p>
				
				<p class="buttons">
					<input type="submit"  value="{{locale_string.TO_SIGN_UP}}" />
					<input type="button"
					  ng-click=setPaneShown(1);
					  value="{{locale_string.CANCEL}}"/>
				</p>		
				
			</form>
		</div>
		
	</div>


</body>

</html>