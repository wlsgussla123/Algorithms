package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class SegmentTree {
	int[] tree;
	int size;
	int N;
	
	public SegmentTree(int[] arr) {
		size = arr.length * 4;
		N = arr.length;
		tree = new int[size];
		init(tree, arr, 1, 0, arr.length-1);
	}
	
	private int init(int[] tree, int[] arr, int node, int start, int end) {
		if(start == end) {
			tree[node] = arr[start];
			return arr[start]; 
		}
		
		int mid = (start + end) / 2;
		int left = init(tree, arr, node*2, start, mid);
		int right = init(tree, arr, node*2+1, mid+1, end);
		tree[node] = left + right;
		return tree[node];
	}
	
	public void subSum(int a, int b) {
		System.out.println(subSum(tree, 1, 0, N-1, a-1, b-1));
	}
	
	private int subSum(int[] tree, int node, int start, int end, int left, int right) {
		if(left > end || right < start) return 0;
		if(left <= start && end <= right) return tree[node];
		int mid = (start + end) / 2;
		int l =  subSum(tree, node*2, start, mid, left, right);
		int r = subSum(tree, node*2+1, mid+1, end, left, right);
		return l+r;
	}
	
	public void print() {
		for(int i=1; i<size; i++) {
			if(tree[i] != 0) {
				System.out.println(i + " " + tree[i]);
			}
		}
	}
}

class Task {
	private int N,M;
	private int[] arr;
	private SegmentTree segmentTree;
	// input
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = getStringTokenizer();
		for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());
		segmentTree = new SegmentTree(arr);
		
		st = getStringTokenizer();
		M = Integer.parseInt(st.nextToken());
		for(int i=0; i<M; i++) {
			st = getStringTokenizer();
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			segmentTree.subSum(a, b);
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
