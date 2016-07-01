angular.module("empApp").controller("NewEmployeeCtrl",["$scope","$uibModal","EmployeeService",function($scope,$uibModal,empService){
	$scope.emp = {};
	
	$scope.add = function(){
		console.log("add called");
		empService.addEmployee($scope.emp).then(
				function(response){
					var data = response.data;
					var msg = data.firstName+" "+data.lastName+" added successfully with id as "+data.id;
					$scope.addAlert("success",msg);
					$scope.dismissConfirm();
					$scope.emp={};
				},
				function(error){
					$scope.addAlert("danger","Some error occured on server side");
					$scope.dismissConfirm();
				}
		)
	};
	
	//confirmation Modal
	$scope.confirmModal = {};
	
	$scope.openConfirm = function () {
	     $scope.confirmModal = $uibModal.open({
	      animation: true,
	      templateUrl: 'newEmpConfirmationModal.html',
	      scope : $scope
	    });
	};
	
	$scope.dismissConfirm = function(){
		$scope.confirmModal.dismiss();
	}
	
	//alert messages
	 $scope.alerts = [];

	 $scope.addAlert = function(intype,inmsg) {
		 $scope.alerts.push({type:intype,msg: inmsg});
	 };

	 $scope.closeAlert = function(index) {
	     $scope.alerts.splice(index, 1);
	 };
}]);