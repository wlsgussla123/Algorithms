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
    	private int N=4;
    	private int[] arr = {1,2,3,4};
    	private StringTokenizer st = null;
    	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	private StringTokenizer getStringTokenizer() throws IOException {
    		return new StringTokenizer(br.readLine(), " ");
    	}
    	
    	private void swap(int a, int b) {
    		int tmp;
    		tmp = arr[a];
    		arr[a] = arr[b];
    		arr[b] = tmp;
    	}
    	
    	private void permutation(int depth, int n, int k) {
    		if(depth==k) {
    			for(int i=0; i<n; i++) {
    				System.out.print(arr[i] + " ");
    			}
    			System.out.println();
    			return;
    		}
    		
    		for(int i=depth; i<n; i++) {
    			swap(depth, i);
    			permutation(depth+1, n, k);
    			swap(depth, i);
    		}
    	}

    	public void run() throws IOException {
    		permutation(0, N, N);
    		close();
    	}
    	
    	private void close() throws IOException {
    		bw.close();
    		br.close();
    	}    	
    }
}
