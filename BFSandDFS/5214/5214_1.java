import java.beans.Visibility;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import org.omg.CORBA.INTERNAL;

class Task {
	private int N,K,M;
	private List<Integer>[] list;
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
		input = new int[K+1];
		for(int i=1; i<=N; i++) list[i] = new ArrayList<Integer>();
		visited = new boolean[N+1];
	}
	
	private void connect(int a, int b) {
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
	
	private void dfs(int index, int cnt) {
		if(index == N) {
			if(answer > cnt) {
				answer = cnt;
			}
		} else {
			for(Integer next : list[index]) {
				if(!visited[next]) {
					visited[next] = true;
					dfs(next, cnt+1);
					visited[next] = false;
				}
			}
		}
	}
	
	private void process() {
		removeOverlap();
//		print();
		for(Integer next : list[1]) {
			visited = new boolean[N+1];
			visited[1] = true;
			visited[next] = true;
			dfs(next, 2); // 1과 지금 들어가는 next를 포함하므로 2부터 시작
		}
		
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
