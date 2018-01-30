package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private char[] input;
	private int length = 0;
	private int answer = 0;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		input = st.nextToken().toCharArray();
		length = input.length;
	}

	private void close() throws IOException {
		br.close();
		bw.close();		
	}
	
	private void checker(int start, int end) {
		char[] str = new char[end-start+1];
		int index=0;
		for(int i=start; i<=end; i++) {
			str[index++] = input[i];
		}
		
		String tmp = String.valueOf(str);
		if(tmp.equals("c=")) length--;
		else if(tmp.equals("c-")) length--;
		else if(tmp.equals("dz=")) length--;
		else if(tmp.equals("d-")) length--;
		else if(tmp.equals("lj")) length--;
		else if(tmp.equals("nj")) length--;
		else if(tmp.equals("s=")) length--;
		else if(tmp.equals("z=")) length--;
	}
	
	private void process() {
		for(int i=0; i<input.length; i++) {
			for(int j=i; j<=i+2; j++) {
				if(j>=input.length) break;
				checker(i,j);
			}
		}
		
		System.out.println(length);
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
