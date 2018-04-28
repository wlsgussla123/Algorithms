package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N,M;
		private HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		
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
			
			for(int i=0; i<N; i++) {
				st = getStringTokenizer();
				map.put(st.nextToken(), true);
			}
			
			int cnt = 0;
			for(int i=0; i<M; i++) {
				st = getStringTokenizer();
				String str = st.nextToken();
				if(map.containsKey(str)) {
					if(map.get(str))
						cnt++;
				}
			}
			
			bw.write(String.valueOf(cnt));
		}
		
		public void run() throws IOException {
			input();
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}
