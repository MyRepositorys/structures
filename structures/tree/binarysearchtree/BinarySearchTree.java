package qiu.tree.binarysearchtree;
/**
 * 2019-7-6
 * @author Qiu2176
 * �������������Ҫ��ʵ�־��� insert,delete,query
 *��ν�Ķ���,����һ���ڵ����ֻ�������ӽڵ㣬��Ϊ����
 *��ν�Ĳ���,���Ǹ�����Ҫ����  �ýڵ�����ӽڵ��key < �ڵ��key < �ýڵ�����ӽڵ��key
 *�ڵ��key ��Ψһ��
 */
public class BinarySearchTree {

	/**
	 * ��̬�ڲ���,�����ڵ���Ϣ��
	 */
	private static class Node{
		
		/**
		 * ��ǰ�ڵ�ĸ��ڵ�,����������ڽڵ�ɾ����ʵ��������һ��
		 */
		private Node parent;
		
		/**
		 * ���ӽڵ�
		 */
		private Node leftChild;
		
		/**
		 * ���ӽڵ�
		 */
		private Node rightChild;
		
		/**
		 * �ڵ��key,�������нڵ����֯���ݵľ��Ǹ����ڵ��key,�������������У�key��Ψһ��
		 */
		private int key;
		/**
		 * ��һ��������֯�ɶ����������Ŀ�ľ����ܿ��ٶ�λ�����ݣ�
		 * ��������������str����,
		 * ��ʵ���оͲ���value ���и�ֵ��
		 */
		
		private String value;
		
		public Node(int key) {
			this.key=key;
		}
	}
	/**
	 * ���ڵ�
	 */
	private Node root;
	
	
	/**
	 * ��ʼ�����ڵ�
	 * @param key	�ڵ��keyֵ,�ڵ��keyֵ��Ψһ��
	 */
	public BinarySearchTree(int key) {
		root=new Node(key);
	}
	
	
	/**
	 * �����в�������,��ô�������ݣ�����ʲô�ط�?
	 * ������ݵ��Ƕ��������������,����һ���ڵ��key���������ӽڵ��key����С�������ӽڵ��key
	 * @param key	�½ڵ��key
	 * �����Ǹ��ݴ�����½ڵ��keyֵ�ڵ�ǰ���н��бȶԣ��ҵ�һ���ڵ�����ӽڵ�������ӽڵ��ʺϲ����½ڵ�,
	 * �Ҹ����ӽڵ�/���ӽڵ�=NULL
	 * ��ν���ʺϵĵط����ǲ����½ڵ��,��Ȼ������������������
	 */
	public void insertNodeWithKey(int key) {
		insertNode(root,new Node(key));
	}
	/**
	 * ���õ��ǵ����ķ�ʽ
	 * @param currentNode	�����еĵ�ǰ�ڵ�,���ǿ��ܱ������½ڵ�Ľڵ�
	 * @param newNode		��������½ڵ�
	 */
	private void insertNode(Node currentNode,Node newNode) {
		if(newNode.key > currentNode.key)
			if(currentNode.rightChild!=null)
				insertNode(currentNode.rightChild, newNode);
			else{
				currentNode.rightChild=newNode;
				newNode.parent=currentNode;
				return;
			}
		if(newNode.key < currentNode.key)
			if(currentNode.leftChild!=null)
				insertNode(currentNode.leftChild, newNode);
			else {
				currentNode.leftChild=newNode;
				newNode.parent=currentNode;
				return;
			}
	}
	
	
	/**
	 * ɾ��ָ���ڵ�,��Ҫ���ýڵ���ӽڵ���ڸ��ڵ���
	 * @param key	���ݸò���,ɾ��ָ���Ľڵ�
	 * �����Ƕ�λ����ɾ���ڵ�;���ǻ�ȡ�ýڵ������
	 * ����Ƿ�����������ýڵ�����ӽڵ�/���ӽڵ���ڸýڵ�ĸ��ڵ�����ӽڵ�/���ӽڵ�
	 * ��ϸ�ķ�һ�£��� (���ӽڵ� & ���ӽڵ�)!=NULL,�Ǿ����ӽڵ���ڱ�ɾ���ڵ��λ���ϣ�
	 * ���ýڵ�����ӽڵ�������ӽڵ�������ӽڵ�
	 * �� ����ýڵ� (���ӽڵ� | ���ӽڵ�)!=NULL����ô�ͽ�ʣ�µĽڵ�ֱ�ӹ��ڱ�ɾ���ڵ��λ��
	 * �� ����ýڵ�û���κ��ӽڵ㣬��ô��ʲôҲ����
	 */
	public void delNodeByKey(int key){
		Node beingDelNode=locateNodeByKey(key);
		Node parentNode=beingDelNode.parent;
		boolean isLeftChild=(parentNode.leftChild==beingDelNode) ? true:false;
		/*
		 * ��һ�����:�ýڵ�û���ӽڵ�
		 */
		if(beingDelNode.leftChild==null && beingDelNode.rightChild ==null) {
			if(isLeftChild)
				parentNode.leftChild=null;
			else
				parentNode.rightChild=null;
			return;
		}
		
		/*
		 * ���������:�ýڵ�������ӽڵ�,�������ӽڵ�
		 */
		if(beingDelNode.leftChild !=null && beingDelNode.rightChild!=null) {
			if(isLeftChild) 
				parentNode.leftChild=beingDelNode.leftChild;
			else 
				parentNode.rightChild=beingDelNode.leftChild;
			//����֮���������˫���,����beingDelNode.leftChild.parent ҲҪ�޸�
			beingDelNode.leftChild.parent=parentNode;
			
			Node rightMost=beingDelNode.leftChild.rightChild;
			
			Node tmp=rightMost.rightChild;
			while(tmp!=null) {
				rightMost=tmp;
				tmp=tmp.rightChild;
			}
			rightMost.rightChild=beingDelNode.rightChild;
			beingDelNode.rightChild.parent=rightMost;
			return;
		}
		/*
		 * �ڶ������:�ýڵ�ֻ�����ӽڵ�������ӽڵ�
		 */
		Node onlyChild=null;
		if(beingDelNode.leftChild==null)
			onlyChild=beingDelNode.rightChild;
		else
			onlyChild=beingDelNode.leftChild;
		if(isLeftChild)
			parentNode.leftChild=onlyChild;
		else
			parentNode.rightChild=onlyChild;
		onlyChild.parent=parentNode;
	}
	
	/**
	 * ����ָ����key����λ�ڵ�,
	 * @param key	ƥ�������,��Ϊ������������ÿ���ڵ��key ����Ψһ��
	 * @return	���ƥ�䵽�ڵ��򷵻ؽڵ������,���򷵻�NULL
	 */
	private Node locateNodeByKey(int key){
		try {
			return locateNodeBykey(root,key);
		} catch (Exception e) {
		}
		return null;
	}
	
	/**
	 * ʹ�õ������в���
	 * @param currentNode	��ǰ�������Ľڵ�
	 * @param key			ƥ��ڵ������
	 * @return				���ƥ�䵽�ڵ��򷵻ؽڵ������,���򷵻�NULL
	 * @throws Exception	�ڵ�ƥ��ʧ�� 
	 */
	private Node locateNodeBykey(Node currentNode,int key) throws Exception {
		if(currentNode==null) {
			throw new Exception("*******136:locateNodeBykey(Node currentNode,int key) ƥ��ʧ��*******");
		}
		if(currentNode.key==key)
			return currentNode;
		if(key>currentNode.key)
			return locateNodeBykey(currentNode.rightChild, key);
		else
			return locateNodeBykey(currentNode.leftChild, key);
	}
	
	/**
	 * ���⹫���ĸ���key ����ƥ��Ľڵ�
	 * @param key	ƥ�������
	 * @return		ƥ�䵽�򷵻ؽڵ�����,���򷵻�NULL
	 */
	public Node getNode(int key) {
		return locateNodeByKey(key);
	}
	
	/*
	 * ��ν��ǰ�������������һ���ڵ������ӽڵ�֮ǰ������
	 */
	public void prologue() {
		prologue(root);
	}
	public void prologue(Node currentNode) {
		if(currentNode==null)
			return;
		System.out.println(currentNode.key);
		prologue(currentNode.leftChild);
		prologue(currentNode.rightChild);
	}
	
	/*
	 * ��������ڶ������в�������,
	 * ��ν������������Ƕ���һ������ڵ�,�����ȱ��� �ýڵ�����Լ���-->��ǰ�ڵ�--> �ýڵ�����ӽڵ�
	 */
	public void mediumOrder() {
		mediumOrder(root);
	}
	
	private void mediumOrder(Node currentNode) {
		if(currentNode==null)
			return;
		if(hasLefiChild(currentNode))
			mediumOrder(currentNode.leftChild);
		System.out.println(currentNode.key);
		if(hasRightChild(currentNode))
			mediumOrder(currentNode.rightChild);
	}
	
	/**
	 * ��ν�ĺ��������������һ���ڵ�Ĵ���Ҫ�ڸýڵ���ӽڵ㴦�����ɺ����
	 */
	public void postOrder() {
		postOrder(root);
	}
	private void postOrder(Node node) {
		if(node==null)
			return;
		postOrder(node.leftChild);
		postOrder(node.rightChild);
		System.out.println(node.key);
	}
	
	
	/*
	 * ��������,�ж�һ���ڵ��Ƿ������ӽڵ�
	 */
	private boolean hasLefiChild(Node node) {
		return node.leftChild!=null;
	}
	/*
	 * ��������,�ж�һ���ڵ��Ƿ������ӽڵ�
	 */
	private boolean hasRightChild(Node node) {
		return node.rightChild!=null;
	}
}
