package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int[] cost;
		private int[] plan;
		private int[] memo; // 1일 vs 1달 치로 더 싼 가격 저자
		private boolean[] visited;
		private final int MAX = 987654321;
		private int ans;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			cost = new int[5]; // 1 : 1일, 2 : 1달, 3 : 3달, 4 : 1년
			plan = new int[13];
			memo = new int[13];
			visited = new boolean[13];
			ans = MAX;
		}
		
		private void input() throws IOException {
			init();
			st = getStringTokenizer();
			for(int i=1; i<=4; i++) {
				cost[i] = Integer.parseInt(st.nextToken());
			}
			
			st = getStringTokenizer();
			for(int i=1; i<=12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}
			
			// 각 달에 대해서 무엇이 이득인지 초기화
			for(int i=1; i<=12; i++) {
				memo[i] = cost[2] > plan[i] * cost[1] ? plan[i] * cost[1] : cost[2];
			}
		}
		
		private boolean checkValid(int x) {
			return x>=1 && x<=12;
		}
		
		private int getCost() {
			int cost = 0;
			for(int i=1; i<=12; i++) {
				if(visited[i]) continue;
				cost += memo[i];
			}
			
			return cost;
		}
		
		private void threeMonth(int start, int cnt) {
			// 계산하자
			int c = getCost() + cost[3] * cnt; // 3달치 끊는 양 계산과 나머지 1일 or 1달 가격
			ans = ans > c ? c : ans;
			
			for(int i=start; i<=12; i++) {
				for(int j=i; j<i+3; j++) {
					if(!checkValid(j)) continue;
					visited[j] = true;
				}
				threeMonth(i+3, cnt+1);
				for(int j=i; j<i+3; j++) {
					if(!checkValid(j)) continue;
					visited[j] = false;
				}
			}
		}
		
		private void solution() {
			// 3개월치 섞은 경우
			for(int i=1; i<=12; i++) {
				// 3개월 치 구매
				for(int j=i; j<i+3; j++) {
					if(!checkValid(j)) continue;
					visited[j] = true;
				}
				threeMonth(i+3, 1);
				// 3개월 치 구매 해제
				for(int j=i; j<i+3; j++) {
					if(!checkValid(j)) continue;
					visited[j] = false;
				}
			}
			
			int cost = 0;
			for(int i=1; i<=12; i++) {
				cost += memo[i];
			}
			ans = ans > cost ? cost : ans;
		}
		
		public void run() throws IOException {
			st = getStringTokenizer();
			int T = Integer.parseInt(st.nextToken());
			for(int i=1; i<=T; i++) {
				input();
				solution();
				bw.write("#"+ i + " " + String.valueOf(ans > cost[4] ? cost[4] : ans)+"\n");
			}
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}
