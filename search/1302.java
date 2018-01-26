package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Task {
	private int N;
	private List<String> list = new ArrayList<String>();
	private String answer;
	
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		for(int i=0; i<N; i++) {
			st = getStringTokenizer();
			list.add(st.nextToken());
		}
	}
	
	private void process() {
		Collections.sort(list);
		int cnt = 0;
		int max = 0;
		String str = list.get(0);
		answer = str;
		for(int i=1; i<N; i++) {
			if(str.equals(list.get(i))) {
				cnt++;
			} else {
				cnt = 0;
			}
			if(max < cnt) {
				max = cnt;
				answer = list.get(i);
			}
			str = list.get(i);
		}
		System.out.println(answer);
	}
	
	public void run() throws IOException {
		input();
		process();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}
