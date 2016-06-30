/**
 * 
 */

angular.module("empApp",['ngRoute','ui.bootstrap','HttpExceptionHandeler']);

angular.module("empApp").config(['$routeProvider','$logProvider',function($routeProvider,$logProvider){
	
	$logProvider.debugEnabled(true);
	
	$routeProvider
	.when('/',{
		templateUrl : 'app/user/partials/ProjectList.html',
		controller : 'ProjectController'
	})
	.when('/Mis',{
		templateUrl : 'app/user/partials/Mis.html',
		controller:'MisCtrl'
	})
	.when('/addrequire',{
		templateUrl : 'app/user/partials/Requirment.html',
		controller:'NewRequirementCtrl'
	})
	.otherwise({
		redirectTo : '/'
	});
}]);