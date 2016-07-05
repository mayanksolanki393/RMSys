angular.module('userApp').controller("RequirementDetailsController",["$scope","$filter","$routeParams","$uibModal","$location","RequirementService","fileUploadService","BaseUrlService",function($scope,$filter,$routeParams,$uibModal,$location,requirementService,fileUploadService,baseUrlService){
	$scope.childReqs = [];
	$scope.requirement = {
			id :  $routeParams.reqId
	};
	
	$scope.search = {
		searchFor:"",	
		searchBy:"id"
	};
	
	$scope.filter = {
			filterBy:"none",
			before : "",
			after :""
	};
	
	$scope.myFile = {};
	
	$scope.baseUrl = baseUrlService.getBaseUrl();
	
	$scope.uploadFile = function(){
		 var fd = new FormData();
	     fd.append("file", $scope.myFile);
	     fd.append("reqId",$scope.requirement.id);
	     fileUploadService.uploadFileToUrl(fd,baseUrlService.getBaseUrl()+"upload/file/requirement").then(
	    		 function(){
	    			 alert("file uploaded successfully");
	    			 $scope.refresh();
	    		 },
	    		 function(error){
	    			 alert(error);
	    		 })
	}
	
	$scope.hasParent = function(){
		console.log("in function");
		return $scope.requirement.parent!=null;
	}
	
	//http calls
	$scope.refresh = function(){
		requirementService.getRequirementById($scope.requirement.id)
		.then(function(response){
			$scope.requirement = response.data;
		});
		
		requirementService.getAllChildRequirements($scope.requirement.id)
		.then(function(response){
			$scope.childReqs = response.data;
		})
	}
	
	$scope.removeRequirement = function(id){
		requirementService.removeRequirement(id)
		.then(function(response){
			if($scope.requirement.parent!=null){
				$location.path("/requirement/"+$scope.requirement.parent.id);
			}
			else{
				$location.path("/project/"+$scope.requirement.project.id);
			}
		});
	}
	
	$scope.$watch('search.searchBy',function(){
		$scope.search.searchFor="";
		$scope.search.searchFor.id="";
		$scope.search.searchFor.title="";
		$scope.search.searchFor.shortTitle="";
		$scope.search.searchFor.type="";
		$scope.search.searchFor.links="";
		$scope.search.searchFor.priority="";
		$scope.search.searchFor.status="";
		$scope.search.searchFor.project="";
	});
	
	$scope.isVisible = function(column){
		if($scope.search.searchBy == column){
			return true;
		}
		return false;
	};
	
	$scope.isVisibleForFilter = function(column){
		if($scope.filter.filterBy == column){
			return true;
		}
		return false;
	};
	
	//sorting logic
	$scope.sortColumn="id";
	$scope.desc = false;
	
	$scope.sort = function(column){
		if($scope.sortColumn == column){
			$scope.desc = !$scope.desc;
		}
		else{
			$scope.sortColumn = column;
			$scope.desc = false;
		}
	};
	
	$scope.sortClass = function(column){
		if($scope.sortColumn == column){
			return $scope.desc == true ? 'arrow-down' : 'arrow-up'; 
		}
		return 'gap';
	};
	
	$scope.dateRangeFilter = function(objProject){
		if($scope.filter.filterBy!='dateRange'){
			return true;
		}
		else{
			var beforeDate = "";
			var afterDate = "";
			var projectDate = new Date(objProject.dateRange);
			if($scope.filter.before!=""){
				beforeDate = new Date($scope.filter.before);
			}
			if($scope.filter.after!=""){
				afterDate = new Date($scope.filter.after);
			}
			if((beforeDate==""||projectDate<beforeDate) && (afterDate==""||projectDate>afterDate)){
				return true;
			}
			else{
				console.log("returning false");
				return false;
			}
		}
	}
	
	 // Confirmation Modal
	 $scope.confirmModal = {};
		
		$scope.openConfirm = function () {
		     $scope.confirmModal = $uibModal.open({
		      animation: true,
		      templateUrl: 'deleteRequirementConfirmationModal.html',
		      scope : $scope
		    });
		};
		
		$scope.dismissConfirm = function(){
			$scope.confirmModal.dismiss();
		}
}])
