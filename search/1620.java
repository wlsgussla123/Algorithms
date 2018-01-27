package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

class Task {
	private int N,M;
	private String[] dic;
	private HashMap<String, Integer> map = new HashMap<String, Integer>();
	
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dic = new String[N+1];
		
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			dic[i] = st.nextToken();
			map.put(dic[i], i);
		}
	}
	
	private void process() throws IOException {
		for(int i=1; i<=M; i++) {
			st = getStringTokenizer();
			String q = st.nextToken();
			// 숫자라면
			if(Pattern.matches("^[0-9]+$", q)) {
				System.out.println(dic[Integer.parseInt(q)]);
			} else {
				System.out.println(map.get(q));
			}
		}		
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
