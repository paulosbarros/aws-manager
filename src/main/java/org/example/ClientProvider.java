package org.example;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;

public class ClientProvider {
	
	protected static AmazonEC2 getEC2Client() {
		AWSCredentialsProvider credentials = CredentialsProvider.getAWSCredentialsProvider();

		// Create the AmazonEC2Client object so we can call various APIs.
		return AmazonEC2ClientBuilder.standard()
	        .withCredentials(credentials)
	        .withRegion(Regions.SA_EAST_1)
	        .build();
	}
}
