/*
	작성자 : 박진현
	문제 : 2017_NHN_02 번 참조
*/

#include <stdio.h>
#include <string.h>

using namespace std;

/*
	encrypt secretword 3 helloworld
	-> cspkfcgzin

	decrypt secretword 3 cspkfcgzin
	-> helloworld
*/

char sentence[4][31];
char temp[31];
char answer[31];

// 암호화는 문자를 왼쪽으로 민다.
void encryptRotate() {
	int len = strlen(sentence[3]);
	int rotate = sentence[2][0] - '0';

	for (int i = 0; i < len; i++) {
		if (i - rotate >= 0) {
			answer[i - rotate] = temp[i];
		}
		else {
			answer[len + (i - rotate)] = temp[i];
		}
	}
	answer[len] = '\0';

	printf("%s\n", answer);
}

void encrypt() {
	int i;

	for (i = 0; i < strlen(sentence[3]); i++) {
		temp[i] = ((sentence[3][i] - 'a' + sentence[1][i] - 'a') % 26) + 'a';
	}
	temp[i] = '\0';

	encryptRotate();
}

// 암호화는 문자를 왼쪽으로 밀었으니, 복호화는 오른쪽으로 밀자.
void decryptRotate() {
	int len = strlen(sentence[3]);
	int rotate = sentence[2][0] - '0';

	for (int i = 0; i < len; i++) {
		if (i + rotate < len) {
			temp[i + rotate] = sentence[3][i];
		}
		else {
			temp[i + rotate - len] = sentence[3][i];
		}
	}
}

void decrypt() {
	int i;

	decryptRotate(); // 완료
//	printf("%s\n", temp);

	int len = strlen(sentence[3]);
	for (int i = 0; i < len; i++) {
		// 괄호처리 잘 하자. 이거 때문에 1시간 고생했따.
		if ((temp[i] - 'a') - (sentence[1][i] - 'a') >= 0) {
			answer[i] = (temp[i] - 'a') - (sentence[1][i] - 'a') + 'a';
		}
		else {
			answer[i] = (26 + (temp[i] - 'a') - (sentence[1][i] - 'a')) + 'a';
		}

		printf("%c\n", answer[i]);
	}

	answer[len] = '\0';
	printf("%s\n", answer);
}

int main(void) {
	for (int i = 0; i < 4; i++) {
		scanf("%s", sentence[i]);
	}

	if (!strcmp(sentence[0], "encrypt")) {
		encrypt();
	}
	else if (!strcmp(sentence[0], "decrypt")) {
		decrypt();
	}

	return 0;
}
