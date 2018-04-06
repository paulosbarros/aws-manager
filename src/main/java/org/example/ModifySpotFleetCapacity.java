package org.example;

import com.amazonaws.services.ec2.model.ModifySpotFleetRequestRequest;

public class ModifySpotFleetCapacity {
	private static final String REQUEST_ID_CREATED = "sfr-278497b0-9c1d-44a7-80e4-1dceec063459";

	public static void execute() {
		ModifySpotFleetRequestRequest modifySpotFleetRequestRequest = new ModifySpotFleetRequestRequest()
			.withSpotFleetRequestId(REQUEST_ID_CREATED)
			.withTargetCapacity(2);
		
		ClientProvider
			.getEC2Client()
			.modifySpotFleetRequest(modifySpotFleetRequestRequest);
	}
	
	public static void main(String[] args) {
		ModifySpotFleetCapacity.execute();
	}
}
