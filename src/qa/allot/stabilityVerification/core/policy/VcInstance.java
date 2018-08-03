package qa.allot.stabilityVerification.core.policy;

import java.util.List;

import qa.allot.stabilityVerification.core.trafficStreams.GlobalVariables;
import qa.allot.stabilityVerification.core.trafficStreams.QosTable;
import qa.allot.stabilityVerification.core.trafficStreams.TrafficStream;

/**
 * VC Instance (+Calculation )
 * 
 * @author vbronshtein
 *
 */
public class VcInstance extends PolicyInstance {

	/**
	 * Calculate method , init all required calculation methods )
	 * 
	 * @param trafficStreams
	 * @param qosTable
	 * @param jumboEnable
	 */
	public void calculate(List<TrafficStream> trafficStreams, QosTable qosTable, boolean jumboEnable) {

		// set all zero if vc with bypass action or jumbo packets with jumbo disable
		if (isBypass() || (!jumboEnable && isJumbo())) {
			this.setBw(0);
			this.setCer(0);
			this.setLiveConnections(0);
			this.setDropConnections(0);
		} else {
			bwCalculation(trafficStreams, qosTable);
			cerClculation(trafficStreams);
			liveConnectionCalculation(trafficStreams);
			dropConnectionCalculation(trafficStreams);
		}
	}

	/**
	 * Internal method calculate BW
	 * @param trafficStreams
	 * @param qosTable
	 */
	private void bwCalculation(List<TrafficStream> trafficStreams, QosTable qosTable) {

		if (this.getLineName().equals("Dos") 
				|| this.getLineName().equals("Drop")) {
			this.setBw(0);
		} else if (this.getLineName().equals("QoS") && this.getVcName().contains("MinVc-pri")) {
			// need redefine QosInstance as Singleton and calculate all values
			switch (this.getVcName()) {
			case "MinVc-pri1":
				this.setBw(qosTable.getVcQosMbPri1());
				break;
			case "MinVc-pri2":
				this.setBw(qosTable.getVcQosMbPri2());
				break;
			case "MinVc-pri3":
				this.setBw(qosTable.getVcQosMbPri3());
				break;
			case "MinVc-pri4":
				this.setBw(qosTable.getVcQosMbPri4());
				break;

			default:
				break;
			}

		} else {

			for (TrafficStream trafficStream : trafficStreams) {
				for (String streamName : getTrafficStreamNames()) {
					if (streamName.equals(trafficStream.getName())) {
						this.setBw((int) (getBw() + trafficStream.getBw()));
						break;
					}
				}
			}
		}
	}

	
	/**
	 * Internal method calculate CER
	 * @param trafficStreams
	 */
	private void cerClculation(List<TrafficStream> trafficStreams) {

		for (TrafficStream trafficStream : trafficStreams) {
			for (String streamName : getTrafficStreamNames()) {
				if (streamName.equals(trafficStream.getName()) && trafficStream.isInternalStream()) {
					this.setCer(this.getCer() + trafficStream.getCer());
				}

			}
		}

	}

	/**
	 * Internal method calculate NOC
	 * @param trafficStreams
	 */
	private void liveConnectionCalculation(List<TrafficStream> trafficStreams) {
		for (TrafficStream trafficStream : trafficStreams) {
			for (String streamName : getTrafficStreamNames()) {
				if (streamName.equals(trafficStream.getName()) && trafficStream.isInternalStream()) {
					setLiveConnections(getLiveConnections() + trafficStream.getNoc());
				}

			}
		}

	}

	/**
	 * Internal method calculate Drop Connections
	 * @param trafficStreams
	 */
	private void dropConnectionCalculation(List<TrafficStream> trafficStreams) {
		int monitorResolution = 30;
		if (isDos()) {
			setDropConnections(getCer() * monitorResolution);
		}

	}

}
