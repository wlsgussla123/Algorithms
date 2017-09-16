/*
  카카오 모의테스트 01번
   자연수 N이 주어지면, N의 각 자릿수의 합을 구해서 return 하는 solution 함수를 만들어 주세요.
  예를들어 N = 123이면 1 + 2 + 3 = 6을 return 하면 됩니다.
  작성자 : 박진현
*/
import java.util.*;

public class Solution {
	public int solution(int n) {
	    int tmp = 0, sum = 0;
        int quotient = 1;
        
        tmp = n;
        while(tmp >= 10) {
            tmp = tmp / 10;
            quotient *= 10;
        }
    
        while(quotient >= 1) {
            sum += n / quotient;
            n = n % quotient;
            quotient = quotient / 10;            
        }
        
		return sum;
	}
}
