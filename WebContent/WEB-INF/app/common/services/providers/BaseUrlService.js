angular.module("rms.httpService").provider('BaseUrlService', function() {

	var baseUrl = "http://localhost:6060/rmsys/";
	return {
		setBaseUrl:function(value){
			baseUrl = value;
		},
		$get : function() {
			return {
				getBaseUrl : function(){
					return baseUrl;
				}
			}
		}
	}
});