package com.cwb.shiro.model;

import java.util.List;

public class Tree {

	private Integer id;

	private String name;

	private Integer parentId;

	private List<Integer> childIds;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public List<Integer> getChildIds() {
		return childIds;
	}

	public void setChildIds(List<Integer> childIds) {
		this.childIds = childIds;
	}

	@Override
	public String toString() {
		return "Tree [id=" + id + ", name=" + name + ", parentId=" + parentId + ", childIds=" + childIds + "]";
	}
	
}
