angular.module('HttpExceptionHandeler',[]).
  factory('$exceptionHandler', ['$log', function($log) {
	  console.log("init");
    return function myExceptionHandler(exception, key) {
      $log.warn(exception, key);	
    };
  }]);