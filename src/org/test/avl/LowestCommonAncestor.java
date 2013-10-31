package org.test.avl;

import org.test.avl.AVLTree.AVLNode;

public class LowestCommonAncestor {
	
	
	private static <T extends Comparable<? super T>> AVLNode<T> lca(AVLNode<T> node, T u, T v, int[] flag) {
		if(node.data.equals(u) && node.data.equals(v)) {
			return node;
		}
		int[] leftFlag, rightFlag;
		leftFlag = rightFlag = new int[]{0};
		if (node.left != null) {
			AVLNode<T> leftAncestor = lca(node.left, u, v, leftFlag);
			if (leftAncestor != null)
				return leftAncestor;
		}
		
		if(node.right != null) {
			AVLNode<T> rightAncestor = lca(node.right, u, v, rightFlag);
			if(rightAncestor != null)
				return rightAncestor;
		}
		
		if(node.data.equals(u))
			flag[0] |= 1;
		if(node.data.equals(v))
			flag[0] |= 2;
		flag[0] |= leftFlag[0];
		flag[0] |= rightFlag[0];
		if(flag[0] == 3)
			return node;
		
		return null;
	}
	
	public static <T extends Comparable<? super T>> AVLNode<T> lca(AVLTree<T> tree, T u, T v) {
		int[] flag = {0};
		return lca(tree.getRoot(), u, v, flag);
	}
	
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
		
		System.out.println("lca:  " + lca(tree, 1, 3).data);
	}
}
