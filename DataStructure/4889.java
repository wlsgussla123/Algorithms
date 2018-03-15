package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private String str;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		str = st.nextToken();
	}
	
	private void solution(int idx) {
		int len = str.length();
		int s=0,c=0;
		for(int i=0; i<len; i++) {
			if(str.charAt(i) == '{') {
				s++;
			} else {
				s--;
				if(s<0) {
					s+=2;
					c++;
				}
			}
		}
		c += s/2;
		System.out.println(idx+"." + " " + c);
	}
	
	public void run() throws IOException {
		int idx = 1;
		while(true) {
			input();
			if(str.charAt(0) == '-') break;
			solution(idx++);
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}
