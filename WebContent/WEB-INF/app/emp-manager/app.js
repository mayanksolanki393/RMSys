/**
 * 
 */

angular.module("empApp",['ngRoute','ui.bootstrap','httpExceptionHandeler']);

angular.module("empApp").config(['$routeProvider','$logProvider',function($routeProvider,$logProvider){
	
	$logProvider.debugEnabled(true);
	
	$routeProvider
	.when('/',{
		templateUrl : 'app/emp-manager/partials/employeelist.html',
		controller : 'EmployeeController'
	})
	.when('/newemployee',{
		templateUrl : 'app/emp-manager/partials/newemployee.html',
		controller:'NewEmployeeCtrl'
	})
	.otherwise({
		redirectTo : '/'
	});
}]);