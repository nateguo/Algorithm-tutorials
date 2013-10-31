package org.test.avl;

public class AVLTree <T extends Comparable<? super T>> {
	
	private AVLNode<T> root;
	
	private int countInsertions;
	private int countSingleRotations;
	private int countDoubleRotations;
	
	public AVLTree() {
		root = null;
		countInsertions = 0;
		countSingleRotations = 0;
		countDoubleRotations = 0;
		
	}
	
	public int height(AVLNode<T> node) {
		return node == null ? -1 : node.height;
	}
	
	public int max(int a, int b) {
		return a >= b ? a : b;
	}
	
	public boolean insert(T data) {
		try {
			root = insert(data, root);
			countInsertions++;
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	protected AVLNode<T> insert(T data, AVLNode<T> t) throws Exception {
		if(t == null)
			t = new AVLNode<T>(data);
		else if(data.compareTo(t.data) < 0) {
			t.left = insert(data, t.left);
			
			if(height(t.left) - height(t.right) == 2){
				if(data.compareTo(t.left.data) < 0){
					t = rotateWithLeftChild(t);
					countSingleRotations++;
				}else {
					t = doubleRotateWithleftChild(t);
					countDoubleRotations++;
				}
			}
			
		}else if(data.compareTo(t.data) > 0) {
			t.right = insert(data, t.right);

			if (height(t.right) - height(t.left) == 2) {
				if (data.compareTo(t.right.data) > 0) {
					t = rotateWithRightChild(t);
					countSingleRotations++;
				} else {
					t = doubleRotateWithRightChild(t);
					countDoubleRotations++;
				}
			}
			
		}else {
			throw new Exception("Attempting to insert to duplicate value");
		}
		
		t.height = max(height(t.left), height(t.right)) + 1;
		return t;
	}
	
	protected AVLNode<T> rotateWithLeftChild(AVLNode<T> root) {
		AVLNode<T> newRoot = root.left;
		root.left = newRoot.right;
		newRoot.right = root;
		
		root.height = max(height(root.left), height(root.right)) + 1;
		newRoot.height = max(height(newRoot.left), root.height) + 1;
		
		return newRoot;
	}
	
	protected AVLNode<T> rotateWithRightChild(AVLNode<T> root) {
		AVLNode<T> newRoot = root.right;
		root.right = newRoot.left;
		newRoot.left = root;
		
		root.height = max(height(root.left), height(root.right)) + 1;
		newRoot.height = max(height(newRoot.right), root.height) + 1;
		return newRoot;
	}
	
	protected AVLNode<T> doubleRotateWithleftChild(AVLNode<T> root) {
		root.left = rotateWithRightChild(root.left);
		return rotateWithLeftChild(root);
	}
	
	protected AVLNode<T> doubleRotateWithRightChild(AVLNode<T> root) {
		root.right = rotateWithLeftChild(root.right);
		return rotateWithRightChild(root);
		
	}
	
	public void clear() {
		root = null;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public T findMax() {
		if(isEmpty())
			return null;
		else
			return findMax(root).data;
	}
	
	public T findMin() {
		if(isEmpty())
			return null;
		else
			return findMin(root).data;
	}
	
	private AVLNode<T> findMin(AVLNode<T> root) {
		if(root.left == null)
			return root;
		while(root.left != null) {
			root = root.left;
		}
		return root;
			
	}
	
	private AVLNode<T> findMax(AVLNode<T> root) {
		if(root.right == null)
			return root;
		while(root.right != null) {
			root = root.right;
		}
		return root;
	}
	
	public void remove(T data) {
		root = remove(data, root);
	}
	
	public AVLNode<T> remove(T x, AVLNode<T> t) {
		if(t == null) {
			System.out.println("Sorry but you're mistaken, " + t + " doesn't exist in this tree :)\n");
			return null;
		}
		System.out.println("Remove starts... " + t.data + " and " + x);
		
		if(x.compareTo(t.data) < 0) {
			t.left = remove(x, t.left);
			int le = t.left != null ? t.left.height : 0;
			
			if((t.right != null) && (t.right.height - le >= 2)) {
				int rightHeight = t.right.right != null ? t.right.right.height : 0;
				int leftHeight = t.right.left != null ? t.right.left.height : 0;
				if(rightHeight >= leftHeight)
					t = rotateWithLeftChild(t);
				else
					t = doubleRotateWithRightChild(t);
				
			}
			
		}else if(x.compareTo(t.data) > 0) {
			t.right = remove(x, t.right);
			int ri = t.right != null ? t.right.height : 0;
			if((t.left != null) &&(t.left.height - ri >= 2)) {
				int leftHeight = t.left.left != null ? t.left.left.height : 0;
				int rightHeight = t.left.right != null ? t.left.right.height : 0;
				if(leftHeight >= rightHeight)
					t = rotateWithRightChild(t);
				else
					t = doubleRotateWithleftChild(t);
			}
			
		}else if(t.left != null) {
			t.data = findMax(t.left).data;
			remove(t.data, t.left);
			
			if((t.right != null) && (t.right.height - t.left.height >= 2)) {
				int rightHeight = t.right.right != null ? t.right.right.height : 0;
				int leftHeight = t.right.left != null ? t.right.left.height : 0;
				if(rightHeight >= leftHeight)
					t = rotateWithLeftChild(t);
				else
					t = doubleRotateWithRightChild(t);
				
			}
			
		}else {
			t = (t.left != null) ? t.left : t.right;
		}
		
		if(t != null) {
			int leftHeight = (t.left != null) ? t.left.height : 0;
			int rightHeight = (t.right != null) ? t.right.height : 0;
			t.height = max(leftHeight, rightHeight) + 1;
		}
		
		return t;
	}
	
	public boolean contain(T x) {
		return contain(x, root);
	}
	
	protected boolean contain(T x, AVLNode<T> t) {
		if(t == null)
			return false;
		else if(x.compareTo(t.data) < 0)
			return contain(x, t.left);
		else if (x.compareTo(t.data) > 0) {
			return contain(x, t.right);
		}
		return true;
	}
	
	public String serializeInfix() {
		StringBuilder str = new StringBuilder();
		serializeInfix(root, str, "   ");
		return str.toString();
	}
	
	protected void serializeInfix(AVLNode<T> t, StringBuilder str, String indent) {
		if(t == null)
			return;
		serializeInfix(t.left, str, indent);
		str.append(t.data.toString() + indent);
		serializeInfix(t.right, str, indent);
	}
	
	public String serializePrefix() {
		StringBuilder str = new StringBuilder();
		serializePrefix(root, str, "   ");
		return str.toString();
	}
	
	protected void serializePrefix(AVLNode<T> t, StringBuilder str, String indent) {
		if(t == null)
			return;
		str.append(t.data.toString() + indent);
		serializePrefix(t.left, str, indent);
		serializePrefix(t.right, str, indent);
	}
	
	public AVLNode<T> getRoot() {
		return root;
	}

	static class AVLNode<T> {
		protected T data;
		protected AVLNode<T> left;
		protected AVLNode<T> right;
		protected int height;
		
		public AVLNode(T data) {
			this(data, null, null);
		}
		
		public AVLNode(T data, AVLNode<T> left, AVLNode<T> right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

}
