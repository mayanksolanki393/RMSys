angular.module('userApp').controller("AddRequirementController",["$scope","$uibModal","$routeParams","$location","RequirementService",function($scope,$uibModal,$routeParams,$location,requirementService){
	
	$scope.addReq = {
			project:{
				id:$routeParams.projId,
			},
			constraints:[],
			type:"NEW FEATURE",
			priority:"TRIVIAL",
			status:"OPEN",
			links : ['http://www.google.com']
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
	
	$scope.removeLink = function(index){
		$scope.addReq.links.splice(index,1);
	}
	
	$scope.add = function(){
		requirementService.addNewRequirement($scope.addReq)
		.then(function(response){
			var data = response.data;
			var msg = data.title + " added successfully with id as " + data.id;
			$scope.addAlert("success",msg);
			$scope.dismissConfirm();
			$scope.addReq= {
					project:{
						id:$routeParams.projId,
					},
					constraints:[],
					type:"NEW FEATURE",
					priority:"TRIVIAL",
					status:"OPEN",
					links : ['http://www.google.com']
			}
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