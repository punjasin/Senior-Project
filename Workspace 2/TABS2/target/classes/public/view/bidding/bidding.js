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
			
			$scope.isUser = function(){
				if(window.$userdata.role=="USER"){
					return true;
				}else{
					return false;
				}
			}
			
			$scope.placeBid = function(){
				 var placebidTime = new Date();
				 $scope.placeTime = toDateTime(placebidTime);				
				if(window.$userdata.token<$scope.bidding.tokenInput){
					$scope.error = true;
					$scope.error_msg = "Error: Insufficient token to place the bid!";
				}else{
					if($scope.updateUserBid == true){
						$http({
							url : '/placeBid',
							method : "POST",
							headers : {
								'Content-Type' : 'application/json'
							},
							data : {
								"id" : $scope.userBidData.id,
								"bidding_id": $routeParams.biddingID,
								"student_id" : window.$userdata.student_id,
								"firstName" : window.$userdata.firstName,
								"lastName" : window.$userdata.lastName,								
								"token" : $scope.bidding.tokenInput,
								"placeBidtime" : $scope.placeTime,
								"status" : "Bidding"
							}
						}).success(function(data, status, headers) {
							$scope.error = false;
							console.log("create successed");
							$scope.selectUpdate = false;
							window.location.replace("/bidding");
						}).error(function(data, status, headers) {						
							$scope.error = true;						
								$scope.error_msg = "Error :";
								console.log("Error : ");						
						});
					}else{
						$http({
							url : '/placeBid',
							method : "POST",
							headers : {
								'Content-Type' : 'application/json'
							},
							data : {
								"bidding_id": $routeParams.biddingID,
								"student_id" : window.$userdata.student_id,
								"firstName" : window.$userdata.firstName,
								"lastName" : window.$userdata.lastName,								
								"token" : $scope.bidding.tokenInput,
								"placeBidtime" : $scope.placeTime,
								"status" : "Bidding"
							}
						}).success(function(data, status, headers) {
							$scope.error = false;
							console.log("create successed");
							$scope.selectUpdate = false;
							window.location.replace("/bidding");
						}).error(function(data, status, headers) {						
							$scope.error = true;						
								$scope.error_msg = "Error :";
								console.log("Error : ");						
						});
					}					
				}											
			}
								
			
			$scope.isUpdate = function(){
				return $scope.selectUpdate;
			}
			
			$scope.formBidding = function(){
				if($scope.bidding.activityIdInput==""){
					$scope.error = true;
			    	$scope.error_msg = "Error: Please select the activity";
				}else{
					var startdate = new Date($('#inputStartTime').val());
				    var enddate = new Date( $('#inputEndTime').val());
				    $scope.st = toDateTime(startdate);
				    $scope.et = toDateTime(enddate);
				    
				    if(startdate.getTime()>enddate.getTime()){
				    	$scope.error = true;
				    	$scope.error_msg = "Error: Invalid Start/End Date time!";			    	
				    }else{
				    	if($scope.selectUpdate==true){		    	
					    	$http({
								url : '/updateBiddingRest',
								method : "POST",
								headers : {
									'Content-Type' : 'application/json'
								},
								data : {
									"id": $routeParams.biddingID,
									"activity_id" : $scope.bidding.activity_id,
									"title" : $scope.bidding.titleInput,
									"description" : $scope.bidding.descriptionInput,								
									"bStart_time" : $scope.st,
									"bEnd_time" : $scope.et,
									"seat_quota" : $scope.bidding.seatQuotaInput
								}
							}).success(function(data, status, headers) {
								$scope.error = false;
								console.log("create successed");
								$scope.selectUpdate = false;
								window.location.replace("/bidding");
							}).error(function(data, status, headers) {						
								$scope.error = true;
								if(status==500){
									$scope.error_msg = "Error : Duplicate Activity";
									console.log("Error : duplicate Activity");
								}
							});
					    	
					    } else {
					    				    	
					    	$http({
							url : '/createBiddingRest',
							method : "POST",
							headers : {
								'Content-Type' : 'application/json'
							},
							data : {
								"activity_id" : $scope.availableActivityList,
								"title" : $scope.bidding.TitleInput,						
								"description" : $scope.bidding.descriptionInput,
								"bStart_time" : $scope.st,
								"bEnd_time" : $scope.et,
								"seat_quota" : $scope.bidding.seatQuotaInput,
								"status": "Close"
							}
						}).success(function(data, status, headers) {
							$scope.error = false;
							console.log("create successed");
							window.location.replace("/bidding");
						}).error(function(data, status, headers) {						
							$scope.error = true;
							if(status==500){
								$scope.error_msg = "Error : Duplicate Activity";
								console.log("Error : duplicate Activity");
								}
							});
					    }
				    }	
				}								
			}
			
			$scope.getAvailableActivities = function(){
				$http.get('/getAvailableActivities').success(function(data) {
					$scope.activities = data;
				}).error(function(data) {
					console.log("error")
				});
			}
			
			$scope.updateBidding = function(){				
				$("a").attr("href", "/bidding/update/"+$routeParams.biddingID);						
			}
			
			
			
			$scope.getBiddingDetail = function(){
				$scope.getAvailableActivities();
				$http.get('/getBidding?id='+$routeParams.biddingID).success(function(data) {					
					$scope.bidding = data;				
					var sDate = new Date($scope.bidding.bStart_time);
					var eDate = new Date($scope.bidding.bEnd_time);
										
					$scope.bidding.title_input = $scope.bidding.title;
					$scope.bidding.activityIdInput = $scope.bidding.activity_id;
					$scope.bidding.descriptionInput = $scope.bidding.description;					
					$scope.bidding.startTimeInput = sDate;
					$scope.bidding.endTimeInput = eDate;
					$scope.bidding.seatQuotaInput = $scope.bidding.seat_quota;					
					$scope.selectUpdate = true;
					$scope.getActivity($scope.bidding.activity_id);
				}).error(function(data) {					
					console.log("error/ creating new activity")
				});									
			}
																	
			$scope.getActivity = function(id){				
				$http.get('/getActivity?id='+id).success(function(data) {
					$scope.activity = data;
				}).error(function(data) {					
					
				});
			}
			
			$scope.deleteBidding = function() {
				$scope.confirmDelete = confirm("Do you want to delete this Bidding?")
				if($scope.confirmDelete==true){
					$http.delete('/deleteBidding/'+$routeParams.biddingID+'/'+$scope.bidding.activity_id).success(function() {
						console.log("delete successed");
						window.location.replace('/bidding');
					}).error(function(){
						console.log("delete failed");
					});	
				}	
			}
			
			$scope.getUserBidData = function() {				
				$http.get('/getUserBidData/'+$routeParams.biddingID+"/"+window.$userdata.student_id).success(function(data) {
					$scope.userBidData = data;					
				}).error(function(data) {
					console.log("error")
				});
				$scope.updateUserBid = true;
			}
			
			
			
			$scope.getBidData = function() {
				$scope.getBiddingDetail();				
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

function toDateTime(date) {
	 var str = "";
	    var year, month, day, hour, min;
	    year = date.getUTCFullYear();
	    month = date.getUTCMonth() + 1;
	    month = month < 10 ? '0' + month : month;
	    day = date.getUTCDate();
	    day = day < 10 ? '0' + day : day;
	    hour = date.getUTCHours()+7;
	    hour = hour < 10 ? '0' + hour : hour;
	    min = date.getUTCMinutes();
	    min = min < 10 ? '0' + min : min;    
	    
	    str += year + '-' + month + '-' + day;
	    str += ' ' + hour + ':' + min + ':00';
    return str;
}

