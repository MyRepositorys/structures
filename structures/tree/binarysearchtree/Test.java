package qiu.tree.binarysearchtree;

public class Test {

	public static void main(String[] args) {
		BinarySearchTree bst=new BinarySearchTree(39);
		
		bst.insertNodeWithKey(25);
		bst.insertNodeWithKey(98);
		bst.insertNodeWithKey(9);
		bst.insertNodeWithKey(2);
		bst.insertNodeWithKey(15);
		bst.insertNodeWithKey(1);
		bst.insertNodeWithKey(7);
		bst.insertNodeWithKey(30);
		bst.insertNodeWithKey(29);
		bst.insertNodeWithKey(35);
		bst.insertNodeWithKey(38);
		bst.insertNodeWithKey(50);
		bst.insertNodeWithKey(120);
		bst.insertNodeWithKey(100);
		bst.insertNodeWithKey(130);
		
		/*
		 * 中序遍历
		 */
		/* bst.mediumOrder(); */
		
		/*
		 * 前序遍历
		 */
		/* bst.prologue(); */
		
		/*
		 * 后序遍历
		 */
		/* bst.postOrder(); */
		
	
		/*
		 *	删除的第一种情况 
		 */
		/*
		 * bst.delNodeByKey(50); bst.mediumOrder();
		 */
		
		
		
		/*
		 *  删除的第二种情况
		 */
		/*
		 * bst.delNodeByKey(35); bst.mediumOrder();
		 */
		
		
		/*
		 *  删除的第三种情况
		 */
		
		bst.delNodeByKey(25);
		bst.mediumOrder();
		
		
	}
}
