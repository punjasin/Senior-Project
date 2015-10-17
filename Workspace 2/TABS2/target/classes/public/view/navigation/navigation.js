angular.module('navigation', ['ngRoute', 'auth']).controller(
		'navigation',

		function($scope, $route, auth, $http) {

			$scope.credentials = {};
						
			$scope.tab = function(route) {
				return $route.current && route === $route.current.controller;
			};

			$scope.authenticated = function() {			
				return auth.authenticated;
			}

			$scope.login = function($http) {
				auth.authenticate($scope.credentials, function(authenticated) {
					if (authenticated) {
						console.log("Login succeeded")
						$scope.error = false;																			
						window.location.replace("/");
					} else {
						console.log("Login failed")
						$scope.error = true;
					}
				})
			};

			$scope.logout = auth.clear;
			
			$http.get('/getProfile?email=' + window.$userdata.email).success(
					function(data) {
						$scope.user = data;
						window.$userdata = data;						
					});
		});