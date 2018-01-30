package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int A,B;
	private int cnt=0;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
	}

	private void close() throws IOException {
		br.close();
		bw.close();		
	}
	
	private void gumminsoo(int index) {
		while(index > 0) {
			int num = index%10;
			if(num != 4 && num != 7) {
				cnt--;
				return;
			}
			index/=10; 
		}
	}
	
	private void process() {
		cnt = B-A+1;
		for(int i=A; i<=B; i++) {
			int j = i;
			gumminsoo(j);
		}
		System.out.println(cnt);
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
