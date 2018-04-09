package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Node {
	int x;
	int y;
	int dir;
	int count;
	
	public Node(int x, int y, int count, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.count = count;
	}
}

public class Main {
	public static void main(String args[]) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N,M,K; // 셀의 개수, 격리 시간, 군집 개수
		private int[][] map;
		// 북 남 서 동
		private final int[] dx = {0,-1,1,0,0};
		private final int[] dy = {0,0,0,-1,1};
		private ArrayList<Node> list;
		private int ans;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			ans = 0;
			map = new int[N][N];
			list = new ArrayList();
			for(int i=0; i<N; i++) {
				map[0][i] = 1;
				map[N-1][i] = 1;
				map[i][0] = 1;
				map[i][N-1] = 1;
			}
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			init();
			
			int x,y,dir,count;
			for(int i=0; i<K; i++) {
				st = getStringTokenizer();
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				count = Integer.parseInt(st.nextToken());
				dir = Integer.parseInt(st.nextToken());
				list.add(new Node(x, y, count, dir));
			}
		}
		
		private int reverseDir(int dir) {
			if(dir == 1) {
				return 2;
			} else if(dir == 2) {
				return 1;
			} else if(dir == 3) {
				return 4;
			} else {
				return 3;
			}
		}
		
		private void move(Node node) {
			int x = node.x;
			int y = node.y;
			int dir = node.dir;
			int cnt = node.count;
			
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if(map[nx][ny] == 1) {
				node.x = nx;
				node.y = ny;
				node.dir = reverseDir(dir);
				node.count = cnt/2;
			} else {
				node.x = nx;
				node.y = ny;
			}
		}
		
		private void solution() throws IOException {
			while(M-->0) {
				for(Node node : list) {
					move(node); // 좌표 이동 및 경계선일 경우 방향전환과 미생물 줄이기
				}
				
				int iIdx = 0;
				while(iIdx < list.size()) {
					int x = list.get(iIdx).x;
					int y = list.get(iIdx).y;
					int dir = list.get(iIdx).dir;
					int sum = list.get(iIdx).count;
					int max = list.get(iIdx ).count;
					
					int jIdx = iIdx + 1;
					boolean flag = false;
					while(jIdx < list.size()) {
						if(x == list.get(jIdx).x && y == list.get(jIdx).y) {
							flag = true;
							sum += list.get(jIdx).count;
							if(max < list.get(jIdx).count) {
								max = list.get(jIdx).count;
								dir = list.get(jIdx).dir;
							}
							list.remove(jIdx);
							continue;
						}
						jIdx++;
					}
					
					// 만약 같은 것이 있어서 지웠을 경우
					if(flag) {
						list.add(new Node(x,y,sum,dir));
						list.remove(iIdx);
						continue;
					}
					iIdx++;
				}
			}
			
			for(Node node : list) {
				ans += node.count;
			}
		}
		
		public void run() throws IOException {
			st = getStringTokenizer();
			int T = Integer.parseInt(st.nextToken());
			for(int i=1; i<=T; i++) {
				input();
				solution();
				bw.write(String.valueOf("#"+i+" " + ans+"\n"));
				bw.flush();
			}
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}
