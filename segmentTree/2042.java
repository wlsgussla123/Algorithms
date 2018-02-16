package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SegmentTree {
	private long[] tree;
	private int size;
	public SegmentTree(long[] arr, int h) {
		size = h;
		tree = new long[h*4];
		init(arr, tree, 1, 0, size-1);
	}
	
	private long init(long[] arr, long[] tree, int node, int start, int end) {
		if(start == end) return tree[node] = arr[start];
		
		int mid = (start + end) / 2;
		return tree[node] = init(arr, tree, node * 2, start, mid) + init(arr, tree, node * 2 + 1, mid + 1, end);
	}
	
	public void update(int index, long diff) {
		update(tree, 1, 0, size-1, index-1, diff);
	}
	
	// tree배열, node 번호, start, end, 바꾸고자 하는 index, 바꾸고자 하는 값들의 차
	private void update(long[] tree, int node, int start, int end, int index, long diff) {
		// 인덱스가 범위 내에 존재하지 않으면 종료.
		if(!(start <= index && index <= end)) return;
		tree[node] += diff; // 만약에 숫자가 2였는데, 10으로 변경한다고 하면 diff가 8이다. 그러므로, 전체 구간 합도 8만큼 값을 증가시켜야 함.
		
		if(start != end) {
			int mid = (start + end) / 2;
			update(tree, node * 2, start, mid, index, diff);
			update(tree, node * 2 + 1, mid + 1, end, index, diff);
		}
	}
	
	public void sum(int a, int b) {
		System.out.println(sum(tree, 1, 0, size-1, a-1, b-1));
	}
	
	// left와 right는 left~right 구간 사이의 합을 구하라는 의미.
	private long sum(long[] tree, int node, int start, int end, int left, int right) {
		// 가간 합을 구하고자 하는 범위를 벗어났을 때
		if(left > end || right < start) return 0;
		// 구하고자 하는 구간ㄴ
		if(left <= start && end <= right) return tree[node];
		
		int mid = (start + end) / 2;
		return sum(tree, node * 2, start, mid, left, right) + sum(tree, node * 2 + 1, mid + 1, end, left, right);
	}
}

class Task {
	private int N,M,K;
	private long[] arr;
	private SegmentTree segmentTree;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	public StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		arr = new long[N];
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		init();
		for(int i=0; i<N; i++) {
			st = getStringTokenizer();
			arr[i] = Long.parseLong(st.nextToken());
		}
		segmentTree = new SegmentTree(arr, N);
		
		for(int i=0; i<M+K; i++) {
			st = getStringTokenizer();
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(a == 1) {
				long diff = c - arr[b-1];
				arr[b-1] = c;
				segmentTree.update(b, diff);
			} else {
				segmentTree.sum(b, c);
			}
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
