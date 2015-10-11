angular.module('register', []).controller('register', function($scope, $http) {

	$scope.register = function() {

		$http({
			url : '/registerAcc',
			method : "POST",
			headers : {
				'Content-Type' : 'application/json'
			},
			data : {
				"email" : $scope.user.emailInput,
				"password" : $scope.user.passwordInput,
				"student_id" : parseInt($scope.user.studentIDInput),
				"firstName" : $scope.user.firstNameInput,
				"lastName" : $scope.user.lastNameInput,
				"major" : $scope.user.majorInput,
				"faculty" : $scope.user.facultyInput
			}
		}).success(function(data, status, headers) {
			$scope.error = false;
			console.log("register successed");
			window.location.replace("/login");
		}).error(function(data, status, headers) {
			$scope.error = true;
			if(status==500){
				$scope.error_msg = "Error : Duplicate email";
				console.log("Error : duplicate email");
			}else{
				$scope.error_msg = "Error : Duplicate student ID"
				console.log("Error : duplicate student ID");
			}			
		});
	}
});
