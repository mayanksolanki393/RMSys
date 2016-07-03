angular.module('userApp').controller("UpdateRequirementController",["$scope","$uibModal","$routeParams","RequirementService",function($scope,$uibModal,$routeParams,requirementService){
	
	$scope.addReq = {
			id:$routeParams.reqId
	};
	
	$scope.inputs = {
			constraints : [
			               'TECHNICAL CONSTRAINTS',
			               'ECONOMICAL CONSTRAINTS',
			               'LEGAL CONSTRAINTS',
			               'OPERATIONAL CONSTRAINTS',
			               'SCHEDULING CONSTRAINTS',
			               'INTERNAL CORPORATE CONSTRAINTS',
			               'INTERNAL PROJECT CONSTRAINTS',
			               'EXTERNAL CONSTRAINTS'
			               ]
	}
	
	$scope.toggleSelection = function(constraint){
		var id = $scope.addReq.constraints.indexOf(constraint);
		
		if(id > -1){
			$scope.addReq.constraints.splice(id,1);
		}
		else{
			$scope.addReq.constraints.push(constraint);
		}
	}
	
	/*$scope.isChecked = function(constraint){
		consol$scope.addReq.constraints
		if($scope.addReq.constraints){
			$scope.addReq.constraints.indexOf(constraint)>-1;
		}
		else{
			return false;
		}
	}*/
	$scope.hasParent = function(){
		console.log("in function");
		return $scope.addReq.parent!=null;
	}
	
	$scope.removeLink = function(index){
		$scope.addReq.links.splice(index,1);
	}
	
	//http calls
	$scope.refresh = function(){
		requirementService.getRequirementById($scope.addReq.id)
		.then(function(response){
			$scope.addReq = response.data;
		});
	}
	$scope.update = function(){
		requirementService.updateRequirement($scope.addReq)
		.then(function(response){
			var data = response.data;
			var msg = data.title + " updated successfully";
			$scope.addAlert("success",msg);
			$scope.dismissConfirm();
			$scope.addReq=response.data;
			window.scrollTo(0,0);
		},function(error){
			$scope.addAlert("danger","Some error occured"); 
			$scope.dismissConfirm();
			window.scrollTo(0,0);
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