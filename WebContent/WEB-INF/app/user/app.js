/**
 * 
 */
angular.module("userApp",['ngRoute','ui.bootstrap','rms.directives','rms.httpService']);

angular.module("userApp").config(['$routeProvider','$logProvider',"BaseUrlServiceProvider",function($routeProvider,$logProvider,baseUrlServiceProvider){
	
	baseUrlServiceProvider.setBaseUrl("http://localhost:6060/rms/");
	
	$logProvider.debugEnabled(true);
	
	$routeProvider
	.when('/',{
		templateUrl : 'app/user/partials/ProjectList.html',
		controller : 'ProjectListController'
	})
	.when('/project/:projId',{
		templateUrl : 'app/user/partials/ProjectDetails.html',
		controller : 'ProjectDetailsController'
	})
	.when('/requirement/:reqId',{
		templateUrl : 'app/user/partials/RequirementDetails.html',
		controller : 'RequirementDetailsController'
	})
	.when('/addRequirement/project/:projId',{
		templateUrl : 'app/user/partials/AddRequirement.html',
		controller : 'AddRequirementController'
	})
	.when('/addRequirement/requirement/:reqId',{
		templateUrl : 'app/user/partials/AddRequirement.html',
		controller : 'AddChildRequirementController'
	})
	.when('/requirement/update/:reqId',{
		templateUrl : 'app/user/partials/UpdateRequirement.html',
		controller : 'UpdateRequirementController'
	})
	.otherwise({
		redirectTo : '/'
	});
}]);