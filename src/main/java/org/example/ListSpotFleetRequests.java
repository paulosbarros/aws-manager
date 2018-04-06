package org.example;

import java.util.List;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.DescribeSpotFleetRequestsResult;
import com.amazonaws.services.ec2.model.SpotFleetRequestConfig;

public class ListSpotFleetRequests {

	public static void execute() {
		AmazonEC2 ec2 = ClientProvider.getEC2Client();

		DescribeSpotFleetRequestsResult results = ec2.describeSpotFleetRequests();

		List<SpotFleetRequestConfig> spotFleetRequestConfigs = results.getSpotFleetRequestConfigs();
		for (SpotFleetRequestConfig spotFleetRequestConfig : spotFleetRequestConfigs) {
			System.out.println(spotFleetRequestConfig.getSpotFleetRequestId());
			System.out.println(spotFleetRequestConfig.getSpotFleetRequestState());
			System.out.println(spotFleetRequestConfig.getActivityStatus());
		}
	}

	public static void main(String[] args) {
		ListSpotFleetRequests.execute();
//		Result = sfr-513f4e7f-7fdf-42ab-a3be-a9285555c23c
	}

}
