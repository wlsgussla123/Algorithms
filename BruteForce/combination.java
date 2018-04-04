package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException {
    	new Task().run();
    }
    
    static class Task {
    	private int cnt = 0;
    	private int N=5, R=3;
    	private int[] arr = {1,2,3,4,5};
    	private int[] tr;
    	private StringTokenizer st = null;
    	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	private StringTokenizer getStringTokenizer() throws IOException {
    		return new StringTokenizer(br.readLine(), " ");
    	}
    	
    	private void combination(int[] tr, int n, int r) {
    		if(r==0) {
    			for(int i=0; i<R; i++) {
    				System.out.print(tr[i] + " ");
    			}
    			System.out.println();
    			return;
    		}
    		if(n<r) return;
    		tr[r-1] = arr[n-1];
    		combination(tr, n-1, r-1);
    		combination(tr, n-1, r);
    	}

    	public void run() throws IOException {
    		tr = new int[R];
    		combination(tr, N, R);
    		close();
    	}
    	
    	private void close() throws IOException {
    		bw.close();
    		br.close();
    	}    	
    }
}
