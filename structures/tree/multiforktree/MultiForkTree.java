package qiu.tree.multiforktree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class MultiForkTree {
	
	private Node rootNode;
	
	
	
	public Node getRootNode() {
		return rootNode;
	}

	public void setRootNode(Node rootNode) {
		this.rootNode = rootNode;
	}

	/**
	 * 初始化根节点
	 * @param name	根节点的key
	 */
	public MultiForkTree(String name) {
		rootNode=new Node(name);
		
	}
	
	/**
	 * 借助栈在多叉树中查找指定key 的节点,并将该节点返回
	 * @param currentNode	根节点
	 * @param name			指定的节点的key
	 * @return				查找到的节点的引用，没找到就是NULL
	 */
	public Node locatedNode(Node currentNode,String name) {
	
		Stack<Node> nodes=new Stack<Node>();
		nodes.push(currentNode);
		
		while(!nodes.isEmpty()) {
			Node tmp=nodes.pop();
			if(tmp.getName().equals(name))
				return tmp;
			if(tmp.getChildsNode().size()!=0)
				for(Node node:tmp.getChildsNode())
					nodes.push(node);
		}
		return null;
	}
	
	/**
	 * 根据节点的key,返回该节点的引用
	 * @param name	指定节点的key
	 * @return		根据 @param name 找到节点应用，并返回
	 */
	public Node locateNode(String name) {
			return locatedNode(rootNode,name);
	}
	
	/**
	 * 向指定的节点中插入一个子节点
	 * @param parentNode	指定的节点
	 * @param childNode		待插入的子节点
	 */
	private void addNode(Node parentNode,String childNode) {
		
		Node theChildNode=new Node(childNode);	//初始化子节点引用

		theChildNode.setParentNode(parentNode);	//指定游离的子节点的父节点
		
		parentNode.addChildNode(theChildNode);	//插入子节点
		
	}
	
	/**
	 *向一个节点中插入多个子节点 
	 * @param args	args[0] 待插入子节点的节点,	args[1+],待插入的子节点
	 */
	private void addNodes(String ...args) {
		
		Node parentNode=locateNode(args[0]);
		for(int i=1;i<args.length;i++)
			addNode(parentNode,args[i]);
	}
	
	/**
	 * 向一个节点中插入一个或者多个子节点
	 * @param args	args[0] 待插入子节点的节点,	args[1+],待插入的子节点
	 */
	public void addNodeOrNodes(String ...args) {
		if(args==null || args.length==0 || args.length < 2) {
			System.out.println("****数据插入异常******");
			return;
		}
		Node parentNode=locateNode(args[0]);
		if(args.length==2)
			addNode(parentNode,args[1]);
		else
			addNodes(args);
	}
		
	/**
	 * 前序遍历
	 * 但是没有格式化打印，如果有节点的告诉数据，那么就可以做到格式化打印了
	 */
	public void prologue() {
	
		Stack<Node> s=new Stack();
		s.push(rootNode);
		while(!s.isEmpty()) {
			Node currentNode=s.pop();
			System.out.println(currentNode.getName());
			if(!currentNode.getChildsNode().isEmpty())
				for(Node node:currentNode.getChildsNode())
					s.push(node);
		}
		
	}
	
	
	/**
	 * 后序遍历 
	 * 但是没有格式化打印，如果有节点的告诉数据，那么就可以做到格式化打印了
	 * 所谓的后序遍历就是先遍历叶节点，然后遍历子节点
	 * 使用递归进行实现
	 */
	public void postOrder(Node currentNode) {
		if(!currentNode.getChildsNode().isEmpty())
			for(Node node:currentNode.getChildsNode())
				postOrder(node);
		System.out.println(currentNode.getName());
	}
	
}
