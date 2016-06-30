angular.module("rms.directives").directive('userName', ['$http', function ($http){
	return {
		restrict : 'A',
		template : "{{username}}",
		scope : {},
		link: function(scope, element, attrs) {
          $http.get("http://localhost:6060/rms/user/current")
                 .success( function(username){
                	 scope.username = username;
                 }).error(function(){
                	scope.username = "user";
                 });  
		}
	};
}]);