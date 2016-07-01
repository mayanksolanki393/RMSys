var rms = angular.module("myRequirmentApp");

rms.provider('ProjectService', function() {

	var baseUrl = "http://localhost:6060/finalproject/";
	var relativeUrl = "emp";
	
	return {
		setBaseUrl:function(value){
			baseUrl = value;
		},
		setRelativeUrl:function(value){
			relativeUrl = value;
		},

		$get : function($http, $q, $log) {
			return {

				addProject : function(objProject) {
					var deferred = $q.defer();
					$log.debug("Sending [put] at " + baseUrl + relativeUrl);
					$http.put(baseUrl + relativeUrl, objProject)
					.then(function(data) {
						deferred.resolve(data);
					}, function(error) {
						throw error;
					});
					return deferred.promise;

				},
				
				removeProject : function(objProject){
					var deferred = $q.defer();
					$log.debug("Sending [delete] at "+baseUrl+relativeUrl+"/"+objProject.id+" with data :"+objProject);
					$http({
						url:baseUrl+relativeUrl+"/"+objProject.id,
						method:'DELETE',
					})
					.then(function(data){
						deferred.resolve(data);
					},function(error){
						throw error;
					});
					return deferred.promise;
				},
				
				updateProject : function(objProject){
					
					var deferred  = $q.defer();
					$log.debug("Sending [post] at" + baseUrl + relativeUrl);
					$http.post(baseUrl+relativeUrl,objProject)
					.then(function(data){
						
						deferred.resolve(data);
					},function(error){
						throw error;
					});
				},
				
				getAllProjects : function() {
					var deferred = $q.defer();
					$log.debug("Sending [get] at " + baseUrl + relativeUrl);
					$http.get(baseUrl + relativeUrl).then(function(data) {
						deferred.resolve(data);
					}, function(error) {
						throw error;
					});
					return deferred.promise;

				},
				
				getProjectById : function(objProject){
					var deferred = $q.defer();
					$log.debug("Sending [get] at " + baseUrl + relativeUrl);
					$http({
						url:baseUrl+relativeUrl+"/"+objProject.id,
						method: 'GET'
					})
					.then(function(data){
						deferred.resolve(data);
					},function(error){
						throw error;
					});
					return deferred.promise;
				}
			}
		}
	};
});