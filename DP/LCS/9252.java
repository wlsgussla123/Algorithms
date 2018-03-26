package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
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
		// 행을 str2로 놓고, 열을 str1로 놓는다.
		// 그림을 그렸을 때를 생각하자.
		int len1 = str1.length();
		int len2 = str2.length();
		for(int i=0; i<len2; i++) {
			for(int j=0; j<len1; j++) {
				if(i==0 || j==0) {
					lcs[i][j] = 0;
					continue;
				}
				
				if(str2.charAt(i) == str1.charAt(j)) {
					lcs[i][j] = lcs[i-1][j-1] + 1;
				} else {
					if(lcs[i][j-1] > lcs[i-1][j]) {
						lcs[i][j] = lcs[i][j-1];
					} else {
						lcs[i][j] = lcs[i-1][j];
					}
				}
			}
		}
		
		System.out.println(lcs[len2-1][len1-1]);
		
		Stack<Character> s = new Stack<>();
		// 주의 구간 : 그림과 배열을 맵핑하기 위하여 str2를 행으로 하고 str1을 열로 하고있다. 따라서, i와j에도 이것이 적용되어야 함
		int i = len2-1, j = len1-1;
		while(lcs[i][j] != 0) {
			if(lcs[i][j] == lcs[i][j-1]) {
				j--;
				continue;
			}
			
			if(lcs[i][j] == lcs[i-1][j]) {
				i--;
				continue;
			}
			
			s.add(str1.charAt(j));
			i--;
			j--;
		}
		
		while(!s.isEmpty()) {
			System.out.print(s.pop());
		}
		System.out.println();
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
	
	private void print() {
		int len1 = str1.length();
		int len2 = str2.length();
		for(int i=0; i<len2; i++) {
			for(int j=0; j<len1; j++) {
				System.out.print(lcs[i][j] + " ");
			}
			System.out.println();
		}
	}
}

public class Main {
    public static void main(String args[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}
