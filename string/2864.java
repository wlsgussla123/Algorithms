package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private String A,B;
	private int min = 987654321, max = -1;
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		A = st.nextToken();
		B = st.nextToken();
	}
	
	private int getMin() {
		StringBuilder a = new StringBuilder();
		StringBuilder b = new StringBuilder();
	
		for(int i=0; i<A.length(); i++) {
			if(A.charAt(i) == '6') a.append(5);
			else a.append(A.charAt(i));
		}
		
		for(int i=0; i<B.length(); i++) {
			if(B.charAt(i) == '6') b.append(5);
			else b.append(B.charAt(i));
		}
		
		return Integer.parseInt(a.toString()) + Integer.parseInt(b.toString());
	}
	
	private int getMax() {
		StringBuilder a = new StringBuilder();
		StringBuilder b = new StringBuilder();
	
		for(int i=0; i<A.length(); i++) {
			if(A.charAt(i) == '5') a.append(6);
			else a.append(A.charAt(i));
		}
		
		for(int i=0; i<B.length(); i++) {
			if(B.charAt(i) == '5') b.append(6);
			else b.append(B.charAt(i));
		}
		
		return Integer.parseInt(a.toString()) + Integer.parseInt(b.toString());		
	}
	
	private void solution() {
		min = getMin();
		max = getMax();
		System.out.println(min + " " + max);
	}
	
	public void run() throws IOException {
		input();
		solution();
		close();
	}
	
	private void close() throws IOException {
		br.close();
		bw.close();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}
