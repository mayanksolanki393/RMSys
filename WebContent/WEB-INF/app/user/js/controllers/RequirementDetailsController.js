angular.module('userApp').controller("ProjectDetailsController",["$scope","$filter","$routeParams","RequirementService",function($scope,$filter,$routeParams,requirementService){
	$scope.requirements = [];
	$scope.temp = {};
	$scope.proj = {
			id :  $routeParams.projId
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
	
	//http calls
	$scope.refresh = function(){
		requirementService.getAllRequirements($scope.proj.id)
		.then(function(response){
			$scope.requirements = response.data;
		});
	}
	
	$scope.removeRequirement = function(objRequirement){
		objRequirement = JSON.parse(angular.toJson(objRequirement));
		requirementService.removeRequirement(objRequirement)
		.then(function(response){
			alert(response.data+" removed successfully");
			$scope.refresh();
		});
	}
	
	$scope.$watch('search.searchBy',function(){
		console.log("here");
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
}])
