var app = angular.module('myApp', []);
app.controller('studentCtrl', function($scope, $http) {
  $http.get("http://localhost:8080/students")
  .success(function (data) {	  
	  $scope.students = data;
	  })
  .error(function(){
      alert("error");
  });
});
