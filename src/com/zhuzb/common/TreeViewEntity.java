package com.zhuzb.common;

import java.util.ArrayList;
import java.util.List;

/**
 * bootstrap-treeview返回的实体
 * @author zhuzb
 *
 */
public class TreeViewEntity {
	private int nodeid;
	private String text;
	private int parentId;
	private boolean selectable;
	private List<TreeViewEntity> nodes = new ArrayList<TreeViewEntity>();
	private TreeViewState state;
	public int getNodeid() {
		return nodeid;
	}
	public void setNodeid(int nodeid) {
		this.nodeid = nodeid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public boolean isSelectable() {
		return selectable;
	}
	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}
	public List<TreeViewEntity> getNodes() {
		return nodes;
	}
	public void setNodes(List<TreeViewEntity> nodes) {
		this.nodes = nodes;
	}
	public TreeViewState getState() {
		return state;
	}
	public void setState(TreeViewState state) {
		this.state = state;
	}

}
