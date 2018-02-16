import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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

class Set {
	public BST bst;
	public BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public Set() {
		bst = new BST();
	}
	
	public void add(int x) {
		if(bst.find(x)) return;
		bst.insert(x);
	}
	
	public void remove(int x) {
		if(!bst.find(x)) return;
		bst.delete(x);
	}
	
	public void check(int x) throws IOException {
		if(bst.find(x)) bw.write("1");
		else bw.write("0");
		bw.newLine();
	}
	
	public void toggle(int x) {
		if(bst.find(x)) bst.delete(x);
		else bst.insert(x);
	}
	
	public void all() {
		bst = new BST();
		for(int i=1; i<=20; i++) {
			bst.insert(i);
		}
	}
	
	public void empty() {
		bst = new BST();
	}
}

class Task {
	private Set set;
	private int N;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	public StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		set = new Set();
		for(int i=0; i<N; i++) {
			st = getStringTokenizer();
			String op = st.nextToken();
			if(op.equals("add")) {
				int x = Integer.parseInt(st.nextToken());
				set.add(x);
			} else if(op.equals("check")) {
				int x = Integer.parseInt(st.nextToken());
				set.check(x);
			} else if(op.equals("remove")) {
				int x = Integer.parseInt(st.nextToken());
				set.remove(x);
			} else if(op.equals("toggle")) {
				int x = Integer.parseInt(st.nextToken());
				set.toggle(x);
			} else if(op.equals("all")) {
				set.all();
			} else if(op.equals("empty")) {
				set.empty();
			}
		}
		set.bw.close();
		br.close();
	}
	
	public void run() throws IOException {
		input();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}
