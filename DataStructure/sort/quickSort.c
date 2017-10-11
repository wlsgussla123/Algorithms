/*
	작성자 : 박진현
	문제 : 자료구조 정렬 구현, 퀵소트
*/

#include <stdio.h>

void swap(int arr[], int x, int y) {
	int temp = arr[x];
	arr[x] = arr[y];
	arr[y] = temp;
}

// pivot의 위치를 반환
int partition(int arr[], int left, int right) {
	int pivot = arr[left]; // pivot을 임의로 지정. 사실 중간값이 되는 것이 가장 좋지만, 일단은 가장 왼쪽 것으로 정한다.
	int low = left + 1; // low는 pivot을 제외한 가장 왼쪽
	int high = right; // high는 pivot을 제외한 가장 오른쪽

	// 교차되기 전에 pivot을 위한 자리 구축
	while (low <= high) {
		// pivot보다 큰 값을 찾기 위하여, pivot이 가장 클 수도 있으므로 범위 설정
		while (pivot >= arr[low] && low <= right) {
			low++;
		}

		// pivot보다 작은 값을 찾기 위하여, pivot을 제외하고 검사하도록
		while (pivot <= arr[high] && high >= (left + 1)) {
			high--;
		}
		
		if (low <= high) {
			swap(arr, low, high);
		}
	}

	swap(arr, left, high);
	return high;
}

// 퀵소트. 먼저 고정하게 될 pivot을 구하고 pivot을 기점으로 왼쪽 오른쪽을 또 나눈다
void quickSort(int arr[], int left, int right) {
	if (left < right) {
		int pivot = partition(arr, left, right);
		quickSort(arr, left, pivot - 1);
		quickSort(arr, pivot + 1, right);
	}
}

int main(void) {
	int arr[8] = { 3,4,2,1,7,5,6,8 };
	int N = sizeof(arr) / sizeof(int);

	quickSort(arr, 0, N - 1);
	for (int i = 0; i < N; i++) {
		printf("%d ", arr[i]);
	}
	putchar('\n');

	return 0;
}
