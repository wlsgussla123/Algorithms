package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private String str1, str2;
	private int[][] lcs = new int[1001][1001];
	
	private StringTokenizer st = null;
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		str1 = "0" + st.nextToken();
		st = getStringTokenizer();
		str2 = "0" + st.nextToken();
	}
	
	private void solution() {
		int len1 = str1.length();
		int len2 = str2.length();
		for(int i=0; i<len1; i++) {
			for(int j=0; j<len2; j++) {
				if(i==0 || j==0) {
					lcs[i][j] = 0;
					continue;
				}
				
				if(str1.charAt(i) == str2.charAt(j)) {
					lcs[i][j] = lcs[i-1][j-1] + 1;
				} else {
					if(lcs[i-1][j] > lcs[i][j-1]) {
						lcs[i][j] = lcs[i-1][j];
					} else {
						lcs[i][j] = lcs[i][j-1];
					}
				}
			}
		}
		System.out.println(lcs[len1-1][len2-1]);
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
    	Task task = new Task();
    	task.run();
    }
}
