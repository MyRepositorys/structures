package qiu.tree.multiforktree;

import java.util.ArrayList;
import java.util.List;

public class Node {


	private String name;
	
	private Node parentNode;	//当前节点的父节点
	
	private List<Node> childsNode=new ArrayList<Node>();	//当前节点的子节点
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Node getParentNode() {
		return parentNode;
	}


	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}


	public List<Node> getChildsNode() {
		return childsNode;
	}


	public void addChildNode(Node childNode) {
		this.childsNode.add(childNode);
	}


	public Node(String name) {
		this.name=name;
	}
}
