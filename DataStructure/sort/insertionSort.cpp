/*
	작성자 : 박진현
	문제 : 자료구조 삽입정렬 구현 연습
*/

#include <stdio.h>

int main(void) {
	int arr[4] = { 3,4,2,1 };
	int N = 4;
	int cur = 0;

	int i, j;

	for (i = 1; i < N; i++) {
		cur = arr[i];
		for (j = i - 1; j >= 0; j--) {
			if (arr[j] > cur) {
				arr[j + 1] = arr[j];
			}
			else {
				break;
			}
		}

		arr[j + 1] = cur;
	}

	for (int i = 0; i < N; i++) {
		printf("%d ", arr[i]);
	}

	return 0;
}
