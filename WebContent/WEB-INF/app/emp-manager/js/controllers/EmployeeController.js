angular.module("empApp").controller("EmployeeController",["$scope","$filter","EmployeeService",function($scope,$filter,empService){
	$scope.emps = [];
	$scope.temp = {};
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
		empService.list()
		.then(function(response){
			$scope.emps = response.data;
		});
	}
	
	$scope.removeEmployee = function(emp){
		emp = JSON.parse(angular.toJson(emp));
		empService.removeEmployee(emp)
		.then(function(response){
			alert(response.data+" removed successfully");
			$scope.refresh();
		});
	}
	
	$scope.$watch('search.searchBy',function(){
		console.log("here");
		$scope.search.searchFor="";
		$scope.search.searchFor.id="";
		$scope.search.searchFor.firstName="";
		$scope.search.searchFor.lastName="";
		$scope.search.searchFor.dateOfBirth="";
		$scope.search.searchFor.salary="";
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
	
	$scope.dobFilter = function(emp){
		if($scope.filter.filterBy!='dateOfBirth'){
			return true;
		}
		else{
			var beforeDate = "";
			var afterDate = "";
			var empDate = new Date(emp.dateOfBirth);
			if($scope.filter.before!=""){
				beforeDate = new Date($scope.filter.before);
			}
			if($scope.filter.after!=""){
				afterDate = new Date($scope.filter.after);
			}
			if((beforeDate==""||empDate<beforeDate) && (afterDate==""||empDate>afterDate)){
				return true;
			}
			else{
				console.log("returning false");
				return false;
			}
		}
	}
}])