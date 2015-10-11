angular.module('profile', ['navigation', 'auth']).controller('profile' ,function($scope, $http, auth) {
	
		$http.get('/getProfile?email='+window.$userdata.email).success(function(data) {
			$scope.user = data;			
			$scope.user.emailInput = $scope.user.email;		
			$scope.user.firstNameInput = $scope.user.firstName;
			$scope.user.lastNameInput = $scope.user.lastName;
			$scope.user.majorInput = $scope.user.major;
			$scope.user.facultyInput = $scope.user.faculty;			

		}).error(function(data) {
			console.log("error")
			$scope.user = data;			
		});	
		
		$scope.changePassword = function() {
			if($scope.user.password != $scope.user.oldPasswordInput){
				$scope.error = true;
				$scope.error_msg = "Error : Old Password is incorrect!";
				return $scope.error;
			}else{
				$http({
					url : '/changePasswordRest',
					method : "POST",
					headers : {
						'Content-Type' : 'application/json'
					},
					data : {
						"id" : $scope.user.id,
						"password" : $scope.user.newPasswordInput,											
					}
				}).success(function(data, status, headers) {
					$scope.error = false;					
					console.log("update successed");
					auth.clear();
				}).error(function(data, status, headers) {
					$scope.error = true;
					if(status==500){
						$scope.error_msg = "Error : Unknown";
						console.log("update failed");
					}			
				});												
				return $scope.error;
			}					
		}
		
		$scope.edit = false;
		
		$scope.editProfile = function() {
			$( "#inputEmail" ).prop( "disabled", false );
			$( "#inputFirstname" ).prop( "disabled", false );
			$( "#inputLastname" ).prop( "disabled", false );
			$( "#inputMajor" ).prop( "disabled", false );
			$( "#inputFaculty" ).prop( "disabled", false );
			$scope.edit = true;
			return $scope.edit;
		};
				
		$scope.update = function() {
			$http({
				url : '/updateProfile',
				method : "POST",
				headers : {
					'Content-Type' : 'application/json'
				},
				data : {
					"id" : $scope.user.id,
					"email" : $scope.user.emailInput,					
					"firstName" : $scope.user.firstNameInput,
					"lastName" : $scope.user.lastNameInput,
					"major" : $scope.user.majorInput,
					"faculty" : $scope.user.facultyInput
				}
			}).success(function(data, status, headers) {
				$scope.error = false;
				console.log("update successed");
				window.location.replace("/profile");
			}).error(function(data, status, headers) {
				$scope.error = true;
				if(status==500){
					$scope.error_msg = "Error : Duplicate email";
					console.log("Error : duplicate email");
				}			
			});
		}

});