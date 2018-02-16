package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 노드
class Node {
	private int data;
	private Node left;
	private Node right;
	
	public Node(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}
}

class BST {
	public Node root;
	public BST() {
		this.root = null;
	}
	
	public boolean find(int key) {
		Node cur = root;
		while(cur != null) {
			if(cur.getData() == key) {
				return true;
			} else if(cur.getData() > key) {
				cur = cur.getLeft();
			} else {
				cur = cur.getRight();
			}
		}
		
		return false;
	}
	
	public void insert(int data) {
		Node newNode = new Node(data);
		if(root == null) {
			root = newNode;
			return;
		}
		// 자리 찾아가서 삽입
		Node cur = root;
		Node p = null;
		while(true) {
			p = cur;
			if(cur.getData() > data) {
				cur = cur.getLeft();
				if(cur == null) {
					p.setLeft(newNode);
					break;
				}
			} else {
				cur = cur.getRight();
				if(cur == null) {
					p.setRight(newNode);
					break;
				}
			}
		}
	}
	
	public void delete(int data) {
		Node p = root;
		Node del = root;
		boolean isLeftChild = false; // 부모 기준에서 왼쪽 서브트리인가 ?
		while(del.getData() != data) {
			p = del;
			if(del == null) return;
			// cur.data가 data보다 큼
			if(del.getData() > data) {
				isLeftChild = true;
				del = del.getLeft();
			} else {
				isLeftChild = false;
				del = del.getRight();
			}
		}
		
		// Case 1: 자식노드가 없는 경우
		if(del.getLeft() == null && del.getRight() == null) {
			if(del==root) {
				root = null;
				return;
			}
			if(isLeftChild) {
				// 왼쪽 자식 지우기
				p.setLeft(null);
			} else {
				// 오른쪽 자식 지우기
				p.setRight(null);
			}
		} else if(del.getLeft() == null && del.getRight() != null) {
			// Case 2-1: 오른쪽 자식을 갖는 경우
			if(del==root) {
				root = del.getRight();
				return;
			}
			if(isLeftChild) {
				p.setLeft(del.getRight());
			} else {
				p.setRight(del.getRight());
			}
			
		} else if(del.getLeft() != null && del.getRight() == null) {
			// Case 2-2: 왼쪽 자식을 갖는 경우
			if(del==root) {
				root = del.getLeft();
				return;
			}
			if(isLeftChild) {
				p.setLeft(del.getLeft());
			} else {
				p.setRight(del.getLeft());
			}
		} else {
			// Case 3: 자식 노드가 둘 다 있다. (오른쪽 최소값 or 왼쪽의 최대값)
			Node successor = getSuccessor(del);
			if(del == root) {
				root = successor;
				return;
			}
			
			if(isLeftChild) {
				p.setLeft(successor);
			} else {
				p.setRight(successor);
			}
			successor.setLeft(del.getLeft());
		}
	}
	
	// 후임자 찾기 (오른쪽에서 가장 작은 노드)
	public Node getSuccessor(Node del) {
		Node successor = null;
		Node successorParent = null;
		Node cur = del.getRight();
		while(cur!=null) {
			successorParent = successor;
			successor = cur;
			cur = cur.getLeft();
		}
		if(successor != del.getRight()) {
			successorParent.setLeft(successor.getRight());
			successor.setLeft(del.getRight());
		}
		
		return successor;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BST bst = new BST();
		bst.insert(1);
		bst.insert(4);
		bst.insert(2);
		bst.insert(3);
		bst.insert(5);
		System.out.println(bst.find(1));
		System.out.println(bst.find(4));
		System.out.println(bst.find(7));
		bst.delete(1);
		System.out.println(bst.find(1));
		System.out.println(bst.find(4));
		System.out.println(bst.find(7));
	}
}
