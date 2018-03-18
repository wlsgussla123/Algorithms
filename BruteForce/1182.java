package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Task {
	private int N,S;
	private ArrayList<Integer> list = new ArrayList<>();
	private int answer = 0;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		st = getStringTokenizer();
		for(int i=0; i<N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
	}
	
	private void solution(int idx, int sum) {
		sum += list.get(idx);
		if(sum == S) answer++;
		if(idx+1>=N) return;
		solution(idx+1, sum);
		solution(idx+1, sum-list.get(idx));
	}
	
	public void run() throws IOException {
		input();
		solution(0, 0);
		System.out.println(answer);
	}
}

public class Main {
    public static void main(String a[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}
