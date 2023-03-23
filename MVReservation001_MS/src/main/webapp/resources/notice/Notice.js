/**
 * 
 */
 
 var noticeSock = new SockJS('http://'+window.location.hostname+":"+window.location.port+'/controller'+'/noticeSendPage');
	
	noticeSock.onopen = function() {
		console.log('open');
	};
	
	
	noticeSock.onmessage = function(e) {
		
	var sendData = JSON.parse(e.data);
	var connectMsg = sendData.sendMsg;
	var sendMsg = sendData.sendMsg;
	
	if(sendData.type == "userConnect") {
		Command: toastr["success"](connectMsg, "[로그인알림]")
	} else {
		Command: toastr["warning"](sendMsg, "[공지]")
	}
	
	};

	
	noticeSock.onclose = function() {
		
	};
	
	
	toastr.options = {
			  "closeButton": false,
			  "debug": false,
			  "newestOnTop": false,
			  "progressBar": false,
			  "positionClass": "toast-top-center",
			  "preventDuplicates": false,
			  "onclick": null,
			  "showDuration": "300",
			  "hideDuration": "1000",
			  "timeOut": "5000",
			  "extendedTimeOut": "1000",
			  "showEasing": "swing",
			  "hideEasing": "linear",
			  "showMethod": "fadeIn",
			  "hideMethod": "fadeOut"
			}
	