package org.test.rb;

public class RBTree <T extends Comparable<? super T>>{
	
	private RBNode<T> root;
	
	public RBTree() {
		this.root = null;
	}
	
	public RBTree(T item) {
		insert(item);
	}
	
	public void insert(T item) {
		insert(root, item);
	}
	
	private RBNode<T> insert(RBNode<T> root, T item) {
		RBNode<T> node;
		if(root == null) {
			node = new RBNode<T>(item);
			root = node;
			return root;
		}
		
		if(root.data.compareTo(item) < 0){
			node = insert(root.left, item);
		}else if(root.data.compareTo(item) > 0) {
			node = insert(root.right, item);
		}else {
			node = root;
		}
		
		adjustColor(node);
		
		return node;
		
	}
	
	private void adjustColor(RBNode<T> node) {
		
		if(node == this.root) {
			this.root.setColor(Color.BLACK);
		}
		if(node.parent.getColor() == Color.BLACK) {
			
		}
		if(node.isLeftChild() && (node.parent.getColor() == Color.RED) 
				&& (node.getUncle().getColor() == Color.RED)) {
			node.parent.setColor(Color.BLACK);
			node.getUncle().setColor(Color.BLACK);
			node.parent.parent.setColor(Color.RED);
			node = node.parent.parent;
			adjustColor(node);
		}
		if(!node.isLeftChild() && (node.parent.getColor() == Color.RED)
				&& (node.getUncle().getColor() == Color.BLACK)) {
			node = node.parent;
			rotateLeft(node);
		}
		if(!node.isLeftChild() && (node.parent.getColor() == Color.RED)
				&& (node.getUncle().getColor() == Color.BLACK)) {
			node.parent.setColor(Color.BLACK);
			node.parent.parent.setColor(Color.RED);
			rotateRight(node.parent.parent);
			
		}
	}
	
	private void rotateLeft(RBNode<T> node) {
		node.parent.left = node.right;
	}
	
	private void rotateRight(RBNode<T> node) {
		
	}
	
	public boolean remove(T item) {
		return false;
	}
	
	public T findMax() {
		return null;
	}
	
	public T findMin() {
		return null;
	}
	
	public boolean contains(T item) {
		return false;
	}
	
}
