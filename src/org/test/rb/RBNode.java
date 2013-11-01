package org.test.rb;

public class RBNode<T> {
	public T data;
	public RBNode<T> left;
	public RBNode<T> right;
	public RBNode<T> parent;
	private Color color;

	public RBNode(T data) {
		this.data = data;
		this.left = null;
		this.right = null;
		this.parent = null;
		this.color = Color.RED;
	}

	public boolean isLeftChild() {
		if ((this.parent != null) && (this.parent.left == this))
			return true;
		return false;
	}

	public RBNode<T> getUncle() {
		if (isLeftChild())
			return this.parent.parent.right;
		else
			return this.parent.parent.left;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
