/**
 * 
 */

angular.module("myRequirmentApp",['ngRoute','ui.bootstrap','HttpExceptionHandeler']);

angular.module("myRequirmentApp").config(['$routeProvider','$logProvider','ProjectServiceProvider','RequirementServiceProvider','UserServiceProvider',function($routeProvider,$logProvider,projectServiceProvider,requirementServiceProvider,userServiceProvider){
	$logProvider.debugEnabled(true);
	
	projectServiceProvider.setBaseUrl("http://localhost:6060/finalproject/");
	requirementServiceProvider.setBaseUrl("http://localhost:6060/finalproject/");
	userServiceProvider.setBaseUrl("http://localhost:6060/finalproject/");
	
	$routeProvider
	.when('/listProject',{
		templateUrl : 'app/emp-manager/partials/project/ProjectList.html',
		controller : 'projectListController'
	})
	.when('/addProject',{
		templateUrl : 'app/emp-manager/partials/project/AddProject.html',
		controller:'addProjectController'
	})
	.when('/updateProject',{
		templateUrl : 'app/emp-manager/partials/project/UpdateProject.html',
		controller : 'updateProjectController'
	})
	.when('/listRequirement',{
		templateUrl : 'app/emp-manager/partials/requirement/RequirementList.html',
		controller : 'requirementListController'
	})
	.when('/addRequirement',{
		templateUrl : 'app/emp-manager/partials/requirement/AddRequirement.html',
		controller : 'addRequirementController'
	})
	.when('/updateRequirement',{
		templateUrl : 'app/emp-manager/partials/requirement/UpdateRequirementList.html',
		controller : 'updateRequirementController'
	})
	.when('/listUser',{
		templateUrl : 'app/emp-manager/partials/user/UserList.html',
		controller : 'userListController'
	})
	.when('/addUser',{
		templateUrl : 'app/emp-manager/partials/user/AddUser.html',
		controller : 'addUserController'
	})
	.when('/updateUser',{
		templateUrl : 'app/emp-manager/partials/user/UpdateUser.html',
		controller : 'updateUserController'
	})
	.otherwise({
		redirectTo : '/'
	});
}]);