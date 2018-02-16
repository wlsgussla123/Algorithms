package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {
	int min;
	int max;
	public Node(int min, int max) {
		this.min = min;
		this.max = max;
	}
}

class SegmentTree {
	private Node[] tree;
	private int size;
	public SegmentTree(int[] arr, int n) {
		// 사이즈를 구하는 공식. 2^x > n 을 만족하는 최솟 x 
		int x = (int)Math.ceil(Math.log(n) / Math.log(2));
		this.size = (int)Math.pow(2, x) * 2 -1;
		tree = new Node[size];
		init(arr, tree, 1, 0, n-1);
//		print();
	}
	
	private void print() {
		for(int i=1; i<size; i++) {
			if(tree[i] != null) {
				System.out.println(i + " " + tree[i].min + " " + tree[i].max);
			}
		}
	}
	
	// 세그먼트 트리 생성 (최소값, 최대값을 갖고있음)
	private Node init(int[] arr, Node[] tree, int node, int start, int end) {
		if(start == end) {
			Node newNode = new Node(arr[start], arr[start]);
			tree[node] = newNode;
			return newNode;
		}
		
		int mid = (start + end) / 2;
		Node lNode = init(arr, tree, node * 2, start, mid);
		Node rNode = init(arr, tree, node * 2 + 1, mid+1, end);
		int min=0, max=0;
		if(lNode.max < rNode.max) max = rNode.max;
		else max = lNode.max;
		if(lNode.min < rNode.min) min = lNode.min;
		else min = rNode.min;
		tree[node] = new Node(min, max);
		
		return new Node(min,max);
	}
	
	public void findMinMax(int left, int right, int n) {
		Node answer = findMinMax(tree, 1, 0, n-1, left-1, right-1);
		if(answer != null) System.out.println(answer.min + " " + answer.max);
	}
	
	private Node findMinMax(Node[] tree, int node, int start, int end, int left, int right) {
		// 범위에 맞지 않을 경우
		if(left > end || right < start) return null;
		if(left <= start && end <= right) {
			return tree[node];
		}
		
		int mid = (start + end) / 2;
		Node lNode = findMinMax(tree, node*2, start, mid, left, right);
		Node rNode = findMinMax(tree, node*2+1, mid+1, end, left, right);
		if(lNode == null && rNode != null) {
			return new Node(rNode.min, rNode.max);
		} else if(lNode != null && rNode == null) {
			return new Node(lNode.min, lNode.max);
		} else if(lNode == null && rNode == null) {
			System.out.println("Both null");
			return null;
		} else {
			int min = 0, max = 0;
			if(lNode.max > rNode.max) {
				max = lNode.max;
			} else {
				max = rNode.max;
			}
			
			if(lNode.min > rNode.min) {
				min = rNode.min;
			} else {
				min = lNode.min;
			}
			
			return new Node(min,max);
		}
	}
}

class Task {
	private int N,M;
	private int[] arr;
	private SegmentTree segmentTree;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	public StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		arr = new int[N];
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		for(int i=0; i<N; i++) {
			st = getStringTokenizer();
			arr[i] = Integer.parseInt(st.nextToken());
		}
		segmentTree = new SegmentTree(arr, N);
		
		for(int i=0; i<M; i++) {
			st = getStringTokenizer();
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			segmentTree.findMinMax(l, r, N);
		}
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
