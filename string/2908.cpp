/*
  작성자 : 박진현
  문제 : 백준 2908번. 문자열 갖고 놀기
*/
#include <stdio.h>
#include <iostream>
#include <string.h>
#include <algorithm>

using namespace std;

void reverse(char * num) {
	int j = strlen(num) - 1;
	char temp;

	for (int i = 0; i < strlen(num)/2; i++) {
		temp = num[i];
		num[i] = num[j];
		num[j] = temp;
		
		j--;
	}
}

int main(void) {
	char num1[4];
	char num2[4];
	
	scanf("%s %s", num1, num2);

	reverse(num1);
	reverse(num2);

	printf("%d\n", max(atoi(num1), atoi(num2)));

	return 0;
}
