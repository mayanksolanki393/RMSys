var mod = angular.module('httpExceptionHandeler', []);

function ErrorHandeler(msg,handler){
	 this.msg = msg;
	 this.handler = handler;
}

var em = new Map();
em.set('emp.put.500',new ErrorHandeler("Some error occured while adding employee please make sure all the fields are filled.",function(){alert("Hello")}));

mod.factory('$exceptionHandler',function () {
    return function (exception, cause) {
    	
    	var urlEles = exception.config.url.split("/")
    	var url = urlEles[urlEles.length-1];
    	var error_code = url+"."+exception.config.method+"."+exception.status;
    	var error_handler = em.get(error_code.toLowerCase());
    	if(error_handler==null){
    		error_handler.msg = "Some Error Occured";
    		error_handler.handler();
    	}
    	alert(error_handler.msg);
    };
});