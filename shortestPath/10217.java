package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Task {
	private int N,M,K;
	private List<int[]>[] graph;
	private long[] cost;
	private long[] time;
	private long[][] dp;
	private final int INF = 987654321;

	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	@SuppressWarnings("unchecked")
	private void init() {
		graph = new ArrayList[N+1];
		for(int i=0; i<=N; i++) {
			graph[i] = new ArrayList<int[]>();
		}
		
		cost = new long[N+1];
		for(int i=1; i<=N; i++) cost[i] = INF;
		time = new long[N+1];
		for(int i=1; i<=N; i++) time[i] = INF;
		dp = new long[N+1][M+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) dp[i][j] = INF;
		}
	}
	
	// 입력
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken()); // 공항 개수
		M = Integer.parseInt(st.nextToken()); // 지원 비용
		K = Integer.parseInt(st.nextToken());
		init();
		
		int s,e,c,t;
		for(int i=1; i<=K; i++) {
			st = getStringTokenizer();
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			int[] edge = {e,c,t};
			graph[s].add(edge);
		}
	}
	
	private void dijkstra(int start) {
		PriorityQueue<int[]> headq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2]; // 시간이 중요하니 시간을 우선순위로 두자.
			}
		});
		cost[start] = 0;
		time[start] = 0;
		dp[1][0] = 0;
		
		int[] cur = {start, 0, 0};
		headq.add(cur);
		int curIndex, curCost, curTime, nextIndex, nextCost, nextTime;
		while(!headq.isEmpty()) {
			cur = headq.poll();
			curIndex = cur[0];
			curCost = cur[1];
			curTime = cur[2];
			
			if(curTime > dp[curIndex][curCost] || curCost > M) continue;
			
			// 힙에서 꺼낸 정점이 목적지라면 끝낸다. 한 번 꺼내진 정점은 더 이상 갱신되지 않기 때문에 이미 최단경로를 구함.
			if(curIndex == N) break;
			for(int[] edge: graph[curIndex]) {
				nextIndex = edge[0];
				nextCost = edge[1];
				nextTime = edge[2];
				
				if(curCost + nextCost > M) continue;
				if(dp[nextIndex][curCost + nextCost] <= curTime + nextTime) continue;
				int[] nextEdge = {nextIndex, curCost + nextCost, curTime + nextTime};
				headq.add(nextEdge);
				dp[nextIndex][curCost + nextCost] = curTime + nextTime;
				
//				if(time[nextIndex] > curTime + nextTime && curCost + nextCost <= M) {
//					time[nextIndex] = curTime + nextTime;
//					cost[nextIndex] = curCost + nextCost;
//					int[] nextEdge = {nextIndex, curCost + nextCost, curTime + nextTime};
//					headq.add(nextEdge);
//				}
			}
		}
		
		long answer = INF;
		for(int i=1; i<=M; i++) {
			answer = Math.min(answer, dp[N][i]);
		}
		System.out.println(answer == INF ? "Poor KCM" : answer);
	}
	
	public void run() throws IOException {
		st = getStringTokenizer();
		int tc = Integer.parseInt(st.nextToken());
		
		while(tc-->0) {
			input();
			dijkstra(1);			
		}

		br.close();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}
