/*
  길이가 n인 배열에 1부터 n까지 숫자가 중복 없이 헌 번씩 들어있는지를 확인하려고 한다.
  1부터 n까지 숫자가 중복 없이 한 번씩 들어있는 경우 true, 아닌 경우 false 반환.

  [4,1,3,2] -> true
  [4,1,3] -> false
*/

class Solution {
    public boolean solution(int[] arr) {
        boolean answer = true;
        int sum = 0;
        int total = 0;
        int size = arr.length;
        int[] temp = new int[arr.length+1];
        
        for(int i=0; i<size; i++) {
            if(arr[i] > size) {
                answer = false;
                break;
            }
            
            if(arr[i] <= size) {
                temp[arr[i]] = arr[i];
            }
           
            sum = sum + size - arr[i];
            total = total + i;
        }

        for(int i=1; i <= size; i++) {
            if(temp[i] == 0) {
                answer = false;
                break;
            }
        }
        
        return answer;
    }
}
