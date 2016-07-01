angular.module("rms.httpService").service('fileUpload', ['$http','$q', function ($http,$q) {
	var deferred = $q.defer();
    this.uploadFileToUrl = function(file, uploadUrl){
        var fd = new FormData();
        fd.append("file", file);
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