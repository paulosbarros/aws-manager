package org.example;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;

public class CredentialsProvider {

	protected static AWSCredentialsProvider getAWSCredentialsProvider() {
		// Retrieves the credentials from the shared credentials file
		AWSCredentialsProvider credentials = new EnvironmentVariableCredentialsProvider();
		return credentials;
	}
	
}
