package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private String str;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), "");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			str = st.nextToken();
			int len = str.length();
			boolean flag = false;
			int cnt = 0;
			
			for(int i=0; i<len; i++) {
				if(str.charAt(i) != ' ') {
					flag = true;
				}
				
				if(str.charAt(i) == ' ') {
					if(flag) {
						cnt++;
						flag = false;
					}
				}
			}
			
			if(flag) {
				cnt++;
			}
			
			System.out.println(cnt);
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

