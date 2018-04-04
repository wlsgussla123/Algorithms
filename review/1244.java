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
    	private char[] str;
    	private int N,K;
    	private int answer;
    	private boolean[][] visited = new boolean[1000000][11];
    	
    	private StringTokenizer st = null;
    	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	private StringTokenizer getStringTokenizer() throws IOException {
    		return new StringTokenizer(br.readLine(), " ");
    	}
    	
    	private void init() {
    		answer = -1;
    	}

    	private void input() throws IOException {
    		st = getStringTokenizer();
    		str = st.nextToken().toCharArray();
    		N = str.length;
    		K = Integer.parseInt(st.nextToken());
    		init();
    	}
    	
    	private void swap(int a, int b) {
    		char tmp = str[a];
    		str[a] = str[b];
    		str[b] = tmp;
    	}
    	
    	private void solve(int idx, int cnt) {
    		if(cnt==K) {
    			int n = Integer.parseInt(String.valueOf(str));
    			answer = answer > n ? answer : n;
    			return;
    		}
    		
    		for(int i=idx; i<N; i++) {
    			for(int j=i+1; j<N; j++) {
    				swap(i, j);
    				int n = Integer.parseInt(String.valueOf(str));
    				if(!visited[n][cnt+1]) {
    					visited[n][cnt+1] = true;
    					solve(i, cnt+1);
    				}
    				swap(i, j);
    			}
    		}
    	}
    	
		public void run() throws IOException {
			st = getStringTokenizer();
			int T = Integer.parseInt(st.nextToken());
			for(int i=1; i<=T; i++) {
				input();
				solve(0,0);
				bw.write("#"+ i + " " + String.valueOf(answer)+"\n");
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
