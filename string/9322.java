package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N;
	private String[] pKey1;
	private String[] pKey2;
	private String[] secret;
	private String[] plain;
	private int[] answerIdx;
	private StringTokenizer st = null;
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		pKey1 = new String[N];
		pKey2 = new String[N];
		secret = new String[N];
		plain = new String[N];
		answerIdx = new int[N];
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
		st = getStringTokenizer();
		for(int i=0; i<N; i++) {
			pKey1[i] = st.nextToken();
		}
		st = getStringTokenizer();
		for(int i=0; i<N; i++) {
			pKey2[i] = st.nextToken();
		}
		st = getStringTokenizer();
		for(int i=0; i<N; i++) {
			secret[i] = st.nextToken();
		}
	}
	
	private void solution() throws IOException {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(pKey2[i].equals(pKey1[j])) {
					plain[j] = secret[i];
					break;
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			System.out.print(plain[i] + " ");
		}
		System.out.println();
	}
	
	public void run() throws IOException {
		st = getStringTokenizer();
		int T = Integer.parseInt(st.nextToken());
		for(int i=1; i<=T; i++) {
			input();
			solution();
		}
		close();
	}
	
	private void close() throws IOException {
		bw.close();
		br.close();
	}
}

public class Main {
    public static void main(String args[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}
