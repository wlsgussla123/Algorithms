package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N,M;
		private int[] map;
		private Queue<Integer> op = new LinkedList();
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			map = new int[N+1];
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			init();
			
			for(int i=1; i<=N; i++) {
				st = getStringTokenizer();
				map[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=0; i<M; i++) {
				st = getStringTokenizer();
				op.add(Integer.parseInt(st.nextToken()));
			}
		}
		
		private void solution() throws IOException {
			int cnt = 1;
			int pos = 1;
			int dice = 0;
			while(!op.isEmpty()) {
				dice = op.poll();
				pos = pos + dice;
				if(pos >= N) break;
				
				pos = pos + map[pos];
				if(pos >= N) break;
				cnt++;
			}
			bw.write(String.valueOf(cnt));
		}
		
		public void run() throws IOException {
			input();
			solution();
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}
