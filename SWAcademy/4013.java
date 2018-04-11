package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Operation {
	int num;
	int dir;
	public Operation(int num, int dir) {
		this.num = num;
		this.dir = dir;
	}
}

public class Main {
	public static void main(String args[]) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int K;
		private int[][] magnetic;
		private ArrayList<Operation> list;
		private final int N=4, M=8;
		private boolean[] visited;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			list = new ArrayList();
			magnetic = new int[N][M];
			visited = new boolean[N];
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			K = Integer.parseInt(st.nextToken());
			init();
			
			for(int i=0; i<N; i++) {
				st = getStringTokenizer();
				for(int j=0; j<M; j++) {
					magnetic[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<K; i++) {
				st = getStringTokenizer();
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				list.add(new Operation(num-1, dir));
			}
		}
		
		private void rotate(int num, int dir) {
			if(dir == 1) {
				int temp = magnetic[num][M-1];
				for(int i=M-1; i>=1; i--) {
					magnetic[num][i] = magnetic[num][i-1];
				}
				magnetic[num][0] = temp;
			} else {
				int temp = magnetic[num][0];
				for(int i=0; i<M-1; i++) {
					magnetic[num][i] = magnetic[num][i+1];
				}
				magnetic[num][M-1] = temp;
			}
		}
		
		private void operate(int num, int dir) {
			int left = magnetic[num][6];
			int right = magnetic[num][2];
			rotate(num, dir);
			visited[num] = true;
			
			if(num == 0) {
				if(!visited[num+1] && right != magnetic[num+1][6]) {
					operate(num+1, dir * -1);
				}
			} else if(num == 3) {
				if(!visited[num-1] && left != magnetic[num-1][2]) {
					operate(num-1, dir * -1);
				}
			} else {
				if(!visited[num+1] && right != magnetic[num+1][6]) {
					operate(num+1, dir * -1);
				}

				if(!visited[num-1] && left != magnetic[num-1][2]) {
					operate(num-1, dir * -1);
				}
			}				
		}
		
		private int calculate() {
			int sum = 0;
			for(int i=0; i<4; i++) {
				if(magnetic[i][0] == 1) {
					if(i==0) {
						sum += 1;
					} else if(i==1) {
						sum += 2;
					} else if(i==2) {
						sum += 4;
					} else {
						sum += 8;
					}
				}
			}
			
			return sum;
		}
		
		public void run() throws IOException {
			st = getStringTokenizer();
			int T = Integer.parseInt(st.nextToken());
			for(int i=1; i<=T; i++) {
				input();
				for(Operation o : list) {
					visited = new boolean[4];
					operate(o.num, o.dir);
				}
				bw.write("#" + i + " " + String.valueOf(calculate())+"\n");
			}
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}
