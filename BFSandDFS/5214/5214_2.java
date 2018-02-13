import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Task {
	private int N,K,M;
	private List<Integer>[] list;
	private HashSet<Integer>[] hashSet;
	private int[] input;
	private boolean[] visited;
	private final int INF = 987654321;
	private int answer = INF;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	public StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		list = new List[N+1];
		for(int i=1; i<=N; i++) list[i] = new ArrayList<Integer>();
		hashSet = new HashSet[N+1];
		for(int i=1; i<=N; i++) hashSet[i] = new HashSet<Integer>();
		input = new int[K+1];
		visited = new boolean[N+1];
	}
	
	private void connect(int a, int b) {
		if(hashSet[a].contains(b)) return;
		hashSet[a].add(b);
		list[a].add(b);
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=M; i++) {
			st = getStringTokenizer();
			input = new int[K+1];
			for(int j=1; j<=K; j++) {
				input[j] = Integer.parseInt(st.nextToken());
			}
			
			for(int a=1; a<=K; a++) {
				for(int b=1; b<=K; b++) {
					if(a==b) continue;
					connect(input[a], input[b]);
				}
			}
		}
	}
	
	// 리스트가 중복 요소를 갖고있으면 중복요소를 지운다.
	private void removeOverlap() {
		boolean[] check = new boolean[N+1];
		for(int i=1; i<=N; i++) {
			check = new boolean[N+1];
			for(int j=0; j<list[i].size(); j++) {
				int num = list[i].get(j);
				if(check[num]) list[i].remove(j);
				check[num] = true;
			}
		}
	}
	
	private void bfs(int start) {
		Queue<int[]> q = new LinkedList<int[]>();
		// 정류장 하나인거 나중에 고려해보자
		for(Integer num : list[start]) {
			visited[num] = true;
			int[] pos = {num,2}; // 다음 인덱스 + 몇번째 정류장?
			q.add(pos); 
		}
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int index = cur[0];
			int cnt = cur[1];
			if(index == N) {
				if(answer > cnt) answer = cnt;
				return;
			}
			
			for(Integer nextIdx : list[index]) {
				if(!visited[nextIdx]) {
					visited[nextIdx] = true;
					int[] next = {nextIdx, cnt+1};
					q.add(next);
				}
			}
		}
	}
	
	private void process() {
//		removeOverlap();
		bfs(1);
		if(answer == INF) System.out.println("-1");
		else System.out.println(answer);
	}

	public void run() throws IOException {
		input();
		process();
	}
	
	private void print() {
		for(int i=1; i<=N; i++) {
			for(Integer num : list[i]) {
				System.out.print(num + " ");
			}
			System.out.println();
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}
