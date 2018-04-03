package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private String[] list;
	private String find;
	private int N;
	private StringTokenizer st = null;
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		find = st.nextToken();
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		list = new String[N];
		for(int i=0; i<N; i++) {
			st = getStringTokenizer();
			list[i] = st.nextToken();
			list[i] += list[i];
		}
	}
	
	private boolean checkAvailbe(int i, int j) {
		int len = list[i].length();
		int f_len = find.length();
		int idx = 0;
		
		for(int k=j; k<len; k++) {
			if(list[i].charAt(k) == find.charAt(idx))
				idx++;
			else 
				return false;
			
			if(idx == f_len) return true;
		}
		
		return false;
	}
	
	private void solution() throws IOException {
		int len = 0;
		int idx = 0;
		int f_len = find.length();
		int answer = 0;
		for(int i=0; i<N; i++) {
			len = list[i].length();
			idx = 0;
			for(int j=0; j<len; j++) {
				if(list[i].charAt(j) == find.charAt(idx)) {
					idx++;
				} else {
					if(checkAvailbe(i, j)) {
						answer++;
						break;
					}
					idx = 0;
				}
				
				if(idx == f_len) {
					answer++;
					break;
				}
			}
		}
		bw.write(String.valueOf(answer));
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

public class Main {
    public static void main(String args[]) throws IOException {
    	new Task().run();
    }
}
