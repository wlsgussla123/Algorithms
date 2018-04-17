package algo;

class BTreeNode {
	private int data;
	private BTreeNode left;
	private BTreeNode right;
	
	public BTreeNode(int data) {
		this.left = null;
		this.right = null;
		this.data = data;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public BTreeNode getLeft() {
		return left;
	}

	public void setLeft(BTreeNode left) {
		this.left = left;
	}

	public BTreeNode getRight() {
		return right;
	}

	public void setRight(BTreeNode right) {
		this.right = right;
	}
}

class BinaryTree {
	private BTreeNode root;
	
	public BinaryTree() {
		this.root = null;
	}
	
	public BTreeNode makeTree(BTreeNode left, int data, BTreeNode right) {
		root = new BTreeNode(data);
		root.setLeft(left);
		root.setRight(right);
		
		return root;
	}
	
	public void setLeftSubTree(BTreeNode child) {
		if(root.getLeft() != null) {
			root.setLeft(null);
		}
		
		root.setLeft(child);
	}
	
	public void setRightSubTree(BTreeNode child) {
		if(root.getRight() != null) {
			root.setRight(null);
		}
		
		root.setRight(child);
	}
	
	public BTreeNode getLeftSubTree() {
		return root.getLeft();
	}
	
	public BTreeNode getRightSubTree() {
		return root.getRight();
	}
	
	public void preorder(BTreeNode root) {
		if(root != null) {
			System.out.println(root.getData());
			preorder(root.getLeft());
			preorder(root.getRight());
		}
	}
	
	public void inorder(BTreeNode root) {
		if(root != null) {
			inorder(root.getLeft());
			System.out.println(root.getData());
			inorder(root.getRight());
		}
	}
	
	public void postorder(BTreeNode root) {
		if(root != null) {
			postorder(root.getLeft());
			postorder(root.getRight());
			System.out.println(root.getData());
		}
	}
	
	public BTreeNode getRoot() {
		return root;
	}
}

public class BinaryTreeMain {
	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		tree.makeTree(null, 1, null);
		
		BTreeNode n2 = new BTreeNode(2);
		BTreeNode n3 = new BTreeNode(3);
		BTreeNode n4 = new BTreeNode(4);
		n2.setLeft(n4);
		tree.setLeftSubTree(n2);
		tree.setRightSubTree(n3);
		
		tree.preorder(tree.getRoot());
		tree.inorder(tree.getRoot());
		tree.postorder(tree.getRoot());
	}
}
