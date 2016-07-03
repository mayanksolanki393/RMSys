angular.module('userApp').controller("AddRequirementController",["$scope","$uibModal","$routeParams","RequirementService",function($scope,$uibModal,$routeProvider,requirementService){
	
	$scope.addReq = {
			project:{
				id:$routeParams.projId
			},
			type:"none",
			priority:"none",
			status:"none"
	};
	
	$scope.add = function(){
		requirementService.addNewRequirement($scope.addReq)
		.then(function(response){
			var data = response.data;
			var msg = data.title + "added successfully with id as" + data.id;
			$scope.addAlert("Success",msg);
			$scope.dismissConfirm();
			$scope.addReq={};
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
		      templateUrl: 'newRequirementConfirmationModal.html',
		      scope : $scope
		    });
		};
		
		$scope.dismissConfirm = function(){
			$scope.confirmModal.dismiss();
		}
	
}]);