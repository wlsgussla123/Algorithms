package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

class Task {
	private int[] number;
	private boolean[] visited;
	private int answer = 0;
	private int N,S;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		number = new int[N];
		visited = new boolean[N];
		st = getStringTokenizer();
		for(int i=0; i<N; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	private void calc(int idx, int sum) {
		sum += number[idx];
		if(sum == S) {
			answer++;
		}
		if(idx+1>=N) {
			return;
		}
		calc(idx+1, sum);
		calc(idx+1, sum - number[idx]);
	}
	
	public void run() throws IOException {
		input();
		calc(0,0);
		System.out.println(answer);
	}
}

public class Main {
    public static void main(String a[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}
