import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Main {

    /*
     * Description :
     * Time complexity : O(N^2)
     * Space complexity : O(N)
     */
    public boolean match(String pattern, String s) {
        // TODO implement your codes to here.
    	boolean result = true;
    	char[] pChar = pattern.toCharArray();
    	char[] sChar = s.toCharArray();
    	
    	int strIdx = 0;
    	for(int i=0; i<pChar.length; i++) {
    		if(strIdx >= s.length()) break;
    		
    		switch(pChar[i]) {
    		case '*':
    			for(int j=strIdx; j<sChar.length; j++) {
    				// 별 뒤에 나올게 없으면 실패
    				if(i+1 == pChar.length) {
    					result = false;
    					break;
    				}
    			
    				// ?면 아무거나 와도 되고, string과 매치하면 *만큼 나와도 되기 때문에
    				if(pChar[i+1] == '?' || pChar[i+1] == sChar[j]) {
    					strIdx++;
    				} else {
    					break; // 매칭 안 하면 깨져야지.
    				}
    			}
    			
    			i = i+1; //index를 맞추기 위해
    			break;
    		case '?':
    			strIdx++; // ?가 나오면 무조건 매칭 (아무거나 될 수 있으므로)
    			break;
    		
    		// 소문자일 경우
    		default: 
    			if(pChar[i] == sChar[strIdx]) {
    				strIdx++;
    				break;
    			} else {
    				result = false;
    			}
    		}
    		
    		// 이미 result가 false인 것이 나왔으면 돌 필요 없이 끝내자.
    		if(result == false || strIdx > s.length()) break;
    	}
    	
    	// 다 돌았으면 완성
    	if(strIdx == s.length()) result = true;
    	
        return result;
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        try (Scanner scan = new Scanner(System.in)) {
            int TC = Integer.parseInt(scan.nextLine());
            for (int t = 0; t < TC; t++) {
                String[] strs = scan.nextLine().split(" ");
                System.out.println(main.match(strs[0], strs[1]) ? "O" : "X");
            }
        }
    }
}
