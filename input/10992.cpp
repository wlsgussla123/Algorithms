/*
  문제 : 백준 10992 별찍기

  설명 : 인터넷에서 알고리즘 풀이에 대한 글을 읽다가 입출력 예제를 풀어보라고 해서 하게 됨
*/

#include <stdio.h>

int main(void) {
	int n;

	scanf("%d", &n);

	for (int i = 0; i < n-1; i++) {
		for (int j = n-1; j >= i; j--) {
			if (j == i) {
				printf("*");
				break;
			}
			printf(" ");
		}

		for (int k = 0; k <= i * 2 - 1; k++) {
			if (k == i * 2 - 1) {
				printf("*");
				break;
			}

			printf(" ");
		}

		putchar('\n');
	}

	for (int i = 0; i < n*2-1; i++) {
		printf("*");
	}
	putchar('\n');
		

	return 0;
}
