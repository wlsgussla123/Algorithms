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
		private int N,M;
		private int[][] map;
		private boolean[][] visited;
		private ArrayList<Position> people;
		private ArrayList<Position> chicken;
		private final int HOME = 1, CHICKEN = 2, CHOICE = 3, MAX = 987654321;
		private int ans = MAX;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			map = new int[N][N];
			visited = new boolean[N][N];
			people = new ArrayList();
			chicken = new ArrayList();
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			init();
			
			for(int i=0; i<N; i++) {
				st = getStringTokenizer();
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == HOME) {
						people.add(new Position(i, j));
					} else if(map[i][j] == CHICKEN) {
						chicken.add(new Position(i, j));
					}
				}
			}
		}
		
		private int getDist(ArrayList<Position> list) {
			int sum = 0;
			int min = MAX;
			for(int i=0; i<people.size(); i++) {
				Position p = people.get(i);
				min = MAX;
				for(int j=0; j<list.size(); j++) {
					Position c = list.get(j);
					int dist = Math.abs(p.x - c.x) + Math.abs(p.y - c.y);
					min = min > dist ? dist : min;
				}
				sum += min;
			}
			return sum;
		}
		
		private void dfs(int idx, int cnt, ArrayList<Position> list, boolean[] check) {
			if(cnt == M) {
				int dist = getDist(list);
				ans = ans > dist ? dist : ans;
				return;
			}
			
			for(int i=idx; i<chicken.size(); i++) {
				if(check[i]) continue;
				check[i] = true;
				list.add(chicken.get(i));
				dfs(i+1, cnt+1, list, check);
				if(list.size() > 0) {
					list.remove(list.size()-1);
				}
				check[i] = false;
			}
		}
		
		public void run() throws IOException {
			input();
			dfs(0, 0, new ArrayList(), new boolean[chicken.size()]);
			bw.write(String.valueOf(ans)+"\n");
			close();
		}
		
		private void print() {
			for(Position p : people) {
				System.out.println(p.x + " " + p.y);
			}
			System.out.println();
			
			for(Position c : chicken) {
				System.out.println(c.x + " " + c.y);
			}
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}
