/**
 * 
 */

angular.module("empApp",['ngRoute','ui.bootstrap','HttpExceptionHandeler']);

angular.module("empApp").config(['$routeProvider','$logProvider',function($routeProvider,$logProvider){
	
	$logProvider.debugEnabled(true);
	
	$routeProvider
	.when('/',{
		templateUrl : 'app/admin/partials/ProjectList.html',
		controller : 'ProjectController'
	})
	.when('/users',{
		templateUrl : 'app/admin/partials/Users.html',
		controller:'UserCtrl'
	})
	.when('/adduser',{
		templateUrl : 'app/admin/partials/adduser.html',
		controller:'NewUser'
	})
	.when('/addproject',{
		templateUrl : 'app/admin/partials/addproject.html',
		controller:'NewProject'
	})
	.otherwise({
		redirectTo : '/'
	});
}]);