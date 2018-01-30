package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int[] alphabet = new int[26];
	private char[] str;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		str = st.nextToken().toCharArray();
		for(int i=0; i<26; i++) alphabet[i] = -1;
	}

	void close() throws IOException {
		br.close();
		bw.close();		
	}
	
	private void process() {
		for(int i=0; i<str.length; i++)
			if(alphabet[str[i]-'a'] == -1) alphabet[str[i]-'a'] = i;
		for(int i=0; i<26; i++)
			System.out.print(alphabet[i] + " ");
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
