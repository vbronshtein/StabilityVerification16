package qa.allot.stabilityVerification.core.policy;

import java.util.ArrayList;
import java.util.List;

/**
 * Pipe instance class , calculate BW/CER/NOC/Drop connections by summarizing all VCs
 * 
 * @author vbronshtein
 *
 */
public class PipeInstance extends PolicyInstance {

	private List<VcInstance> vcList = new ArrayList<>();

	public List<VcInstance> getVcList() {
		return vcList;
	}

	public void adToVcList(VcInstance vcInstance) {
		this.vcList.add(vcInstance);
	}

	public void pipeCalculation() {
		//on "Time" case take only first VC
		if (this.getLineName().equals("TIME")) {
			setBw((int) vcList.get(0).getBw());
			setCer(vcList.get(0).getCer());
			setLiveConnections(vcList.get(0).getLiveConnections());
			setDropConnections(vcList.get(0).getDropConnections());
		//for all other cases summarize all
		} else {
			for (VcInstance vcInstance : vcList) {
				setBw((int) (getBw() + vcInstance.getBw()));
				setCer(getCer() + vcInstance.getCer());
				setLiveConnections(getLiveConnections() + vcInstance.getLiveConnections());
				setDropConnections(getDropConnections() + vcInstance.getDropConnections());
			}
		}
	}

}
