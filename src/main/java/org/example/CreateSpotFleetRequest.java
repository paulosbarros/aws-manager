package org.example;

import java.util.ArrayList;
import java.util.Collection;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.GroupIdentifier;
import com.amazonaws.services.ec2.model.RequestSpotFleetRequest;
import com.amazonaws.services.ec2.model.RequestSpotFleetResult;
import com.amazonaws.services.ec2.model.SpotFleetLaunchSpecification;
import com.amazonaws.services.ec2.model.SpotFleetRequestConfigData;

public class CreateSpotFleetRequest {

	public static RequestSpotFleetResult execute() {
		
		AmazonEC2 ec2 = ClientProvider.getEC2Client();

		// Inicialize a spot fleet request		
		RequestSpotFleetRequest requestSpotFleetRequest = new RequestSpotFleetRequest();
		
		// Get the ARN for the role created using AWS Console. >> AWSServiceRoleForEC2SpotFleet
		String arn = GetRoleArn.getArnByRoleName("AWSServiceRoleForEC2SpotFleet");
		
		// Initialize the spot fleet request configuration
		// Set configured IAM role 'AWSServiceRoleForEC2SpotFleet'
		SpotFleetRequestConfigData spotFleetRequestConfig = new SpotFleetRequestConfigData();
		spotFleetRequestConfig.setIamFleetRole(arn);
		
		// Set the capacity to load only 1 instance.
		spotFleetRequestConfig.setTargetCapacity(1);

		// Request 1 x with a bid price of $0.1.
		spotFleetRequestConfig.setSpotPrice("0.1");
		
		// Set up the specifications of the launch. This includes the
		// instance type (e.g. t1.micro) and the latest Amazon Linux
		// AMI id available. Note, you should always use the latest
		// Amazon Linux AMI id or another of your choosing.
		SpotFleetLaunchSpecification spotFleetLaunchSpecification = new SpotFleetLaunchSpecification();
		spotFleetLaunchSpecification.setImageId("ami-5339733f");
		spotFleetLaunchSpecification.setInstanceType("t1.micro");

		// Create a group of the security groups to the configuration.
		Collection<GroupIdentifier> securityGroups = new ArrayList<GroupIdentifier>();
		
		// Add the security group 'GettingStartedGroup'
		GroupIdentifier gettingStartedGroup = new GroupIdentifier()
			.withGroupId("sg-6883f50e");
		securityGroups.add(gettingStartedGroup);
				
		// Set the collection of security groups in the configuration.
		spotFleetLaunchSpecification.setSecurityGroups(securityGroups);
		
		// Create a collection of SpotFleetLaunchSpecification.
		Collection<SpotFleetLaunchSpecification> launchSpecifications = new ArrayList<SpotFleetLaunchSpecification>();
		launchSpecifications.add(spotFleetLaunchSpecification);

		// Add the collection of specifications to the request configuration
		spotFleetRequestConfig.withLaunchSpecifications(launchSpecifications);

		// Add the configuration to the request
		requestSpotFleetRequest.setSpotFleetRequestConfig(spotFleetRequestConfig);

		// Call the RequestSpotFleet API.
		return ec2.requestSpotFleet(requestSpotFleetRequest);
	}
	
	public static void main(String[] args) {
		RequestSpotFleetResult requestResult = CreateSpotFleetRequest.execute();
		System.out.println(requestResult.getSpotFleetRequestId());
	}
	
}
