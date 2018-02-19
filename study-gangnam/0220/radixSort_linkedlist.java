package algo;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static final Scanner sc = new Scanner(System.in);
	public static int N;
	public static void main(String[] args) {
		N = sc.nextInt();
		int[] arr = new int[N];
		for(int i=0; i<N; i++) arr[i] = sc.nextInt();
		radixSort(arr);
		
		for(int i=0; i<arr.length; i++) System.out.println(arr[i]);
	}
	
	// 가장 큰 자리수 반환
	public static int findMaxDigits(int[] arr) {
		int max = 0;
		int cnt = 0;
		for(int i=0; i<N; i++) {
			int num = arr[i];
			cnt = 0;
			while(num>0) {
				num /= 10;
				cnt++;
			}
			if(cnt > max) max = cnt;
		}
		return max;
	}
	
	public static void radixSort(int[] arr) {
		int d = findMaxDigits(arr); // 가장 큰 자리수 가져오고
		int exp = 1;
		ArrayList<Integer>[] radix = new ArrayList[10];
		for(int i=0; i<10; i++) radix[i] = new ArrayList<Integer>();
		
		// 기수정렬 단계별로 (각 자리수 별로)
		for(int i=0; i<d; i++, exp*=10) {
			// 1,10,100....N의 자리수 별로 각 자리수가 해당하는 버킷에 추가한다.
			for(int j=0; j<N; j++) {
				int bucket = (arr[j]/exp) % 10;
				radix[bucket].add(arr[j]);
			}
			
			// 각 버킷을 순차적으로 돌면서 배열에 삽입
			int pos = 0;
			for(int j=0; j<10; j++) {
				for(int k=0; k<radix[j].size(); k++) {
					arr[pos++] = radix[j].get(k);
				}
				radix[j].clear();
			}
		}
	}
}
