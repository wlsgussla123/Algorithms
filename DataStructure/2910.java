package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int count;
	int value;
	public Node(int count, int value) {
		this.count = count;
		this.value = value;
	}
}

class Task {
	private int N,C;
	private HashMap<Integer, Integer> map = new HashMap<>();
	private ArrayList<Node> list = new ArrayList<>();
	private Queue<Integer> order = new LinkedList<>();
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void solution() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		st = getStringTokenizer();
		for(int i=0; i<N; i++) {
			int key = Integer.parseInt(st.nextToken());
			if(map.containsKey(key)) {
				map.replace(key, map.get(key) + 1);
			} else {
				map.put(key, 1);
				order.add(key);
			}
		}
		
		while(!order.isEmpty()) {
			int key = order.poll();
			list.add(new Node(map.get(key), key));
		}
		
		Collections.sort(list, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o2.count - o1.count;
			}
		});
		
		for(Node cur : list) {
			for(int i=0; i<cur.count; i++) {
				System.out.print(cur.value + " ");
			}
		}
	}
	
	public void run() throws IOException {
		solution();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}
