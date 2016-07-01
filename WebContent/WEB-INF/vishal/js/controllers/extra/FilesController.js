angular.module("empApp").controller('FilesController',['$scope',"fileUpload",function($scope,fileUpload){
	$scope.files = [];
	$scope.myFile = {};
	
	$scope.uploadFile = function(){
		var file = $scope.myFile;
		var uploadUrl = 'http://localhost:6060/rms/upload/file';
		fileUpload.uploadFileToUrl(file, uploadUrl).then(
				function(response){
					alert("File uploaded successfully "+response);
				},
				function(error){
					alert("Some error occurred"+ error);
				}
		)
	}
}]);