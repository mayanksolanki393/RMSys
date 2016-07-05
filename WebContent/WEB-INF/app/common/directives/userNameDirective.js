angular.module("rms.directives").directive('userName', ['$http','BaseUrlService', function ($http,baseUrlService){
	return {
		restrict : 'A',
		template : "{{username}}",
		scope : {},
		link: function(scope, element, attrs) {
          $http.get(baseUrlService.getBaseUrl()+"/user/current")
                 .success( function(username){
                	 scope.username = username;
                 }).error(function(){
                	scope.username = "user";
                 });  
		}
	};
}]);