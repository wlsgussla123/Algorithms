package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Task {
	private int N;
	private HashMap<String, Integer> map;
	private int answer;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		answer = 0;
		map = new HashMap<>();
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
		
		for(int i=0; i<N; i++) {
			st = getStringTokenizer();
			String name = st.nextToken();
			String category = st.nextToken();
			if(map.get(category) != null) {
				int value = map.get(category);
				value++;
				map.put(category, value);
			} else {
				map.put(category, 1);
			}
		}
	}
	
	private void solution() {
		int answer = 1;
		for(int value : map.values()) {
			// 종류 개수 + 1 
			answer *= value+1;
		}
		// 아무것도 안 입었을 경우를 뺀다.
		System.out.println(answer-1);
	}
	
	public void run() throws IOException {
		st = getStringTokenizer();
		int T = Integer.parseInt(st.nextToken());
		for(int i=1; i<=T; i++) {
			input();
			solution();
		}
	}
}

public class Main {
    public static void main(String a[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}
