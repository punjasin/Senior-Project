angular.module('bidding', ['ngRoute', 'auth', 'navigation']).controller(
		'bidding', 
		function($scope, $http, $routeParams, $route) {
			
			$scope.getBiddingList = function() {
				$http.get('/getBiddingList').success(function(data) {
					$scope.biddingList = data;
				}).error(function(data) {
					console.log("error")
				});
			}
			
			$scope.isAdmin = function(){
				if(window.$userdata.role=="ADMIN"){
					return true;
				}else{
					return false;
				}
			}
			
			$scope.getBidData = function() {
				
				
				
				$http.get('/getBidData?bId='+$routeParams.biddingID).success(function(data) {
					$scope.bidDataList = data;
				}).error(function(data) {
					console.log("error")
				});
			}
			
		});

function selectBiddingId(x) {
	var row = x.parentNode.parentNode.rowIndex;
	var biddingId = document.getElementById("biddingTable").rows[row].cells[0].innerHTML;	
	$("a").attr("href", "/bidding/"+biddingId);
}
