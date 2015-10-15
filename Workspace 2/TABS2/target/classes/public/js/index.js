angular
		.module('index', [ 'ngRoute', 'auth', 'home', 'activity', 'navigation', 'register', 'profile', 'bidding' ])
		.config(
				
				function($routeProvider, $httpProvider, $locationProvider) {					
					
					$routeProvider.when('/', {
						templateUrl : 'view/home/home.html',
						controller : 'home'
					}).when('/activity', {
						templateUrl : 'view/activity/activity.html',
						controller : 'activity'
					}).when('/createActivity', {
						templateUrl : 'view/activity/activityForm.html',
						controller : 'activity'
					}).when('/activity/update/:activityID', {
						templateUrl : 'view/activity/activityForm.html',
						controller : 'activity'
					}).when('/activity/:activityID', {						
						templateUrl : 'view/activity/activityDetail.html',
						controller : 'activity'
					}).when('/register', {
						templateUrl : 'view/register/register.html',
						controller : 'register'
					}).when('/bidding', {
						templateUrl : 'view/bidding/bidding.html',
						controller : 'bidding'
					}).when('/bidding/:biddingID', {
						templateUrl : 'view/bidding/biddingDetail.html',
						controller : 'bidding'
					}).when('/profile', {
						templateUrl : 'view/profile/profile.html',
						controller : 'profile'
					}).when('/changePassword', {
						templateUrl : 'view/profile/changePassword.html',
						controller : 'profile'
					}).when('/login', {
						templateUrl : 'view/navigation/login.html',
						controller : 'navigation'
					}).otherwise('/');
					
					$locationProvider.html5Mode(true);
					$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

				}).run(function(auth) {

					// Initialize auth module with the home page and login/logout path
					// respectively
					auth.init('/', '/login', '/logout');

				});