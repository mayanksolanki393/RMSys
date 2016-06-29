/*angular.module("rms.directives").directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);*/

angular.module("rms.directives").directive('fileUpload', ['$parse','$http', function ($parse,$http){
	return {
		restrict : 'A',
		templateUrl : 'app/common/directives/fileupload/fileupload.html',
		link: function($scope, element, attrs) {
            $scope.addFile = function(){
                 $http({
				    method: 'POST',
				    url: 'http://localhost:6060/rms/upload/file',
				    headers: {'Content-Type': undefined },
				    transformRequest: function (data) {
				        var formData = new FormData();
				        formData.append("file", data);
				        return formData;
				    },
				    data: {file: $scope.file }
				
				})
                 .success( function(){
                	 $scope.files.push($scope.file);
                 }).error(function(){
                	alert("Some Error Occured");
                 });
            };
            
            $scope.setFile = function(file){
            	console.log(file)
            	$scope.file = file;
            };
            
           
        }
	};
}]);

angular.module("rms.directives").service

