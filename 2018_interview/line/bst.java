package datastructure;

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
	
	private Node root = null;
	public BinarySearchTree() {
		this.root = null;
	}
	
	private Node insertKey(Node root, int key) {
		if(root == null) {
			Node newNode = new Node(key);
			root = newNode;
			return root;
		}
		
		if(root.key > key) {
			root.left = insertKey(root.left, key);
		} else if(root.key < key) {
			root.right = insertKey(root.right, key);
		}
		return root;
	}
	
	public void insert(int key) {
		root = insertKey(root, key);
	}
	
	private boolean searchKey(Node root, int key) {
		if(root == null) {
			System.out.println("key was not found");
			return false;
		}
		
		if(root.key == key) {
			return true;
		} else if(root.key > key) {
			return searchKey(root.left, key);
		} else {
			return searchKey(root.right, key);
		}
	}
	
	public boolean search(int key) {
		return searchKey(root, key);
	}

//	private Node minNode(Node root) {
//		while(root.right != null) {
//			root = root.right;
//		}
//		
//		return root;
//	}
	
//	private Node deleteKey(Node root, int key) {
//		if(root == null) {
//			System.out.println("not found");
//			return null;
//		}
//		
//		if(root.key > key) {
//			return root.left = deleteKey(root.left, key);
//		} else if(root.key < key) {
//			return root.right = deleteKey(root.right, key);
//		} else {
//			if(root.left != null && root.right != null) {
//				Node temp = root;
//				Node min = minNode(temp.left);
//				root.key = min.key;
//				deleteKey(root.left, min.key);
//			} else if(root.left != null) {
//				root = root.left;
//			} else if(root.right != null) {
//				root = root.right;
//			} else {
//				root = null;
//			}
//			
//			return root;
//		}
//	}
	
	private Node minNode(Node root) {
		while(root.left != null) {
			root = root.left;
		}
		
		return root;
	}
	
	private Node deleteKey(Node root, int key) {
		if(root == null) {
			System.out.println("not found");
			return null;
		}
		
		if(root.key > key) {
			root.left = deleteKey(root.left, key);
		} else if(root.key < key) {
			root.right = deleteKey(root.right, key);
		} else {
			if(root.left != null && root.right != null) {
				// 자식이 두 개 다 없을 경우.
				Node min = minNode(root.right);
				root.key = min.key;
				root.right = deleteKey(root.right, min.key);
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
	
	public void delete(int key) {
		root = deleteKey(root, key);
	}
	
	private void inorder(Node root) {
		if(root == null) {
			return;
		}
		inorder(root.left);
		System.out.println(root.key);
		inorder(root.right);
	}
	
	public void inorder() {
		inorder(root);
	}
}

public class BinarySearchTreeMain {
	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree();
		bst.insert(7);
		bst.insert(1);
		bst.insert(6);
		bst.insert(4);
		bst.insert(9);
		bst.insert(8);
		bst.insert(10);
		bst.insert(5);
//		bst.inorder();
//		System.out.println(bst.search(1));
//		System.out.println(bst.search(7));
//		System.out.println(bst.search(3));
//		System.out.println(bst.search(9));
//		System.out.println(bst.search(1));
//		System.out.println(bst.search(11));
		bst.delete(7);
		bst.delete(1);
		bst.delete(10);
		bst.inorder();
	}
}
