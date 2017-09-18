/*
 1차원 직선상에 존재하는 여러 개의 점의 좌표가 주어진다. 주어지는 점들 중 가장 가까운 거리에 있는 두 점 쌍을 하나 출력하시오.


 서로 거리가 가장 가까운 두 좌표를 한 줄에 공백으로 구분하여 출력한다.

두 좌표 중 작은 값을 앞에 출력하고 더 큰 값을 뒤에 출력한다.
거리가 같은 쌍이 여러개가 존재할 경우 두 좌표의 합이 최소가 되는 쌍만을 출력한디.

ex) 6
6 20 34 8 38 40 -> 6 8
*/


#include <iostream>
#include <stdio.h>
#include <stdlib.h>

using namespace std;

int shortestDistance(int a, int b) {
	return a > b ? a - b : b - a;
}

int main() {
	int n;
	int i, j;
	int * arr;

	int result1 = 0;
	int result2 = 0;
	int min = 0, temp = 0;
	
	scanf("%d", &n);
	arr = (int *)malloc(sizeof(int) * n);
	
	for(i=0; i<n; i++) {
		scanf("%d", &arr[i]);
	}
	
	min = shortestDistance(arr[0], arr[1]);
	result1 = arr[0] > arr[1] ? arr[0] : arr[1];
	result2 = arr[0] > arr[1] ? arr[1] : arr[0];
	
	for(i=0; i<n; i++) {
		for(j=0; j<n; j++) {
			if(i==j) 
				continue;

			temp = shortestDistance(arr[i], arr[j]);
			
			if(min > temp) {
				min = temp;
				result1 = arr[i] > arr[j] ? arr[i] : arr[j];
				result2 = arr[i] > arr[j] ? arr[j] : arr[i];
			}
		}
	}
	
	printf("%d\n", min);
	printf("%d %d\n", result2, result1);

  return 0;
}
