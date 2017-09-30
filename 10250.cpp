/*
  백준 10250
*/
#include <stdio.h>
#include <stdlib.h>

int main(void) {
	int T, H, W, N;
	int i, j, k=0;
	int * x;
	int * y;
	
	scanf("%d", &T);

	x = (int *)malloc(sizeof(int) * T);
	y = (int *)malloc(sizeof(int) * T);

	while (k < T) {
		do {
			scanf("%d %d %d", &H, &W, &N);
		} while (!(H >= 1) || !(W <= 99) || !((N >= 1) && N <= H*W));

		for (i = 1; i <= W; i++) {
			for (j = 1; j <= H; j++) {
				if (N == 0) {
					break;
				}
				N--;
			}
			if (N == 0) {
				j--;
				break;
			}
		}

		x[k] = i;
		y[k] = j;

		k++;
	}

	for (i = 0; i < k; i++) {
		switch (x[i]) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
			printf("%d0%d\n", y[i], x[i]);
			break;
		default:
			printf("%d%d\n", y[i], x[i]);
		}
	}

	return 0;
}
