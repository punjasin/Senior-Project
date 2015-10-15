angular.module('activity', ['ngRoute', 'auth', 'navigation']).controller(
		'activity', 
		function($scope, $http, $routeParams, $route) {
			
			$scope.getActivities = function() {
				$http.get('/activities').success(function(data) {
					$scope.activities = data;
				}).error(function(data) {
					console.log("error")
				});
			}
								
			$scope.getDetail = function(){
				
				$http.get('/getActivity?id='+$routeParams.activityID).success(function(data) {					
					$scope.activity = data;				
					var sDate = new Date($scope.activity.start_time);
					var eDate = new Date($scope.activity.end_time);
										
					$scope.activity.nameInput = $scope.activity.activity_name;
					$scope.activity.descriptionInput = $scope.activity.description;
					$scope.activity.placeInput = $scope.activity.place;
					$scope.activity.startTimeInput = sDate;
					$scope.activity.endTimeInput = eDate;
					$scope.activity.seatQuotaInput = $scope.activity.seat_quota;	
					$scope.selectUpdate = true;
				}).error(function(data) {
					console.log("error")
				});													
			}
			$scope.isUpdate = function() {
				return $scope.selectUpdate;
			}
			
			
			$scope.updateActivity = function(){				
				$("a").attr("href", "/activity/update/"+$routeParams.activityID);						
			}
			
			
			$scope.formActivity = function(){		  
			    var startdate = new Date($('#inputStartTime').val());
			    var enddate = new Date( $('#inputEndTime').val());
			    $scope.st = toDateTime(startdate);
			    $scope.et = toDateTime(enddate);			    
			    			    
			    
			    if($scope.selectUpdate==true){		    	
			    	$http({
						url : '/updateActivityRest',
						method : "POST",
						headers : {
							'Content-Type' : 'application/json'
						},
						data : {
							"id": $routeParams.activityID,
							"activity_name" : $scope.activity.nameInput,
							"description" : $scope.activity.descriptionInput,
							"place" : $scope.activity.placeInput,
							"start_time" : $scope.st,
							"end_time" : $scope.et,
							"seat_quota" : $scope.activity.seatQuotaInput
						}
					}).success(function(data, status, headers) {
						$scope.error = false;
						console.log("create successed");
						$scope.selectUpdate = false;
						window.location.replace("/activity");
					}).error(function(data, status, headers) {
						$scope.error = true;
						if(status==500){
							$scope.error_msg = "Error : Duplicate Activity";
							console.log("Error : duplicate Activity");
						}
					});
			    	
			    } else {
			    	$http({
					url : '/createActivityRest',
					method : "POST",
					headers : {
						'Content-Type' : 'application/json'
					},
					data : {
						"activity_name" : $scope.activity.nameInput,
						"description" : $scope.activity.descriptionInput,
						"place" : $scope.activity.placeInput,
						"start_time" : $scope.st,
						"end_time" : $scope.et,
						"seat_quota" : $scope.activity.seatQuotaInput
					}
				}).success(function(data, status, headers) {
					$scope.error = false;
					console.log("create successed");
					window.location.replace("/activity");
				}).error(function(data, status, headers) {
					$scope.error = true;
					if(status==500){
						$scope.error_msg = "Error : Duplicate Activity";
						console.log("Error : duplicate Activity");
						}
					});
			    }
			   
			    }
			
			
			$scope.isAdmin = function(){
				if(window.$userdata.role=="ADMIN"){
					return true;
				}else{
					return false;
				}
			}
						
			$scope.deleteActivity = function(){	
				$scope.confirmDelete = confirm("Do you want to delete this activity?")
				if($scope.confirmDelete==true){
					$http.delete('/deleteAct?id='+$scope.activity.id).success(function() {
						console.log("delete successed");
						window.location.replace('/activity');
					}).error(function(){
						console.log("delete failed");
					});	
				}			
			}
		
		});


function selectId(x) {
	var row = x.parentNode.parentNode.rowIndex;
	var activityId = document.getElementById("activityTable").rows[row].cells[0].innerHTML;	
	$("a").attr("href", "/activity/"+activityId);
}

function toDateTime(date) {
    var str = "";
    var year, month, day, hour, min;
    year = date.getUTCFullYear();
    month = date.getUTCMonth() + 1;
    month = month < 10 ? '0' + month : month;
    day = date.getUTCDate();
    day = day < 10 ? '0' + day : day;
    hour = date.getUTCHours();
    hour = hour < 10 ? '0' + hour : hour;
    min = date.getUTCMinutes();
    min = min < 10 ? '0' + min : min;    
    
    str += year + '-' + month + '-' + day;
    str += ' ' + hour + ':' + min + ':00';
    return str;
}
