package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
	}

	private void close() throws IOException {
		br.close();
		bw.close();		
	}
	
	private boolean isGumminsoo(int index) {
		boolean flag = true;
		while(index > 0) {
			int num = index%10;
			if(num!=4 && num!=7) {
				flag = false;
				break;
			}
			index/=10;
		}
		
		return flag;
	}
	
	private void process() {
		int max = 0;
		for(int i=1; i<=N; i++) {
			int j = i;
			if(isGumminsoo(j)) max = i;
		}
		System.out.println(max);
	}
	
	public void run() throws IOException {
		input();
		process();
		close();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}
