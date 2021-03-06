angular.module('security.service', [
  'security.retryQueue',    // Keeps track of failed requests that need to be retried once the user logs in
  'security.login',         // Contains the login form template and controller
  'ui.bootstrap.modal'     // Used to display the login form as a modal dialog.
])
.factory('security',['$http','$q','$location', 'securityRetryQueue','$modal'
	,function($http, $q, $location, queue, $modal) {
	  // Redirect to the given url (defaults to '/')
	  function redirect(url) {
	    url = url || '/';
	    $location.path(url);
	  }
	  var loginDialog = null;
	  function openLoginDialog() {
	    if ( loginDialog ) {
	      throw new Error('Trying to open a dialog that is already open!');
	    }
	    loginDialog = $modal.open({templateUrl:'resources/js/templates/common/security/login/form.tpl.html', controller:'LoginFormController'})
	    loginDialog.result.then(onLoginDialogClose);
	  }
	  function closeLoginDialog(success) {
	    if (loginDialog) {
	      loginDialog.close(success);
	    }
	  }
	  function onLoginDialogClose(success) {
	    loginDialog = null;
	    if ( success ) {
	      queue.retryAll();
	    } else {
	      queue.cancelAll();
	      redirect();
	    }
	  }
	  // Register a handler for when an item is added to the retry queue
	  queue.onItemAddedCallbacks.push(function(retryItem) {
	    if ( queue.hasMore() ) {
	      service.showLogin();
	    }
	  });
	  
	// The public API of the service
	  var service = {
	    // Get the first reason for needing a login
	    getLoginReason: function() {
	      return queue.retryReason();
	    },
	    // Show the modal login dialog
	    showLogin: function() {
	      openLoginDialog();
	    },
	    // Attempt to authenticate a user by the given email and password
	    login: function(username, password) {
	      var request = $http({
				url : "api/authenticate/login.json",
				method : "POST",
				data : { 
					username : username,
					password : password
				},
				headers : [{ 'Content-Type': 'application/json' },
				    {'Accept' : 'application/json'}
				]
			});
	      return request.then(function(response) {
	        service.currentUser = response.data.user;
	        if ( service.isAuthenticated() ) {
	          closeLoginDialog(true);
	        }
	        return service.isAuthenticated();
	      });
	    },
	    // Give up trying to login and clear the retry queue
	    cancelLogin: function() {
	      closeLoginDialog(false);
	      redirect();
	    },
	    // Logout the current user and redirect
	    logout: function(redirectTo) {
	      $http.post('logout').then(function() {
	        service.currentUser = null;
	        redirect(redirectTo);
	      });
	    },
	    // Ask the backend to see if a user is already authenticated - this may be from a previous session.
	    requestCurrentUser: function() {
	      if ( service.isAuthenticated() ) {
	        return $q.when(service.currentUser);
	      } else {
	        return $http.get('api/authenticate/current-user.json').then(function(response) {
	          service.currentUser = response.data.user;
	          return service.currentUser;
	        });
	      }
	    },
	    // Information about the current user
	    currentUser: null,
	    // Is the current user authenticated?
	    isAuthenticated: function(){
	      return !!service.currentUser;
	    },	    
	    // Is the current user an adminstrator?
	    isAdmin: function() {
	    	if(!service.currentUser){
	    		return false;
	    	}
	    	var isAdmin = false;
	    	angular.forEach(service.currentUser.roles, function(value, key){
    			isAdmin = isAdmin || (value.name == "ROLE_ADMIN");
	    	});
	    	return isAdmin;
	    }
	  };

	  return service;
}]);