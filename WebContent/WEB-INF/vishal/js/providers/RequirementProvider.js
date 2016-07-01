var rms = angular.module('myRequirmentApp');

rms.provider('RequirementService', function() {

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

				addNewRequirement : function(objRequirement) {

					var deferred = $q.defer();
					$log.debug("Sending [put] at " + baseUrl + relativeUrl);
					$http.put(baseUrl + relativeUrl, objRequirement).then(function(data) {
						deferred.resolve(data);
					}, function(error) {
						throw error;
					});
					return deferred.promise;

				},
				
				removeRequirement : function(objRequirement){
					var deferred = $q.defer();
					$log.debug("Sending [delete] at "+baseUrl+relativeUrl+"/"+objRequirement.id+" with data :"+objRequirement);
					$http({
						url:baseUrl+relativeUrl+"/"+objRequirement.id,
						method:'DELETE',
					})
					.then(function(data){
						deferred.resolve(data);
					},function(error){
						throw error;
					});
					return deferred.promise;
				},
				
				updateRequirement : function(objRequirement){
					
					var deferred  = $q.defer();
					$log.debug("Sending [post] at" + baseUrl + relativeUrl);
					$http.post(baseUrl+relativeUrl,objRequirement)
					.then(function(data){
						
						deferred.resolve(data);
					},function(error){
						throw error;
					});
				},
				
				getAllRequirements : function() {
					var deferred = $q.defer();
					$log.debug("Sending [get] at " + baseUrl + relativeUrl);
					$http.get(baseUrl + relativeUrl).then(function(data) {
						deferred.resolve(data);
					}, function(error) {
						throw error;
					});
					return deferred.promise;
				},
				
				getRequirementById : function(objRequirement){
					var deferred = $q.defer();
					$log.debug("Sending [get] at " + baseUrl + relativeUrl);
					$http({
						url:baseUrl+relativeUrl+"/"+objRequirement.id,
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