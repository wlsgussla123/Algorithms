package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int[] alphabet = new int[26];
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		char[] a = st.nextToken().toCharArray();
		for(int i=0; i<a.length; i++) {
			alphabet[a[i]-'a']++;
		}
		
		st = getStringTokenizer();
		char[] b = st.nextToken().toCharArray();
		for(int j=0; j<b.length; j++) {
			alphabet[b[j]-'a']--;
		}
	}
	
	private void process() {
		int answer = 0;
		for(int i=0; i<26; i++) {
			if(alphabet[i] == 0) continue;
			if(alphabet[i] > 0) {
				answer += alphabet[i];
			} else {
				answer += alphabet[i] * -1;
			}
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
