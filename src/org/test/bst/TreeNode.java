package org.test.bst;

public class TreeNode <T extends Comparable<T>> {
	
	public T data;
	public TreeNode<T> left;
	public TreeNode<T> right;
	
	public TreeNode (T data) {
		this(data, null, null);
	}
	
	public TreeNode (T data, TreeNode<T> left, TreeNode<T> right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
}
