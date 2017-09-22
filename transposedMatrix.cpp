/*
  전치 행렬로 바꾸기.
*/

#include <stdio.h>
#include <stdlib.h>

using namespace std;

int ** matrix;
int ** transposedMatrix;

int main(void) {
	int n;

	scanf("%d", &n);
	matrix = (int **)malloc(sizeof(int *) * n);
	transposedMatrix = (int **)malloc(sizeof(int *) * n);

	for (int i = 0; i < n; i++) {
		matrix[i] = (int *)malloc(sizeof(int) * n);
		transposedMatrix[i] = (int *)malloc(sizeof(int) * n);
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			scanf("%d", &matrix[i][j]);
		}
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			transposedMatrix[i][j] = matrix[j][i];
		}
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			printf("%d ", transposedMatrix[i][j]);
		}
		putchar('\n');
	}

	return 0;
}
