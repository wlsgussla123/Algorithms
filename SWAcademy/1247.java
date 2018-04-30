package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Position {
	int x;
	int y;
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N;
		private int sx,sy,ex,ey;
		private int ans = 987654321;
		private ArrayList<Position> customer = new ArrayList();
		private boolean[] visited;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			st = getStringTokenizer();
			sx = Integer.parseInt(st.nextToken());
			sy = Integer.parseInt(st.nextToken());
			ex = Integer.parseInt(st.nextToken());
			ey = Integer.parseInt(st.nextToken());
			visited = new boolean[N];
			
			int x;
			int y;
			for(int i=0; i<N; i++) {
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				customer.add(new Position(x, y));
			}
		}
		
		private int getDist(int x1, int x2, int y1, int y2) {
			int dx = Math.abs(x1 - x2);
			int dy = Math.abs(y1 - y2);
			return dx + dy;
		}
		
		private void solve(int x, int y, int cost, int cnt) {
			if(cnt == N) {
				int d = getDist(x, ex, y, ey);
				int sum = cost + d;
				ans = ans > sum ? sum : ans;
				return;
			}
			
			int size = customer.size();
			int d = -1;
			for(int i=0; i<size; i++) {
				if(!visited[i]) {
					visited[i] = true;
					d = getDist(x, customer.get(i).x, y, customer.get(i).y);
					solve(customer.get(i).x, customer.get(i).y, cost + d, cnt + 1);
					visited[i] = false;
				}
			}
		}
		
		public void run() throws IOException {
			st = getStringTokenizer();
			int T = Integer.parseInt(st.nextToken());
			for(int i=1; i<=T; i++) {
				input();
				solve(sx, sy, 0, 0);
				bw.write("#" + i + " " + String.valueOf(ans)+"\n");
				customer.clear();
				ans = 987654321;
			}
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}
