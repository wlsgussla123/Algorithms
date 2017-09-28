/*
  작성자 : 박진현
  문제 : 2751, 수 정렬하기, 자료구조

*/
#include <stdio.h>
#include <stdlib.h>

int Partition(int arr[], int left, int right, int pivotIndex);
void QuickSort(int arr[], int left, int right);
void Swap(int * x, int * y);

int main(void) {
	int i,n;
	int * arr;

	scanf("%d", &n);
	arr = (int *)malloc(sizeof(int) * n);
	if (arr == NULL) {
		printf("Allocation Error\n");
		return 1;
	}

	for (i = 0; i < n; i++) {
		scanf("%d", &arr[i]);
	}

	QuickSort(arr, 0, n - 1);
	for (i = 0; i < n; i++) {
		printf("%d\n", arr[i]);
	}

	return 0;
}

int Partition(int arr[], int left, int right, int pivotIndex) {
	int pivotValue = arr[pivotIndex];
	int storeIndex = left;
	int i;

	Swap(&arr[pivotIndex], &arr[right]);
	for (i = left; i <= right-1; i++) {
		if (arr[i] <= pivotValue) {
			Swap(&arr[i], &arr[storeIndex]);
			storeIndex = storeIndex + 1;
		}
	}
	Swap(&arr[right], &arr[storeIndex]);

	return storeIndex;
}

void QuickSort(int arr[], int left, int right) {
	if (right > left) {
		int pivotIndex = (left+right) / 2;
		int pivotNewIndex = Partition(arr, left, right, pivotIndex);
		QuickSort(arr, left, pivotNewIndex - 1);
		QuickSort(arr, pivotNewIndex + 1, right);
	}
}

void Swap(int * x, int * y) {
	int temp;

	temp = *x;
	*x = *y;
	*y = temp;
}
