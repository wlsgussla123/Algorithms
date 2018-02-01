package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int L,P,V;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		L = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
	}
	
	private void close() throws IOException {
		br.close();
		bw.close();		
	}
	
	private int process() {
		int answer = 0;
		while(V>0) {
			if(V<L) answer+=V;
			else answer += L;
			V -= P;
		}
		return answer;
	}

    public void run() throws IOException {
    	int index=1;
    	int answer = 0;
    	while(true) {
    		input();
    		if(L==0 && P==0 && V==0) break;
    		answer = process();
    		System.out.println("Case "+index+": "+answer);
    		index++;
    	}
    	close();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}
