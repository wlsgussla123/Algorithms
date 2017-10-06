/*
	작성자 : 박진현
	문제 : 카카오 채용 1차 테스트 문제
	작성일 : 17.10.06
	
	설명 : 노가다로 2시간 조금 넘게 걸린 문제... 다른 사람의 풀이법을 참조해야 할 필요가 있는 문제다.
*/

#include <stdio.h>
#include <iostream>
#include <string>
#include <sstream>
#include <vector>
#include <algorithm>

using namespace std;

int n, t, m;
string temp;
vector<string> timetable;
vector<int> minuteTable;

int lastBus = 540; // 09:00
string answer = "";

// HH:MM 형태의 타임테이블을 분으로 환산한 int형 벡터로 나타낸다.
void convertTimetable() {
	stringstream stream(temp);
	string buf;

	// 하나의 문자열로 받은 시간표 문자열 백터로 분리
	while (stream >> buf) {
		timetable.push_back(buf);
	}

	sort(timetable.begin(), timetable.end()); 
	
	for (int i = 0; i < timetable.size(); i++) {
		int hour = (((timetable[i][0] - '0') * 10) + (timetable[i][1] - '0')) * 60; // timetable 시간 변환
		int min = ((timetable[i][3] - '0') * 10) + (timetable[i][4] - '0'); // timetable을 분 변환

		minuteTable.push_back(hour + min);
	}
}

// 먼저 온 사람은 버스를 타고 간다. 
void fifoBus() {
	// 막차 전까지 보낼 사람 일단 보냅시다.
	for (int i = 540; i < lastBus; i = i + t) {
		for (int j = 0; j < m; j++) {
			// minutable 범위를 넘어서 참조하지 않도록 
			// 기다리는 사람 웨이팅 시간이 지금 도착 버스(i)에 탈수 있는 시간대면 타버리자
			if (j < minuteTable.size() && minuteTable[j] <= i) {
				minuteTable.erase(minuteTable.begin());
			}
		}
	}
}

int main(void) {
	scanf("%d %d %d", &n, &t, &m);
	getc(stdin);
	getline(cin, temp); // 시간표 입력, 분할하기 때문에 임시 변수임

	convertTimetable(); // HH:MM -> int형 분으로 변환

	//// 최대한 늦게 가기 때문에 막차 시간으로 조정
	for (int i = 1; i < n; i++) {
		lastBus += t; // 주인공은 게으르기 때문에 최대한 막차를 탈 것.
	}

	fifoBus(); // 먼저 온 사람은 버스 타고 감. (난 막차라 상관없음)
	
	if (minuteTable.size() < m) {
		// 버스 최대 탑승인원 보다 남은 사람이 적기 때문에 그냥 막차를 타면 됨
		string hour = to_string(lastBus / 60);
		string min = to_string(lastBus % 60);

		if (atoi(hour.c_str()) < 10)
			hour = '0' + hour;
		if (atoi(min.c_str()) < 10)
			min = '0' + min;

		answer = hour + ":" + min;
	}
	else if (n == m && n == 1) {
		string hour = to_string(lastBus / 60);
		string min = to_string(lastBus % 60);

		if (atoi(hour.c_str()) < 10)
			hour = '0' + hour;
		if (atoi(min.c_str()) < 10)
			min = '0' + min;

		answer = hour + ":" + min;
	}
	else {
		// 가장 늦게 나오는 애보다 1분 먼저 오자
		int maxTime = *max_element(minuteTable.begin(), minuteTable.end());
		string hour = to_string((maxTime - 1) / 60);
		string min = to_string((maxTime - 1) % 60);

		if (atoi(hour.c_str()) < 10)
			hour = '0' + hour;
		if (atoi(min.c_str()) < 10)
			min = '0' + min;

		answer = hour + ":" + min;
	}

	cout << answer << endl;
	
	return 0;
}
