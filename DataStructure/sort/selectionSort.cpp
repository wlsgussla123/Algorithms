/*
	작성자 : 박진현
	문제 : 자료구조 선택정렬 구현
*/

#include <stdio.h>

int main(void) {
	int arr[5] = { 2,5,3,1,4 };
	int N = 5;
	
	int min = 0;
	for (int i = 0; i < N; i++) {
		min = i;
		for (int j = i; j < N; j++) {
			// 처음에 min > arr[j]로 하고 min으로 했다가 잘못된 결과 나옴.
			if (arr[min] > arr[j]) {
				min = j;
			}
		}

		// min 으로 스왑해봐야 아무 의미가 없음. min을 갖고있는 배열의 인덱스에 접근해서 바꿔야지
		int temp = arr[i];
		arr[i] = arr[min];
		arr[min] = temp;
	}

	for (int i = 0; i < N; i++) {
		printf("%d ", arr[i]);
	}
	putchar('\n');

	return 0;
}
