angular.module('app', [ 'ngRoute', 'security', 'ui.bootstrap' ]);

angular.module('app').run([ 'security', function(security) {
	// Get the current user when the application starts
	// (in case they are still logged in from a previous session)
	security.requestCurrentUser();
} ]);

angular.module('app').controller('AppCtrl', [ '$scope', function($scope) {

} ]);

angular.module('app').controller(
		'HeaderCtrl',
		[ '$scope', '$location', '$route', 'security',
				function($scope, $location, $route, security) {
					$scope.location = $location;

					$scope.isAuthenticated = security.isAuthenticated;
					$scope.isAdmin = security.isAdmin;

					$scope.home = function() {
						if (security.isAuthenticated()) {
							$location.path('/dashboard');
						} else {
							$location.path('/projectsinfo');
						}
					};

					$scope.isNavbarActive = function(navBarPath) {
						// return navBarPath === breadcrumbs.getFirst().name;
					};

					$scope.hasPendingRequests = function() {
						// return httpRequestTracker.hasPendingRequests();
					};
				} ]);