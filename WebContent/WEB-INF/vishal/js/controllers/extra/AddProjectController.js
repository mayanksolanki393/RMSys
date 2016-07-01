var addProject = angular.module('myRequirmentApp');

addProject.controller("addProjectController",["$scope","$uibModal","ProjectService",function($scope,$uibModal,projectService){
	
	$scope.addProject = {
			status:"none",
	};
	
	$scope.add = function(){
		projectService.addProject($scope.addProject)
		.then(function(response){
			var data = response.data;
			var msg = data.title + "added successfully with id as" + data.id;
			$scope.addAlert("Success",msg);
			$scope.dismissConfirm();
			$scope.addProject={};
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
		      templateUrl: 'newProjectConfirmationModal.html',
		      scope : $scope
		    });
		};
		
		$scope.dismissConfirm = function(){
			$scope.confirmModal.dismiss();
		}
	
}]);

