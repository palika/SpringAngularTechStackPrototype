angular.module('security.login.form',[])
.controller('LoginFormController', ['$scope', 'security', function($scope, security) {
	$scope.user = {};	
	$scope.authError = null;
	//Reason why authentication is asked
	$scope.authReason = null;
	if ( security.getLoginReason() ) {
	    $scope.authReason = ( security.isAuthenticated() ) ?
	      'login.reason.notAuthorized' :
	      'login.reason.notAuthenticated';
	}
	
	//Make authentication request to the server
	$scope.login = function(){
		$scope.authError = null;
		console.log($scope.user);
		security.login($scope.user.username, $scope.user.password).then(function(loggedIn) {
		   if ( !loggedIn ) {
		     // If we get here then the login failed due to bad credentials
		     $scope.authError = 'login.error.invalidCredentials';
		   }
		 }, function(x) {
		   // If we get here then there was a problem with the login request to the server
		   $scope.authError = 'login.error.serverError';
		 });
	};
	$scope.clearForm = function() {
      $scope.user = {};
    };
    $scope.cancelLogin = function() {
      security.cancelLogin();
    };
}]);