import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static final Scanner sc = new Scanner(System.in);
	public static int N;
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st = null;
	
	public static StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	public static void main(String[] args) throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		int max = 0;
		for(int i=0; i<N; i++) {
			st = getStringTokenizer();
			arr[i] = Integer.parseInt(st.nextToken());
			if(arr[i] > max) max = arr[i];
		}
		countingSort(arr,max);
	}
	
	public static void countingSort(int[] arr, int max) throws IOException {
		int[] count = new int[max+1];
		int len = arr.length;
		int[] result = new int[len];
		
		for(int i=0; i<len; i++) {
			count[arr[i]]++;
		}
		
		for(int i=1; i<max; i++) {
			count[i+1] += count[i];
		}

		for(int i=len-1; i>=0; i--) {
			result[count[arr[i]]-1] = arr[i];
			count[arr[i]]--;
		}

		for(int i=0; i<len; i++) bw.write(result[i]+"\n");
		bw.close();
	}
}
