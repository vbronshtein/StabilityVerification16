package qa.allot.stabilityVerification.core.helpers;

import java.util.List;

import qa.allot.stabilityVerification.core.policy.LineInstance;
import qa.allot.stabilityVerification.core.policy.PipeInstance;
import qa.allot.stabilityVerification.core.policy.PolicyInstance;
import qa.allot.stabilityVerification.core.policy.VcInstance;

public class PolicyInstanceClassification {
	
	
	public PolicyInstanceClassification() {
		super();
	}

	/**
	 * Method Classify all VC to Pipes  and all pipes to lines 
	 * 
	 */
	public void poliClasification(List<PolicyInstance> policyInstances) {
		for (PolicyInstance policyInstance : policyInstances) {
			//VCs classification to Pipes
			if (policyInstance instanceof VcInstance) {
				VcInstance vcInstance = (VcInstance) policyInstance;
				for (PolicyInstance policyInstance2 : policyInstances) {
					if (policyInstance2 instanceof PipeInstance
							&& policyInstance.getLineName().equals(policyInstance2.getLineName())
							&& policyInstance.getPipeName().equals(policyInstance2.getPipeName())) {
						PipeInstance pipeInstance = (PipeInstance) policyInstance2;
						pipeInstance.adToVcList(vcInstance);
						break;
					}
				}
			} else if (policyInstance instanceof PipeInstance) {
				PipeInstance pipeInstance = (PipeInstance) policyInstance;
				for (PolicyInstance policyInstance2 : policyInstances) {
					if (policyInstance2 instanceof LineInstance
							&& policyInstance2.getLineName().equals(policyInstance.getLineName())) {
						LineInstance lineInstance = (LineInstance) policyInstance2;
						lineInstance.addToPipeInstances(pipeInstance);
						break;
					}
				}

			} 
		}
	}

}
