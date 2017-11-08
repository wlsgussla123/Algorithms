package test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/*
 * 작성자 : 박진현
 * 문제 : 백준 7885번 문제, 회사에 남아있는 사람
 * 자료구조를 사용하기 위한 문제로 선택
 */

public class Main {
	private static final Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		int n = sc.nextInt();
		HashMap<String, String> map = new HashMap<String, String>();		
		sc.nextLine(); // 버퍼 개행 잡아먹고
		
		String temp; 
		for(int i=0; i<n; i++) {
			temp = sc.nextLine(); // 임시 문자열 입력
			String[] result = temp.split("\\s");
			
			// 이름이 다시 나온건, 나갔다는 뜻
			if(map.containsKey(result[0])) {
				map.remove(result[0]);
			} else {
				map.put(result[0], result[1]); // 이름을 key, 상태를 value로				
			}
		}
		
		// 결과 리스트
		String[] result = new String[map.size()];
		
		// enter인 사람만 뽑자
		if(map.containsValue("enter")) {
			Iterator<String> itr = map.keySet().iterator();
			int index = 0;
			
			while(itr.hasNext()) {
				result[index++] = itr.next();
			}			
		}
				
		// 출력을 위해서
		Arrays.sort(result, Collections.reverseOrder());
		for(String str : result) {
			System.out.println(str);
		}
	}
}
