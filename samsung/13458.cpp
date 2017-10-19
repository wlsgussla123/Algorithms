/*
	작성자 : 박진현
	문제 : 백준 13458번, 시험 감독
*/

#include <iostream>

using namespace std;

int A[1000001] = { 0, };
long long answer = 0;

int main(void) {
	int N, B, C;

	scanf("%d", &N);
	for (int i = 0; i < N; i++) {
		scanf("%d", &A[i]);
	}
	scanf("%d %d", &B, &C);

	int index = 0;
	while (index < N) {
		A[index] -= B;
		answer++;

		while (A[index] > 0) {
			answer = answer + (long long)A[index] / C;
			A[index] = A[index] % C;

			if (A[index] > 0 && A[index] < C) {
				answer++;
				break;
			}
		}
		index++;
	}

	cout << answer << endl;

	return 0;
}
