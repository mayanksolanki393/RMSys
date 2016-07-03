angular.module("rms.directives").directive('addLink', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        template :  ["<div class='form-inline'>",
        			"<input type='text' ng-model='mylink' class='form-control col-md-6' placeholder='enter link to add'>" ,
        			"&nbsp;&nbsp;",
        			"<input type='button' class='btn btn-primary' ng-click='addLink()' value='Add Link'>",
        			"</div>"].join(""),
        scope : {
        	links:"=",
        },
        controller : function($scope){
        	$scope.mylink="";
        	$scope.addLink = function(){
        		$scope.links.push($scope.mylink);
        		$scope.mylink = "";
        	} 
        }
    };
}]);
