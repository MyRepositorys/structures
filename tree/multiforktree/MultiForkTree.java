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
	 * ��ʼ�����ڵ�
	 * @param name	���ڵ��key
	 */
	public MultiForkTree(String name) {
		rootNode=new Node(name);
		
	}
	
	/**
	 * ����ջ�ڶ�����в���ָ��key �Ľڵ�,�����ýڵ㷵��
	 * @param currentNode	���ڵ�
	 * @param name			ָ���Ľڵ��key
	 * @return				���ҵ��Ľڵ�����ã�û�ҵ�����NULL
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
	 * ���ݽڵ��key,���ظýڵ������
	 * @param name	ָ���ڵ��key
	 * @return		���� @param name �ҵ��ڵ�Ӧ�ã�������
	 */
	public Node locateNode(String name) {
			return locatedNode(rootNode,name);
	}
	
	/**
	 * ��ָ���Ľڵ��в���һ���ӽڵ�
	 * @param parentNode	ָ���Ľڵ�
	 * @param childNode		��������ӽڵ�
	 */
	private void addNode(Node parentNode,String childNode) {
		
		Node theChildNode=new Node(childNode);	//��ʼ���ӽڵ�����

		theChildNode.setParentNode(parentNode);	//ָ��������ӽڵ�ĸ��ڵ�
		
		parentNode.addChildNode(theChildNode);	//�����ӽڵ�
		
	}
	
	/**
	 *��һ���ڵ��в������ӽڵ� 
	 * @param args	args[0] �������ӽڵ�Ľڵ�,	args[1+],��������ӽڵ�
	 */
	private void addNodes(String ...args) {
		
		Node parentNode=locateNode(args[0]);
		for(int i=1;i<args.length;i++)
			addNode(parentNode,args[i]);
	}
	
	/**
	 * ��һ���ڵ��в���һ�����߶���ӽڵ�
	 * @param args	args[0] �������ӽڵ�Ľڵ�,	args[1+],��������ӽڵ�
	 */
	public void addNodeOrNodes(String ...args) {
		if(args==null || args.length==0 || args.length < 2) {
			System.out.println("****���ݲ����쳣******");
			return;
		}
		Node parentNode=locateNode(args[0]);
		if(args.length==2)
			addNode(parentNode,args[1]);
		else
			addNodes(args);
	}
		
	/**
	 * ǰ�����
	 * ����û�и�ʽ����ӡ������нڵ�ĸ������ݣ���ô�Ϳ���������ʽ����ӡ��
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
	 * ������� 
	 * ����û�и�ʽ����ӡ������нڵ�ĸ������ݣ���ô�Ϳ���������ʽ����ӡ��
	 * ��ν�ĺ�����������ȱ���Ҷ�ڵ㣬Ȼ������ӽڵ�
	 * ʹ�õݹ����ʵ��
	 */
	public void postOrder(Node currentNode) {
		if(!currentNode.getChildsNode().isEmpty())
			for(Node node:currentNode.getChildsNode())
				postOrder(node);
		System.out.println(currentNode.getName());
	}
	
}
