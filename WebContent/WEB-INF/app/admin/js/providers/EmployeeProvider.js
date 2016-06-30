angular.module('empApp').provider('EmployeeService',function(){
	var baseUrl = "http://localhost:8080/rms/";
	var relativeUrl = "emp"
	return {
		setBaseUrl:function(value){
			baseUrl = value;
		},
		setRelativeUrl:function(value){
			relativeUrl = value;
		},
		$get:function($http,$q,$log){
			return{
				list : function(){
					var deferred = $q.defer();
					$log.debug("sending [get] at "+baseUrl+relativeUrl);
					$http.get(baseUrl+relativeUrl)
					.then(function(data){
						deferred.resolve(data);
					},function(error){
						throw error;
					});
					return deferred.promise;
					
				},
				addEmployee : function(emp){
					var deferred = $q.defer();
					$log.debug("sending [put] at "+baseUrl+relativeUrl);
					$http.put(baseUrl+relativeUrl,emp)
					.then(function(data){
						deferred.resolve(data);
					},function(error){
						throw error;
					});
					return deferred.promise;
					
				},
				removeEmployee : function(emp){
					var deferred = $q.defer();
					$log.debug("sending [delete] at "+baseUrl+relativeUrl+"/"+emp.id+" with data :"+emp);
					$http({
						url:baseUrl+relativeUrl+"/"+emp.id,
						method:'DELETE',
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
