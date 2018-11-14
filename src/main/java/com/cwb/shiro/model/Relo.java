package com.cwb.shiro.model;

public class Relo {
    private Integer rid; 
	private String rname;
	private boolean state;
	
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	@Override
	public String toString() {
		return "Relo [rid=" + rid + ", rname=" + rname + ", state=" + state + "]";
	}
	
	
}    
