package qa.allot.stabilityVerification.core.trafficStreams;

import java.util.List;

/**
 * QOS Table Calculation : 
 * Pipe_Max_Qos 
 * vc_Qos_Mb_Pri1 
 * vc_Qos_Mb_Pri2
 * vc_Qos_Mb_Pri3 
 * vc_Qos_Mb_Pri4
 * 
 * @author vbronshtein
 *
 */
public class QosTable {
	private int pipeCatalogMaxQos;
	private int vcCatalogQosMbPri1;
	private int vcCatalogQosMbPri2;
	private int vcCatalogQosMbPri3;
	private int vcCatalogQosMbPri4;

	// For BW calculation
	private int pipeMaxQos;
	private int vcQosMbPri1;
	private int vcQosMbPri2;
	private int vcQosMbPri3;
	private int vcQosMbPri4;

	public int getPipeCatalogMaxQos() {
		return pipeCatalogMaxQos;
	}

	public void setPipeCatalogMaxQos(int pipeCatalogMaxQos) {
		this.pipeCatalogMaxQos = pipeCatalogMaxQos;
	}

	public int getVcCatalogQosMbPri1() {
		return vcCatalogQosMbPri1;
	}

	public void setVcCatalogQosMbPri1(int vcCatalogQosMbPri1) {
		this.vcCatalogQosMbPri1 = vcCatalogQosMbPri1;
	}

	public int getVcCatalogQosMbPri2() {
		return vcCatalogQosMbPri2;
	}

	public void setVcCatalogQosMbPri2(int vcCatalogQosMbPri2) {
		this.vcCatalogQosMbPri2 = vcCatalogQosMbPri2;
	}

	public int getVcCatalogQosMbPri3() {
		return vcCatalogQosMbPri3;
	}

	public void setVcCatalogQosMbPri3(int vcCatalogQosMbPri3) {
		this.vcCatalogQosMbPri3 = vcCatalogQosMbPri3;
	}

	public int getVcCatalogQosMbPri4() {
		return vcCatalogQosMbPri4;
	}

	public void setVcCatalogQosMbPri4(int vcCatalogQosMbPri4) {
		this.vcCatalogQosMbPri4 = vcCatalogQosMbPri4;
	}

	public int getPipeMaxQos() {
		return pipeMaxQos;
	}

	public void setPipeMaxQos(int pipeMaxQos) {
		this.pipeMaxQos = pipeMaxQos;
	}

	public int getVcQosMbPri1() {
		return vcQosMbPri1;
	}

	public void setVcQosMbPri1(int vcQosMbPri1) {
		this.vcQosMbPri1 = vcQosMbPri1;
	}

	public int getVcQosMbPri2() {
		return vcQosMbPri2;
	}

	public void setVcQosMbPri2(int vcQosMbPri2) {
		this.vcQosMbPri2 = vcQosMbPri2;
	}

	public int getVcQosMbPri3() {
		return vcQosMbPri3;
	}

	public void setVcQosMbPri3(int vcQosMbPri3) {
		this.vcQosMbPri3 = vcQosMbPri3;
	}

	public int getVcQosMbPri4() {
		return vcQosMbPri4;
	}

	public void setVcQosMbPri4(int vcQosMbPri4) {
		this.vcQosMbPri4 = vcQosMbPri4;
	}

	/**
	 * Qos table calculation method
	 * @param streams
	 */
	public void stcQosCalculation(List<TrafficStream> streams) {
		for (TrafficStream trafficStream : streams) {
			if (trafficStream.getName().contains("QoS_Min_QoS_pri")) {
				pipeCatalogMaxQos += trafficStream.getBw();
			}
		}
		pipeCatalogMaxQos /= 4;

		vcCatalogQosMbPri1 = (int) (pipeCatalogMaxQos * 0.05f);
		vcCatalogQosMbPri2 = (int) (pipeCatalogMaxQos * 0.1f);
		vcCatalogQosMbPri3 = (int) (pipeCatalogMaxQos * 0.15f);
		vcCatalogQosMbPri4 = (int) (pipeCatalogMaxQos * 0.2f);

		vcQosMbPri1 = (int) (((pipeCatalogMaxQos
				- (double) (vcCatalogQosMbPri1 + vcCatalogQosMbPri2 + vcCatalogQosMbPri3 + vcCatalogQosMbPri4)) * 0.1
				+ vcCatalogQosMbPri1) * 2);
		vcQosMbPri2 = (int) (((pipeCatalogMaxQos
				- (double) (vcCatalogQosMbPri1 + vcCatalogQosMbPri2 + vcCatalogQosMbPri3 + vcCatalogQosMbPri4)) * 0.2
				+ vcCatalogQosMbPri2) * 2);
		vcQosMbPri3 = (int) (((pipeCatalogMaxQos
				- (double) (vcCatalogQosMbPri1 + vcCatalogQosMbPri2 + vcCatalogQosMbPri3 + vcCatalogQosMbPri4)) * 0.3
				+ vcCatalogQosMbPri3) * 2);
		vcQosMbPri4 = (int) (((pipeCatalogMaxQos
				- (double) (vcCatalogQosMbPri1 + vcCatalogQosMbPri2 + vcCatalogQosMbPri3 + vcCatalogQosMbPri4)) * 0.4
				+ vcCatalogQosMbPri4) * 2);

	}

	@Override
	public String toString() {
		return "QosTable [pipeCatalogMaxQos=" + pipeCatalogMaxQos + ", vcCatalogQosMbPri1=" + vcCatalogQosMbPri1
				+ ", vcCatalogQosMbPri2=" + vcCatalogQosMbPri2 + ", vcCatalogQosMbPri3=" + vcCatalogQosMbPri3
				+ ", vcCatalogQosMbPri4=" + vcCatalogQosMbPri4 + ", pipeMaxQos=" + pipeMaxQos + ", vcQosMbPri1="
				+ vcQosMbPri1 + ", vcQosMbPri2=" + vcQosMbPri2 + ", vcQosMbPri3=" + vcQosMbPri3 + ", vcQosMbPri4="
				+ vcQosMbPri4 + "]";
	}

	public void print() {
		System.out.println();
		System.out.println("--QoS Catalogs-- ");
		System.out.println("pipeMaxQos = " + pipeCatalogMaxQos);
		System.out.println("vcQosMbPri1 = " + vcCatalogQosMbPri1);
		System.out.println("vcQosMbPri2 = " + vcCatalogQosMbPri2);
		System.out.println("vcQosMbPri3 = " + vcCatalogQosMbPri3);
		System.out.println("vcQosMbPri4 = " + vcCatalogQosMbPri4);
		System.out.println();

	}
}
