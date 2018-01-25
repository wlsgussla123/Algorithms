package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Task {
	private List<Integer> list;
	private boolean flag = true;
	
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		list = new ArrayList<Integer>();
		while(st.hasMoreTokens()) {
			int n = Integer.parseInt(st.nextToken());
			if(n == -1) {
				flag = false;
				break;
			}
			list.add(n);
		}
	}
	
	private void process() {
		int size = list.size()-1;
		int cnt = 0;
		for(int i=0; i<size; i++) {
			int num = list.get(i);
			for(int j=0; j<size; j++) {
				if(num * 2 == list.get(j)) {
					cnt++;
					break;
				}
			}
		}
		System.out.println(cnt);
	}
	
	public void run() throws IOException {
		while(flag) {
			input();
			if(!flag) break;
			process();
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}
