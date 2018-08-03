package qa.allot.stabilityVerification.core.helpers;

import java.util.List;

import qa.allot.stabilityVerification.core.policy.LineInstance;
import qa.allot.stabilityVerification.core.policy.PipeInstance;
import qa.allot.stabilityVerification.core.policy.PolicyInstance;
import qa.allot.stabilityVerification.core.policy.VcInstance;
import qa.allot.stabilityVerification.core.trafficStreams.QosTable;
import qa.allot.stabilityVerification.core.trafficStreams.TrafficStream;

/**
 * Class for required policy instance calculations 
 * 
 * @author vbronshtein
 *
 */
public class PolicyInstanceCalculation {
	private List<PolicyInstance> policyInstances;

	public PolicyInstanceCalculation(List<PolicyInstance> policyInstances) {
		super();
		this.policyInstances = policyInstances;
	}


	
	/**
	 * Method initialize  all  required policy instance calculations
	 * 
	 * @param trafficStreams
	 * @param qosTable
	 * @param jumboEnable
	 */
	public void CalculatePolicyValues(List<TrafficStream> trafficStreams , QosTable qosTable , boolean jumboEnable) {
		for (PolicyInstance policyInstance : policyInstances) {
			if (policyInstance instanceof VcInstance) {
				VcInstance vcInstance = (VcInstance) policyInstance;
				vcInstance.calculate(trafficStreams ,qosTable ,jumboEnable);
			}
		}
		for (PolicyInstance policyInstance : policyInstances) {
			if (policyInstance instanceof PipeInstance) {
				PipeInstance pipeInstance = (PipeInstance) policyInstance;
				pipeInstance.pipeCalculation();
			}
		}
		for (PolicyInstance policyInstance : policyInstances) {
			if (policyInstance instanceof LineInstance) {
				LineInstance lineInstance = (LineInstance) policyInstance;
				lineInstance.lineCalculation();
			}
		}
	}
}
