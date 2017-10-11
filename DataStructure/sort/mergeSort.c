/*
	작성자 : 박진현
	문제 : 자료구조 정렬 연습, 병합정렬 (MergeSort)
*/

#include <stdio.h>

int answer[8] = { 0, };
int N = 8;

void merge(int arr[], int left, int mid, int right) {
	int fIdx = left;
	int rIdx = mid + 1;
	int index = left; // 분할을 했을 때, 왼쪽 영역 오른쪽 영역으로 나누어진다. 이때, 시작점은 각각의 left가 시작점이므로 0이 아니다.

	// 왼쪽, 오른쪽을 비교하여 작은게 있으면 채우자.
	while (fIdx <= mid && rIdx <= right) {
		if (arr[fIdx] <= arr[rIdx]) {
			answer[index++] = arr[fIdx++];
		}
		else {
			answer[index++] = arr[rIdx++];
		}
	}

	// 왼쪽 오른쪽 비교해서 남은 부분은 어차피 정렬이 되어있으므로 쭈욱 넣자.
	if (fIdx <= mid) {
		for (int i = fIdx; i <= mid; i++) {
			answer[index++] = arr[i];
		}
	}
	else {
		for (int i = rIdx; i <= right; i++) {
			answer[index++] = arr[i];
		}
	}

	for (int i = left; i <= right; i++) {
		arr[i] = answer[i];
	}
}

void mergeSort(int arr[], int left, int right) {
	if (left < right) {
		int mid = (left + right) / 2;

		// 분할 코드
		mergeSort(arr, left, mid);
		mergeSort(arr, mid+1, right);
		merge(arr, left, mid, right);
	}
}

int main(void) {
	int arr[8] = { 8,3,2,7,1,5,4,6 };

	mergeSort(arr, 0, sizeof(arr) / sizeof(int) - 1);

	for (int i = 0; i < N; i++) {
		printf("%d ", arr[i]);
	}
	putchar('\n');

	return 0;
}
