angular.module('userApp').controller("ProjectDetailsController",["$scope","$filter","$routeParams","$uibModal","ProjectService",function($scope,$filter,$routeParams,$uibModal,projectService){
	$scope.project = {
			id :  $routeParams.projId
	};
	
	$scope.search = {
		searchFor:"",	
		searchBy:"id"
	};
	
	$scope.filter = {
			filterBy:"none",
			before : "",
			after :"",
			status:"",
			type:""
	};
	
	//http calls
	$scope.refresh = function(){
		projectService.getProjectById($scope.project.id)
		.then(function(response){
			$scope.project = response.data;
		});
	}
	
	$scope.removeRequirement = function(objRequirement){
		objRequirement = JSON.parse(angular.toJson(objRequirement));
		requirementService.removeRequirement(objRequirement)
		.then(function(response){
			alert(response.data+" removed successfully");
			$scope.refresh();
		},
		function(error){
			$scope.openErrorDialog(error);
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
	
	$scope.$watch('filter.filterBy',function(){
		console.log("Filter by changed");
		$scope.filter.before = "";
		$scope.filter.after ="";
		$scope.filter.status="";
		$scope.filter.type="";
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
			var projectDate = new Date(objProject.createdOn);
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
				return false;
			}
		}
	}
	
	$scope.lastMod = function(objProject){
		if($scope.filter.filterBy!='lastMod'){
			return true;
		}
		else{
			var beforeDate = "";
			var afterDate = "";
			var projectDate = new Date(objProject.lastModifiedOn);
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
				return false;
			}
		}
	}
	
	$scope.typeFilter = function(objProject){
		if($scope.filter.filterBy!='type'||$scope.filter.type==""){
			return true;
		}
		else{
			if(objProject.type==$scope.filter.type){
				return true;
			}
			else{
				return false;
			}
		}
	}
	
	$scope.statusFilter = function(objProject){
		if($scope.filter.filterBy!='status' || $scope.filter.status==""){
			return true;
		}
		else{
			if(objProject.status==$scope.filter.status){
				return true;
			}
			else{
				return false;
			}
		}
	}
	//exception handeling
	$scope.errorModal = {};
		
	$scope.openErrorDialog = function (error) {
		     $scope.errorModal = $uibModal.open({
		      animation: true,
		      templateUrl: 'ErrorMessageDialog.html',
		      scope : error
		    });
		};
		
		$scope.dismissErrorModal = function(){
			$scope.errorModal.dismiss();
		}
}])
