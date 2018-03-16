package algo;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	static int Answer;
	static int[] people;

	public static void main(String args[]) throws Exception	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			int N = sc.nextInt();
			people = new int[N];
			for(int i=0; i<N; i++) {
				people[i] = sc.nextInt();
			}
			Arrays.sort(people);
			
			int max = 0;
			for(int i=0; i<N; i++) {
				int temp = people[i] + (N-i);
				max = max > temp ? max : temp;
			}
			
			Answer = 0;
			for(int i=0; i<N; i++) {
				if(people[i] + N >= max) {
					Answer++;
				}
			}
			
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
}
