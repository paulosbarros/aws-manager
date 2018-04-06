document.querySelector( '#get-instances' ).addEventListener( 'click', manager.getInstances );
	
document.querySelector( '#get-instance-by-id' ).addEventListener( 'click', manager.getInstanceById );
	
document.querySelector( '#create-instance-by-type-and-ami' ).addEventListener( 'click', manager.createInstanceByType );

document.querySelector( '#delete-instance-by-id' ).addEventListener( 'click', manager.deleteInstanceById );