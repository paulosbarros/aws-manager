package org.example;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.AuthorizeSecurityGroupIngressRequest;
import com.amazonaws.services.ec2.model.CreateSecurityGroupRequest;
import com.amazonaws.services.ec2.model.IpPermission;
import com.amazonaws.services.ec2.model.IpRange;

public class CreateSecurityGroup {

	public static void execute() {
		// Retrieves the credentials from the shared credentials file
		AWSCredentialsProvider credentials = new EnvironmentVariableCredentialsProvider();
	
		// Create the AmazonEC2Client object so we can call various APIs.
		AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard()
	        .withCredentials(credentials)
	        .withRegion(Regions.SA_EAST_1)
	        .build();
	
		// Create a new security group.
		try {
		    CreateSecurityGroupRequest securityGroupRequest =
		        new CreateSecurityGroupRequest("GettingStartedGroup",
		        "Getting Started Security Group");
		    ec2.createSecurityGroup(securityGroupRequest);
		} catch (AmazonServiceException ase) {
		    // Likely this means that the group is already created, so ignore.
		    System.out.println(ase.getMessage());
		}
	
		IpRange ipAddr = new IpRange();
		ipAddr.setCidrIp("0.0.0.0/0");
	
		// Get the IP of the current host, so that we can limit the Security Group
		// by default to the ip range associated with your subnet.
		try {
		    // Get IP Address
		    InetAddress addr = InetAddress.getLocalHost();
		    String cidrIp = addr.getHostAddress()+"/10";
		    ipAddr.setCidrIp(cidrIp);
		}
		catch (UnknownHostException e) {
		    // Fail here...
		}
	
		// Create a range that you would like to populate.
		Collection<IpRange> ipRanges = new ArrayList<IpRange>();
		ipRanges.add(ipAddr);
	
		// Open up port 22 for TCP traffic to the associated IP from
		// above (e.g. ssh traffic).
		ArrayList<IpPermission> ipPermissions = new ArrayList<IpPermission> ();
		IpPermission ipPermission = new IpPermission();
		ipPermission.setIpProtocol("tcp");
		ipPermission.setFromPort(new Integer(22));
		ipPermission.setToPort(new Integer(22));
		ipPermission.setIpv4Ranges(ipRanges);
		ipPermissions.add(ipPermission);
	
		try {
		    // Authorize the ports to the used.
		    AuthorizeSecurityGroupIngressRequest ingressRequest =
		        new AuthorizeSecurityGroupIngressRequest(
		            "GettingStartedGroup",ipPermissions);
		    ec2.authorizeSecurityGroupIngress(ingressRequest);
		}
		catch (AmazonServiceException ase) {
		    // Ignore because this likely means the zone has already
		    // been authorized.
		    System.out.println(ase.getMessage());
		}
	}
	
	public static void main(String[] args) {
		CreateSecurityGroup.execute();
//		Create the security group 'GettingStartedGroup'
	}
	
}
