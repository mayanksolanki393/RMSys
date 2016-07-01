var rms = angular.module('myRequirmentApp');

rms.provider('UserService', function() {

	var baseUrl = "http://localhost:6060/rmsys/";
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

				addUser : function(objUser) {

					var deferred = $q.defer();
					$log.debug("Sending [put] at " + baseUrl + relativeUrl);
					$http.put(baseUrl + relativeUrl, objUser).then(function(data) {
						deferred.resolve(data);
					}, function(error) {
						throw error;
					});
					return deferred.promise;

				},
				
				removeUser : function(objUser){
					var deferred = $q.defer();
					$log.debug("Sending [delete] at "+baseUrl+relativeUrl+"/"+objUser.id+" with data :"+objUser);
					$http({
						url:baseUrl+relativeUrl+"/"+objUser.id,
						method:'DELETE',
					})
					.then(function(data){
						deferred.resolve(data);
					},function(error){
						throw error;
					});
					return deferred.promise;
				},
				
				updateUser : function(objUser){
					
					var deferred  = $q.defer();
					$log.debug("Sending [post] at" + baseUrl + relativeUrl);
					$http.post(baseUrl+relativeUrl,objUser)
					.then(function(data){
						
						deferred.resolve(data);
					},function(error){
						throw error;
					});
				},
				
				getAllUsers : function() {
					var deferred = $q.defer();
					$log.debug("Sending [get] at " + baseUrl + relativeUrl);
					$http.get(baseUrl + relativeUrl).then(function(data) {
						deferred.resolve(data);
					}, function(error) {
						throw error;
					});
					return deferred.promise;

				},
				
				getUserById : function(objUser){
					var deferred = $q.defer();
					$log.debug("Sending [get] at " + baseUrl + relativeUrl);
					$http({
						url:baseUrl+relativeUrl+"/"+objUser.id,
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