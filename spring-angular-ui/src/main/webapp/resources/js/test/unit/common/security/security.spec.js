describe('security', function() {
	var $rootScope, $http, $httpBackend, status, userInfo;
	//Templates are loaded as a module via ng-html2js preprocessor in Karma config
	beforeEach(module('security','resources/js/templates/common/security/login/form.tpl.html','template/modal/backdrop.html', 'template/modal/window.html'));

	beforeEach(inject(function(_$rootScope_, _$httpBackend_, _$http_) {
		$rootScope = _$rootScope_;
		$httpBackend = _$httpBackend_;
		$http = _$http_;

		userInfo = {
			id : '1234567890',
			email : 'jo@bloggs.com',
			firstName : 'Jo',
			lastName : 'Bloggs'
		};
		$httpBackend.when('GET', '/current-user').respond(200, {
			user : userInfo
		});
	}));

	//Check $http if there were any http requests/resposnes failing
	afterEach(function() {
		$httpBackend.verifyNoOutstandingExpectation();
		$httpBackend.verifyNoOutstandingRequest();
	});
	
	var service, queue;
	  beforeEach(inject(function($injector) {
	    service = $injector.get('security');
	    queue = $injector.get('securityRetryQueue');
	  }));
	
	describe('showLogin', function() {
	    it("should open the dialog", function() {
	      service.showLogin();
	      $rootScope.$digest();
	      expect(angular.element('.login-form').length).toBeGreaterThan(0);
	    });
	  });
	
	describe('login',function(){
		it('sends an htpp request to login the user',function(){
			$httpBackend.when('POST','api/authenticate/login.json').respond(200,{user:userInfo});
			$httpBackend.expect('POST','api/authenticate/login.json');
			service.login('username','password');
			$httpBackend.flush();
			expect(service.currentUser).toBe(userInfo);
		});
		it('calls queue.retry on a successful login', function() {
			$httpBackend.when('POST', 'api/authenticate/login.json').respond(200, {
				user : userInfo
			});
			spyOn(queue, 'retryAll');
			service.showLogin();
			service.login('username', 'password');
			$httpBackend.flush();
			$rootScope.$digest();
			expect(queue.retryAll).toHaveBeenCalled();
			expect(service.currentUser).toBe(userInfo);
		});
		it('does not call queue.retryAll after a login failure', function() {
			$httpBackend.when('POST', 'api/authenticate/login.json').respond(200, {
				user : null
			});
			spyOn(queue, 'retryAll');
			expect(queue.retryAll).not.toHaveBeenCalled();
			service.login('user', 'password');
			$httpBackend.flush();
			expect(queue.retryAll).not.toHaveBeenCalled();
			expect(service.currentUser).toBe(null);
		});	
		it('returns true to success handlers if the user authenticated', function() {
	      $httpBackend.when('POST', 'api/authenticate/login.json').respond(200, { user: userInfo });
	      service.login('username', 'password').then(function(loggedIn) {
	        expect(loggedIn).toBe(true);
	      });
	      $httpBackend.flush();
	    });
		it('returns false to success handlers if the user was not authenticated', function() {
	      $httpBackend.when('POST', 'api/authenticate/login.json').respond(200, { user: undefined });
	      service.login('username', 'password').then(function(loggedIn) {
	        expect(loggedIn).toBe(false);
	      });
	      $httpBackend.flush();
	    });
	});
	
	describe('logout', function() {
	    beforeEach(function() {
	      $httpBackend.when('POST', 'logout').respond(200, {});
	    });

	    it('sends a http post to clear the current logged in user', function() {
	      $httpBackend.expect('POST', 'logout');
	      service.logout();
	      $httpBackend.flush();
	    });

	    it('redirects to / by default', function() {
	      inject(function($location) {
	        spyOn($location, 'path');
	        service.logout();
	        $httpBackend.flush();
	        expect($location.path).toHaveBeenCalledWith('/');
	      });
	    });

	    it('redirects to the path specified in the first parameter', function() {
	      inject(function($location) {
	        spyOn($location, 'path');
	        service.logout('/other/path');
	        $httpBackend.flush();
	        expect($location.path).toHaveBeenCalledWith('/other/path');
	      });
	    });
	  });
	
	describe("currentUser", function() {

	    it("should be unauthenticated to begin with", function() {
	      expect(service.isAuthenticated()).toBe(false);
	      expect(service.isAdmin()).toBe(false);
	      expect(service.currentUser).toBe(null);
	    });
	    it("should be authenticated if we update with user info", function() {
	      var userInfo = {};
	      service.currentUser = userInfo;
	      expect(service.isAuthenticated()).toBe(true);
	      expect(service.isAdmin()).toBe(false);
	      expect(service.currentUser).toBe(userInfo);
	    });
	    it("should be admin if we update with admin user info", function() {
	      var userInfo = { roles:["ROLE_ADMIN"] };
	      service.currentUser = userInfo;
	      expect(service.isAuthenticated()).toBe(true);
	      expect(service.isAdmin()).toBe(true);
	      expect(service.currentUser).toBe(userInfo);
	    });

	    it("should not be authenticated or admin if we clear the user", function() {
	      var userInfo = { roles:["ROLE_ADMIN"] };
	      service.currentUser = userInfo;
	      expect(service.isAuthenticated()).toBe(true);
	      expect(service.isAdmin()).toBe(true);
	      expect(service.currentUser).toBe(userInfo);

	      service.currentUser = null;
	      expect(service.isAuthenticated()).toBe(false);
	      expect(service.isAdmin()).toBe(false);
	      expect(service.currentUser).toBe(null);
	    });
	  });
});