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
		if(root == null){
			root = new RBNode<T>(item);
			root.setColor(Color.BLACK);
			return;
		}
		
		RBNode<T> parent = find(item);
		RBNode<T> current;
		if(parent.data.compareTo(item) > 0) {
			current = new RBNode<T>(item);
			parent.right = current;
			current.parent = parent;
			adjustColor(current);
		}else if(parent.data.compareTo(item) < 0) {
			current = new RBNode<T>(item);
			parent.left = current;
			current.parent = parent;
			adjustColor(current);
		}else {
			
		}
		
	}
	
	private RBNode<T> find(T item) {
		RBNode<T> current = root;
		while(true) {
			if(item.compareTo(current.data) > 0) {
				if(current.right != null)
					current = current.right;
				else
					return current;
			}else if(item.compareTo(current.data) < 0) {
				if(current.left != null)
					current = current.left;
				else 
					return current;
			}else {
				return current;
			}
		}
	}
	
	public boolean insertX(T item) {
		try {
			this.root = insertX(root, item);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	private RBNode<T> insertX(RBNode<T> root, T item) throws Exception {
		
		if(root == null) {
			root = new RBNode<T>(item);
			
			adjustColor(root);
		}else if(root.data.compareTo(item) > 0){
			root.left = insertX(root.left, item);
			
			adjustColor(root);
		}else if(root.data.compareTo(item) < 0) {
			root.right = insertX(root.right, item);
			
			adjustColor(root);
		}else {
			throw new Exception("Attempting to insert to duplicate value");
		}
		
		return root;
		
	}
	
	private void adjustColor(RBNode<T> node) {
		
		if(node == root || node.parent.getColor() == Color.BLACK) {
			return;
		}
		if((node.parent.getColor() == Color.RED) 
				&& (node.getUncle() != null) && (node.getUncle().getColor() == Color.RED)) {
				node.parent.setColor(Color.BLACK);
				node.getUncle().setColor(Color.BLACK);
				node.parent.parent.setColor(Color.RED);
				node = node.parent.parent;
				adjustColor(node);
		}
		if(!node.isLeftChild() && (node.parent.getColor() == Color.RED)
				&& (node.getUncle() != null) && (node.getUncle().getColor() == Color.BLACK)) {
			node = node.parent;
			rotateLeft(node);
			adjustColor(node);
		}
		if(node.isLeftChild() && (node.parent.getColor() == Color.RED)
				&& (node.getUncle() != null) && (node.getUncle().getColor() == Color.BLACK)) {
			node.parent.setColor(Color.BLACK);
			node.parent.parent.setColor(Color.RED);
			rotateRight(node.parent.parent);
			adjustColor(node);
		}
	}
	
	private void rotateLeft(RBNode<T> node) {
		node.parent.left = node.right;
		node.right = node.parent.left;
		node.parent.left = node;
	}
	
	private void rotateRight(RBNode<T> node) {
		node.parent.right = node.left;
		node.left = node.parent.right;
		node.parent.right = node;
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
	
	public String serializeInfix() {
		StringBuilder str = new StringBuilder();
		serializeInfix(root, str, "   ");
		return str.toString();
	}
	
	protected void serializeInfix(RBNode<T> t, StringBuilder str, String indent) {
		if(t == null)
			return;
		serializeInfix(t.left, str, indent);
		str.append(t.data.toString() + ":" + t.getColor() + indent);
		serializeInfix(t.right, str, indent);
	}
	
	public String serializePrefix() {
		StringBuilder str = new StringBuilder();
		serializePrefix(root, str, "   ");
		return str.toString();
	}
	
	protected void serializePrefix(RBNode<T> t, StringBuilder str, String indent) {
		if(t == null)
			return;
		str.append(t.data.toString() + ":" + t.getColor() + indent);
		serializePrefix(t.left, str, indent);
		serializePrefix(t.right, str, indent);
	}
	
}
