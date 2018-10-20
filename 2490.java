import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().run();
    }
}

class Solution {
    private int[] arr =  new int[4];
    private InputClass inputClass = new InputClass();
    private int T = 0;


    public void run() throws IOException {
        while(T<3) {
            inputClass.input();
            solve();
            T++;
        }
        inputClass.close();
    }

    public void solve() throws IOException {
        int len = arr.length;
        int cnt = 0;

        for(int i=0; i<len; i++) {
            if(arr[i] == 0) {
                cnt++;
            }
        }

        switch(cnt) {
            case 0:
                inputClass.write('E');
                break;
            case 1:
                inputClass.write('A');
                break;
            case 2:
                inputClass.write('B');
                break;
            case 3:
                inputClass.write('C');
                break;
            case 4:
                inputClass.write('D');
                break;
        }
    }

    class InputClass {
        private BufferedWriter bw;
        private BufferedReader br;
        private StringTokenizer st;

        public InputClass() {
            bw = new BufferedWriter(new OutputStreamWriter(System.out));
            br = new BufferedReader(new InputStreamReader(System.in));
            st = null;
        }

        private StringTokenizer getStringTokenizer() throws IOException {
            return new StringTokenizer(br.readLine(), " ");
        }

        public void input() throws IOException {
            st = getStringTokenizer();
            for(int i=0; i<4; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
        }

        public void write(char content) throws IOException {
            bw.write(content + "\n");
        }

        public void close() throws IOException {
            bw.close();
            br.close();
        }
    }
}
