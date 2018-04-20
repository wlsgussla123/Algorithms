package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N,M;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
		}
		
		private void dfs(int start, int cnt, ArrayList<Integer> list) {
			if(cnt == M) {
				for(int n : list) {
					System.out.print(n + " ");
				}
				System.out.println();
				return;
			}
			
			for(int i=start; i<=N; i++) {
				ArrayList<Integer> temp = (ArrayList<Integer>) list.clone();
				temp.add(i);
				dfs(i+1, cnt+1, temp);
			}
		}
		
		public void run() throws IOException {
			input();
			dfs(1,0, new ArrayList());
			close();
		}

		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}
