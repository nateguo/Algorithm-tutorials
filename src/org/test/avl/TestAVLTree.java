package org.test.avl;

public class TestAVLTree {

	public static void main(String[] args) {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		int[] elements = {2,1,4,5,9,3,6,7};
		for(int i=0; i< elements.length; i++){
			tree.insert(elements[i]);
		}
		
		System.out.println ("Infix Traversal:");
		System.out.println(tree.serializeInfix());
		System.out.println ("Prefix Traversal:");
		System.out.println(tree.serializePrefix());
	}

}
