package com.cwb.shiro.model;

import java.util.List;

public class Power {
	private int  id;
	
	private String text;
	
	private String url;
	
	private int pid;
	
	private State state;
	
	private List<Power> nodes;
	
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public List<Power> getNodes() {
		return nodes;
	}

	public void setNodes(List<Power> nodes) {
		this.nodes = nodes;
	}


}

