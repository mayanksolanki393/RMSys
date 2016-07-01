/**
 * 
 */

angular.module("empApp",['ngRoute','ui.bootstrap','rms.directives','rms.services','rmsHttpService']);

angular.module("empApp").config(['$routeProvider','$logProvider','rmsHttpServiceProvider',function($routeProvider,$logProvider,rmsHttpServiceProvider){
	
	rmsHttpServiceProvider.setBaseUrl("http://localhost:6060/rms/")
	
	$logProvider.debugEnabled(true);
	
	$routeProvider
	.when('/',{
		templateUrl : 'app/admin/partials/employeelist.html',
		controller : 'EmployeeController'
	})
	.when('/newemployee',{
		templateUrl : 'app/admin/partials/newemployee.html',
		controller:'NewEmployeeCtrl'
	})
	.otherwise({
		redirectTo : '/'
	});
}]);