var adduser = angular.module('myRequirmentApp');

adduser.controller("addUserController",["$scope","$uibModal","UserService",function($scope,$uibModal,userService){
	
	$scope.addUser = {
			role:"none",
	};
	
	$scope.add = function(){
		userService.addUser($scope.addUser)
		.then(function(response){
			var data = response.data;
			var msg = data.title + "added successfully with id as" + data.id;
			$scope.addAlert("Success",msg);
			$scope.dismissConfirm();
			$scope.addUser={};
		},function(error){
			$scope.addAlert("Danger","Some error occured"); 
			$scope.dismissConfirm();
			
		});
		
	};
	
	//alert messages
	 $scope.alerts = [];

	 $scope.addAlert = function(intype,inmsg) {
		 $scope.alerts.push({type:intype,msg: inmsg});
	 };

	 $scope.closeAlert = function(index) {
	     $scope.alerts.splice(index, 1);
	 };
	 
	 // Confirmation Modal
	 $scope.confirmModal = {};
		
		$scope.openConfirm = function () {
		     $scope.confirmModal = $uibModal.open({
		      animation: true,
		      templateUrl: 'newUserConfirmationModal.html',
		      scope : $scope
		    });
		};
		
		$scope.dismissConfirm = function(){
			$scope.confirmModal.dismiss();
		}
	
}]);

