/*
	작성자 : 박진현
	문제 : 자료구조 정렬 구현 준비
*/

#include <stdio.h>

int main(void) {
	int arr[5] = { 2,3,5,1,4 };
	int N = 5;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N - 1 - i; j++) {
			if (arr[j] > arr[j + 1]) {
				int temp = arr[j];
				arr[j] = arr[j + 1];
				arr[j + 1] = temp;
			}
		}
	}

	for (int i = 0; i < N; i++) {
		printf("%d ", arr[i]);
	}
	putchar('\n');

	return 0;
}
