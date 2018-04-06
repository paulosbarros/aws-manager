var manager = (function(){
	
	var getInstances = function(){
		fetch('ec2/spot/')
		.then(function(response) {
			return response.text();
		}).then(function(text){
			appendOutput(text);
		});
	};
	
	var getInstanceById = function(){
		var instanceId = document.querySelector( '#get-instance-by-id-value' ).value;
		if(!instanceId){
			alert('Digite um Id para procurar.');
			return;
		}
		fetch('ec2/spot/' + instanceId)
		.then(function(response) {
			return response.text();
		}).then(function(text){
			appendOutput(text);
		});
	};
	
	var createInstanceByType = function(){
		var instanceType = document.querySelector( '#create-instance-type' ).value;
		var amiId = document.querySelector( '#create-instance-ami' ).value;
		if(!instanceType){
			alert('Digite um Type para criar a instância.');
			return;
		}
		fetch('ec2/spot/' + instanceType + '/' + amiId, {method:'post'})
		.then(function(response) {
			return response.text();
		}).then(function(text){
			appendOutput(text);
		});
	};
	
	var deleteInstanceById = function(){
		var instanceId = document.querySelector( '#delete-instance-by-id-value' ).value;
		if(!instanceId){
			alert('Digite um instanceId para deletar a instância.');
			return;
		}
		fetch('ec2/spot/' + instanceId, {method:'delete'})
		.then(function(response) {
			return response.text();
		}).then(function(text){
			appendOutput(text);
		});
	};
	
	var appendOutput = function ( text ) {
		var newLine = document.createElement("div");
		newLine.innerHTML += text;
		document.querySelector( '#output' ).appendChild(newLine);
	};
	
	return {
		getInstances : getInstances,
		getInstanceById : getInstanceById,
		createInstanceByType : createInstanceByType,
		deleteInstanceById : deleteInstanceById
	}
	
})();
