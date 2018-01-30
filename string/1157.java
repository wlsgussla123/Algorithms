package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int[] alphabet = new int[26];
	private int max = 0;
	private boolean isSame = false;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		String str = st.nextToken().toLowerCase();
		char[] input = str.toCharArray();
		for(int i=0; i<input.length; i++)
			alphabet[input[i]-'a']++;
	}

	void close() throws IOException {
		br.close();
		bw.close();		
	}
	
	private void process() {
		max = 0;
		for(int i=1; i<26; i++) {
			if(alphabet[max] < alphabet[i]) max = i;
		}
		for(int i=0; i<26; i++) {
			if(i==max) continue;
			if(alphabet[i] == alphabet[max]) isSame = true;
		}
		
		if(isSame) System.out.println("?");
		else System.out.println((char)('A'+max));
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
