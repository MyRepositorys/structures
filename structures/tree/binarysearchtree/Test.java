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
		 * �������
		 */
		/* bst.mediumOrder(); */
		
		/*
		 * ǰ�����
		 */
		/* bst.prologue(); */
		
		/*
		 * �������
		 */
		/* bst.postOrder(); */
		
	
		/*
		 *	ɾ���ĵ�һ����� 
		 */
		/*
		 * bst.delNodeByKey(50); bst.mediumOrder();
		 */
		
		
		
		/*
		 *  ɾ���ĵڶ������
		 */
		/*
		 * bst.delNodeByKey(35); bst.mediumOrder();
		 */
		
		
		/*
		 *  ɾ���ĵ��������
		 */
		
		bst.delNodeByKey(25);
		bst.mediumOrder();
		
		
	}
}
