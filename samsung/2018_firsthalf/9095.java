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
		private int N;
		private int ans;
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			ans = 0;
		}
		
		private void dfs(int sum) {
			if(sum>N) return;
			if(sum==N) {
				ans++;
				return;
			}
			
			for(int i=1; i<=3; i++) {
				dfs(sum+i);
			}
		}
		
		public void run() throws IOException {
			st = getStringTokenizer();
			int T = Integer.parseInt(st.nextToken());
			for(int idx=1; idx<=T; idx++) {
				input();
				for(int i=1; i<=3; i++) {
					dfs(i);
				}
				bw.write(String.valueOf(ans)+"\n");
			}
			close();
		}

		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}
