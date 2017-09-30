/*
	작성자 : 박진현
	문제 : 크랙킹 코딩 인터뷰 1-6
	상세 : 배열을 90도 회전하라.
*/

#include <stdio.h>

using namespace std;

int arr[100][100];

int answer_arr[100][100];
int N;

void rotate() {
	int len = N - 1;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j <N; j++) {
			answer_arr[len--][i] = arr[i][j];
		}

		len = N - 1;
	}
}

int main(void) {
	scanf("%d", &N);

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			scanf("%d", &arr[i][j]);
		}
	}

	rotate();

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			printf("%d ", answer_arr[i][j]);
		}
		putchar('\n');
	}

	return 0;
}
