package algo;

class BinarySearchTree {
	class Node {
		int key;
		Node left;
		Node right;
		public Node(int key) {
			this.key = key;
			this.left = null;
			this.right = null;
		}
	}
	
	private Node root;
	public BinarySearchTree() {
		this.root = null;
	}
	
	public void insert(int key) {
		root = insertKey(root, key); 
	}
	
	private Node insertKey(Node root, int key) {
		Node node = new Node(key);
		if(root == null) {
			root = node;
			return root;
		}
		
		if(root.key == key) return root;
		if(root.key > key) {
			root.left = insertKey(root.left, key);
		} else {
			root.right = insertKey(root.right, key);
		}
		
		return root;
	}
	
	public void delete(int key) {
		root = deleteKey(root, key);
	}
	
	private Node minimumElement(Node root) {
		if(root.left == null)
			return root;
		else
			return minimumElement(root.left);
	}
	
	private Node deleteKey(Node root, int key) {
		if(root == null)
			return null;
		if(root.key > key) {
			root.left = deleteKey(root.left, key);
		} else if(root.key < key) {
			root.right = deleteKey(root.right, key);
		} else {
			if(root.left != null && root.right != null) {
				Node temp = root;
				Node minNodeForRight = minimumElement(temp.right);
				root.key = minNodeForRight.key;
				deleteKey(root.right, minNodeForRight.key);
			} else if(root.left != null) {
				root = root.left;
			} else if(root.right != null) {
				root = root.right;
			} else {
				root = null;
			}
		}
		return root;
	}
	
	public boolean searchKey(Node root, int key) {
		if(root == null)
			return false;

		
		if(root.key > key) {
			return searchKey(root.left, key);
		} else if(root.key < key) {
			return searchKey(root.right, key);
		} else {
			return true;
		}
	}
	
	public void inorder(Node root) {
		if(root != null) {
			inorder(root.left);
			System.out.println(root.key);
			inorder(root.right);
		}
	}
	
	public Node getRoot() {
		return root;
	}
}

public class BinarySearchTreeMain {
	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree();
		bst.insert(1);
		bst.insert(5);
		bst.insert(3);
		bst.insert(9);
		bst.insert(7);
		bst.inorder(bst.getRoot());
		
		if(bst.searchKey(bst.getRoot(), 3)) {
			System.out.println("key in BST");
		} else {
			System.out.println("No key in BST");
		}
	
		bst.delete(9);
		bst.inorder(bst.getRoot());
	}
}
