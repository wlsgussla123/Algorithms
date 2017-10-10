/*
	작성자 : 박진현
	문제 : 백준, 1057번 문제
*/

#include <stdio.h>

int main(void) {
	int players[100000] = { 0, };
	int N, a, b;
	int answer = 0;

	scanf("%d %d %d", &N, &a, &b);
	while (a != b) {
		a = a / 2 + a % 2;
		b = b / 2 + b % 2;
		answer++;
	}

	printf("%d\n", answer);

	return 0;
}
