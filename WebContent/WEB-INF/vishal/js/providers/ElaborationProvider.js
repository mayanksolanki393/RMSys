var rms = angular.module('myRequirmentApp');

rms.provider('ElaborationService', function() {

	var baseUrl = "http://localhost:6060/rmsys/";
	var relativeUrl = "";

	return {

		$get : function($http, $q, $log) {
			return {

				addElaboration : function(objElaboration) {

					var deferred = $q.defer();
					$log.debug("Sending [put] at " + baseUrl + relativeUrl);
					$http.put(baseUrl + relativeUrl, objElaboration).then(function(data) {
						deferred.resolve(data);
					}, function(error) {
						throw error;
					});
					return deferred.promise;

				},
				
				removeElaboration : function(objElaboration){
					var deferred = $q.defer();
					$log.debug("Sending [delete] at "+baseUrl+relativeUrl+"/"+objElaboration.id+" with data :"+objElaboration);
					$http({
						url:baseUrl+relativeUrl+"/"+objElaboration.id,
						method:'DELETE',
					})
					.then(function(data){
						deferred.resolve(data);
					},function(error){
						throw error;
					});
					return deferred.promise;
				},
				
				updateElaboration : function(objElaboration){
					
					var deferred  = $q.defer();
					$log.debug("Sending [post] at" + baseUrl + relativeUrl);
					$http.post(baseUrl+relativeUrl,objElaboration)
					.then(function(data){
						
						deferred.resolve(data);
					},function(error){
						throw error;
					});
				},
				
				getAllElaborations : function() {
					var deferred = $q.defer();
					$log.debug("Sending [get] at " + baseUrl + relativeUrl);
					$http.get(baseUrl + relativeUrl).then(function(data) {
						deferred.resolve(data);
					}, function(error) {
						throw error;
					});
					return deferred.promise;
				},
				
				getElaborationById : function(objElaboration){
					var deferred = $q.defer();
					$log.debug("Sending [get] at " + baseUrl + relativeUrl);
					$http({
						url:baseUrl+relativeUrl+"/"+objElaboration.id,
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