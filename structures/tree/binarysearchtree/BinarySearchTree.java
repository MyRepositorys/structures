package qiu.tree.binarysearchtree;
/**
 * 2019-7-6
 * @author Qiu2176
 * 二叉查找树中主要的实现就是 insert,delete,query
 *所谓的二叉,就是一个节点最多只有两个子节点，分为左右
 *所谓的查找,就是该树需要满足  该节点的左子节点的key < 节点的key < 该节点的右子节点的key
 *节点的key 是唯一的
 */
public class BinarySearchTree {

	/**
	 * 静态内部类,描述节点信息的
	 */
	private static class Node{
		
		/**
		 * 当前节点的父节点,有这个属性在节点删除的实现中容易一点
		 */
		private Node parent;
		
		/**
		 * 左子节点
		 */
		private Node leftChild;
		
		/**
		 * 右子节点
		 */
		private Node rightChild;
		
		/**
		 * 节点的key,二叉树中节点的组织依据的就是各个节点的key,在整个二叉树中，key是唯一的
		 */
		private int key;
		/**
		 * 把一组数据组织成二叉查找树的目的就是能快速定位到数据，
		 * 这个数据用下面的str代替,
		 * 在实现中就不对value 进行赋值了
		 */
		
		private String value;
		
		public Node(int key) {
			this.key=key;
		}
	}
	/**
	 * 根节点
	 */
	private Node root;
	
	
	/**
	 * 初始化根节点
	 * @param key	节点的key值,节点的key值是唯一的
	 */
	public BinarySearchTree(int key) {
		root=new Node(key);
	}
	
	
	/**
	 * 向树中插入数据,怎么插入数据，插在什么地方?
	 * 这个依据的是二叉查找树的特性,任意一个节点的key大于其左子节点的key，且小于其右子节点的key
	 * @param key	新节点的key
	 * 首先是根据传入的新节点的key值在当前树中进行比对，找到一个节点的左子节点或者右子节点适合插入新节点,
	 * 且该左子节点/右子节点=NULL
	 * 所谓的适合的地方就是插入新节点后,任然满足二叉查找树的特性
	 */
	public void insertNodeWithKey(int key) {
		insertNode(root,new Node(key));
	}
	/**
	 * 采用的是迭代的方式
	 * @param currentNode	迭代中的当前节点,就是可能被插入新节点的节点
	 * @param newNode		待插入的新节点
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
	 * 删除指定节点,还要将该节点的子节点挂在父节点上
	 * @param key	根据该参数,删除指定的节点
	 * 首先是定位到待删除节点;就是获取该节点的引用
	 * 其次是分情况决定将该节点的左子节点/右子节点挂在该节点的父节点的左子节点/右子节点
	 * 仔细的分一下，① (左子节点 & 右子节点)!=NULL,那就左子节点挂在被删除节点的位置上，
	 * 将该节点的右子节点挂在左子节点的最左子节点
	 * ② 如果该节点 (左子节点 | 右子节点)!=NULL，那么就将剩下的节点直接挂在被删除节点的位置
	 * ③ 如果该节点没有任何子节点，那么就什么也不做
	 */
	public void delNodeByKey(int key){
		Node beingDelNode=locateNodeByKey(key);
		Node parentNode=beingDelNode.parent;
		boolean isLeftChild=(parentNode.leftChild==beingDelNode) ? true:false;
		/*
		 * 第一种情况:该节点没有子节点
		 */
		if(beingDelNode.leftChild==null && beingDelNode.rightChild ==null) {
			if(isLeftChild)
				parentNode.leftChild=null;
			else
				parentNode.rightChild=null;
			return;
		}
		
		/*
		 * 第三种情况:该节点既有左子节点,又有右子节点
		 */
		if(beingDelNode.leftChild !=null && beingDelNode.rightChild!=null) {
			if(isLeftChild) 
				parentNode.leftChild=beingDelNode.leftChild;
			else 
				parentNode.rightChild=beingDelNode.leftChild;
			//父子之间的引用是双向的,所以beingDelNode.leftChild.parent 也要修改
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
		 * 第二种情况:该节点只有左子节点或者右子节点
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
	 * 根据指定的key，定位节点,
	 * @param key	匹配的依据,因为二叉搜索树中每个节点的key 都是唯一的
	 * @return	如果匹配到节点则返回节点的引用,否则返回NULL
	 */
	private Node locateNodeByKey(int key){
		try {
			return locateNodeBykey(root,key);
		} catch (Exception e) {
		}
		return null;
	}
	
	/**
	 * 使用迭代进行查找
	 * @param currentNode	当前迭代到的节点
	 * @param key			匹配节点的依据
	 * @return				如果匹配到节点则返回节点的引用,否则返回NULL
	 * @throws Exception	节点匹配失败 
	 */
	private Node locateNodeBykey(Node currentNode,int key) throws Exception {
		if(currentNode==null) {
			throw new Exception("*******136:locateNodeBykey(Node currentNode,int key) 匹配失败*******");
		}
		if(currentNode.key==key)
			return currentNode;
		if(key>currentNode.key)
			return locateNodeBykey(currentNode.rightChild, key);
		else
			return locateNodeBykey(currentNode.leftChild, key);
	}
	
	/**
	 * 对外公开的根据key 返回匹配的节点
	 * @param key	匹配的依据
	 * @return		匹配到则返回节点引用,否则返回NULL
	 */
	public Node getNode(int key) {
		return locateNodeByKey(key);
	}
	
	/*
	 * 所谓的前序遍历就是任意一个节点在其子节点之前被处理
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
	 * 中序遍历在二叉树中才有意义,
	 * 所谓的中序遍历就是对于一个任意节点,都是先遍历 该节点的左自己点-->当前节点--> 该节点的右子节点
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
	 * 所谓的后序遍历就是任意一个节点的处理要在该节点的子节点处理后完成后进行
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
	 * 辅助方法,判断一个节点是否有左子节点
	 */
	private boolean hasLefiChild(Node node) {
		return node.leftChild!=null;
	}
	/*
	 * 辅助方法,判断一个节点是否有右子节点
	 */
	private boolean hasRightChild(Node node) {
		return node.rightChild!=null;
	}
}
