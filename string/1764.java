package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private int cnt = 0;
	private List<String> answers = new ArrayList<String>();
	private HashSet<String> set = new HashSet<String>();
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
		M = Integer.parseInt(st.nextToken());
		for(int i=0; i<N; i++) {
			st = getStringTokenizer();
			set.add(st.nextToken());
		}
		for(int i=0; i<M; i++) {
			st = getStringTokenizer();
			String temp = st.nextToken();
			if(set.contains(temp)) {
				cnt++;
				answers.add(temp);
			}
		}
	}
	
	private void close() throws IOException {
		br.close();
		bw.close();		
	}
	
	private void process() throws IOException {
		System.out.println(cnt);
		Collections.sort(answers);
		for(String word : answers)
			bw.write(word+"\n");
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
