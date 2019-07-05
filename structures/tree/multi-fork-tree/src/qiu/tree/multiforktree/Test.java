package qiu.tree.multiforktree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Test {

	public static void main(String[] args) {
		
		
		  MultiForkTree mt=new MultiForkTree("aa"); 
		  mt.addNodeOrNodes("aa","bb","cc");
		  mt.addNodeOrNodes("bb","aaa","adada","adsf");
		  mt.addNodeOrNodes("cc","11","22","33");
		  mt.addNodeOrNodes("11","44","55");
		 // mt.prologue();
		  mt.postOrder(mt.getRootNode());
	}
}
