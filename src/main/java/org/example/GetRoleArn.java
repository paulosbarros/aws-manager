package org.example;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClient;
import com.amazonaws.services.identitymanagement.model.GetRoleRequest;

public class GetRoleArn {

	protected static String getArnByRoleName(String roleName) {
		// Create a request to get role data.
		GetRoleRequest getRoleRequest = new GetRoleRequest().withRoleName(roleName);
		
		// Get ARN for the role data
		return AmazonIdentityManagementClient.builder()
			.withCredentials(CredentialsProvider.getAWSCredentialsProvider())
			.withRegion(Regions.SA_EAST_1)
			.build()
				.getRole(getRoleRequest)
					.getRole()
						.getArn();
	}
	
}
