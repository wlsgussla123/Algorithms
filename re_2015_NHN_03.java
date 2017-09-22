
import java.util.*;

class Main {
	public static boolean isVowel(String w) {
		int cnt=0;
		boolean check = false;
		
		for(int i=0; i<w.length(); i++) {
			if(w.charAt(i) == 'a' || w.charAt(i) == 'e' || w.charAt(i) == 'i' || w.charAt(i) == 'o' || w.charAt(i) == 'u') {
				cnt++;
				
				if(cnt>=2) {
					check = true;
					break;
				}
			} else {
				cnt = 0;
			}
		}
		
		return check;
	}
	
	public static boolean isConsonant(String w) {
		int cnt = 0;
		boolean check = false;
		
		for(int i=0; i<w.length(); i++) {
			if(w.charAt(i) == 'a' || w.charAt(i) == 'e' || w.charAt(i) == 'i' || w.charAt(i) == 'o' || w.charAt(i) == 'u') {
				cnt = 0;
			} else {
				cnt++;
				
				if(cnt>=3) {
					check = true;
					break;
				}
			}
		}
		
		return check;
	}

  public static void main(String[] args) {
		String input;
		Scanner sc = new Scanner(System.in);
		
		input = sc.nextLine();
		String[] words = input.split("\\s");
	
		int cntVowel = 0;
		int cntConsonant = 0;
		
		for(String word : words) {
			if(isVowel(word)) {
				cntVowel++;
			}
			
			if(isConsonant(word)) {
				cntConsonant++;
			}
		}
		
		System.out.println(cntVowel);
		System.out.println(cntConsonant);
	}
}
