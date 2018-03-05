package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
    private String[] card = {"AABBCCDD", "KKKKJJJJ", "MOMOMOMO"};
    private String[] word = {"AAAKKKKKMMMMM", "ABCDKJ"};
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer st = null;
    
    private StringTokenizer getStringTokenizer() throws IOException {
        return new StringTokenizer(br.readLine(), " ");
    }
    
    private boolean checkWord(String[] card, String word) {
        boolean flag = true;
        boolean[] c_visited = new boolean[card.length];
        boolean[] w_visited = new boolean[word.length()];
        int idx = 0;
        
        char[][] temp = new char[card.length][8];
        for(int i=0; i<card.length; i++) {
            temp[i] = card[i].toCharArray();
        }
        
        while(flag) {
            flag = false;
            for(int i=0; i<temp.length; i++) {
                for(int j=0; j<temp[i].length; j++) {
                    if(temp[i][j] == word.charAt(idx)) {
                        w_visited[idx++] = true;
                        c_visited[i] = true;
                        temp[i][j] = '0';
                        flag = true;
                        break;
                    }
                }
                if(idx == word.length()) break;
            }
            if(idx == word.length()) break;
        }
    
        for(int i=0; i<c_visited.length; i++) {
            if(!c_visited[i]) return false;
        }
        
        for(int i=0; i<w_visited.length; i++) {
            if(!w_visited[i]) return false;
        }
        
        return true;
    }
    
    private String[] solution(String[] card, String[] word) {
        String[] result = new String[word.length];
        int idx = 0;
                
        for(int i=0; i<word.length; i++) {
            if(checkWord(card, word[i])) {
                result[idx++] = word[i];
            }
        }
        
        return result;
    }
    
    public void run() throws IOException {
        String[] result = solution(card, word);
        boolean flag = false;
        
        for(int i=0; i<result.length; i++) {
            if(result[i] == null) continue;
            System.out.println(result[i]);
            flag = true;
        }
        
        if(!flag) System.out.println("-1");
    }
}
 
public class Main {
    public static void main(String args[]) throws IOException {
        Task task = new Task();
        task.run();
    }
}
