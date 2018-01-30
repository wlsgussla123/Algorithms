package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		char[] input = st.nextToken().toCharArray();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<input.length; i++)
			if(input[i] >= 'A' && input[i] <='Z') sb.append(input[i]);
		System.out.println(sb.toString());
	}
	
	private void close() throws IOException {
		br.close();
		bw.close();		
	}

	public void run() throws IOException {
		input();
		close();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}
