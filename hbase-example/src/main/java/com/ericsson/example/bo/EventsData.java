package com.ericsson.example.bo;

public class EventsData {
	
	private String moid;
	private String countertime;
	private String nodename;
	private String pmPdcpVolDlDrbTransUm;
	
	public String getMoid() {
		return moid;
	}
	public void setMoid(String moid) {
		this.moid = moid;
	}
	public String getCountertime() {
		return countertime;
	}
	public void setCountertime(String countertime) {
		this.countertime = countertime;
	}
	public String getNodename() {
		return nodename;
	}
	public void setNodename(String nodename) {
		this.nodename = nodename;
	}
	public String getPmPdcpVolDlDrbTransUm() {
		return pmPdcpVolDlDrbTransUm;
	}
	public void setPmPdcpVolDlDrbTransUm(String pmPdcpVolDlDrbTransUm) {
		this.pmPdcpVolDlDrbTransUm = pmPdcpVolDlDrbTransUm;
	}
	public String getPmSchedActivityCellDl() {
		return pmSchedActivityCellDl;
	}
	public void setPmSchedActivityCellDl(String pmSchedActivityCellDl) {
		this.pmSchedActivityCellDl = pmSchedActivityCellDl;
	}
	public String getPmUeThpTimeDl() {
		return pmUeThpTimeDl;
	}
	public void setPmUeThpTimeDl(String pmUeThpTimeDl) {
		this.pmUeThpTimeDl = pmUeThpTimeDl;
	}
	private String pmSchedActivityCellDl;
	private String pmUeThpTimeDl;

}
