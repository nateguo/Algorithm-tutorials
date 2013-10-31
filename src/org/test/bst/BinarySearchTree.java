package org.test.bst;

public class BinarySearchTree<T extends Comparable<T>> {

	private TreeNode<T> overAllRoot;

	public BinarySearchTree() {
		overAllRoot = null;
	}

	public BinarySearchTree(TreeNode<T> overAllRoot) {
		this.overAllRoot = overAllRoot;
	}

	public void print() {
		print(overAllRoot);
		System.out.println();
		
	}
	
	public void print(TreeNode<T> root) {
		if(root != null) {
			print(root.left);
			System.out.print(root.data + "  ");
			print(root.right);
		}
	}
	
	public boolean contains(T target) {
		return contains(overAllRoot, target);
	}
	
	private boolean contains(TreeNode<T> root, T target){
		if (root == null) {
			return false;
		} else if (root.data.compareTo(target) < 0) {
			return contains(root.right, target);
		} else if (root.data.compareTo(target) > 0) {
			return contains(root.left, target);
		} else {
			return true;
		}
		
	}
	
	public void add (T value) {
		overAllRoot = add(overAllRoot, value);
	}
	
	private TreeNode<T> add(TreeNode<T> root, T value) {
		if(root == null){
			root = new TreeNode<T>(value);
		}else if (root.data.compareTo(value) < 0) {
			root.right = add(root.right, value);
		}else if (root.data.compareTo(value) > 0) {
			root.left = add(root.left, value);
		}
		
		return root;
			
	}
	
	public T getMax() {
		TreeNode<T> maxNode = getMax(overAllRoot);
		return maxNode.data;
	}
	
	private TreeNode<T> getMax(TreeNode<T> root) {
		if(root != null) {
			while(root.right != null)
				root = root.right;
		}
		
		return root;
	}
	
	public T getMin() {
		TreeNode<T> minNode = getMin(overAllRoot);
		return minNode.data;
	}
	
	private TreeNode<T> getMin(TreeNode<T> root) {
		if(root != null) {
			while(root.left != null)
				root = root.left;
		}
		
		return root;
	}
	
	public int getDepth() {
		if(overAllRoot == null)
			return -1;
		else {
			TreeNode<T> depth = overAllRoot;
			return getDepth(depth);
		}
	}
	
	private int getDepth(TreeNode<T> root) {
		int left =-1, right = -1;
		if(root.left != null)
			left = getDepth(root.left);
		if(root.right != null)
			right = getDepth(root.right);
		return left > right ? (left + 1) : (right + 1);
	}
	
	public int getNumLeaves(){
		if(overAllRoot == null) {
			return 0;
		}
		int leaf = 0;
		TreeNode<T> leaves = overAllRoot;
		return getNumLeaves(leaves, leaf);
	}
	
	private int getNumLeaves(TreeNode<T> root, int leaf) {
		if(root == null)
			return 0;
		if(root.left == null && root.right == null)
			return 1;
		else{
			return getNumLeaves(root.left, leaf) + getNumLeaves(root.right, leaf);
		}
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("{");
		toString(overAllRoot, s);
		s.append("}");
		return s.toString();
	}
	
	private StringBuilder toString(TreeNode<T> root, StringBuilder s) {
		if(root != null){
			toString(root.left, s);
			s.append(root.data + "  ");
			toString(root.right, s);
		}
		return s;
	}
	
	public void printSideways() {
		printSideways(overAllRoot, "");
	}

	public void printSideways(TreeNode<T> root, String indent) {
		if(root != null) {
			printSideways(root.left, indent + "   ");
			System.out.println(indent + root.data);
			printSideways(root.right, indent + "   ");
		}
	}
}
