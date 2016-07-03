angular.module("rms.httpService").service('fileUploadService', ['$http','$q', function ($http,$q) {
	var deferred = $q.defer();
    this.uploadFileToUrl = function(fd, uploadUrl){
        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
        .success(function(response){
        	 deferred.resolve(response);
        })
        .error(function(error){
        	deferred.reject(error);
        });
        return deferred.promise;
    }
}]);