# 최단경로 알고리즘
그래프 이론에서 최단 경로 문제란 가장 짧은 경로에서 두 꼭짓점을 찾는 문제로서, 가중 그래프에서는 구성하는 변들의 가중치 합이 최소가 되도록 하는 경로를 찾는 문제이다.  
- **단일-출발 최단 경로 문제** : 단일 꼭짓점 v에서 출발하여 그래프 내의 모든 다른 꼭짓점들에 도착하는 가장 짧은 경로를 찾는 문제.  
- **단일-도착 최단 경로 문제** : 모든 꼭짓점들로부터 출발하여 그래프 내의 한 단일 꼭짓점 v로 도착하는 가장 짧은 경로를 찾는 문제.  
- **전체-쌍 최단 경로 문제** : 그래프 내의 모든 꼭짓점 쌍들 사이의 최단 경로를 찾는 문제.

## 다익스트라 알고리즘  
- 하나의 정점에서 다른 모든 정점까지의 최단 경로를 구하는 문제

## 벨먼-포드 알고리즘  
- 다익스트라와 마찬가지로 시작점을 정해 주면 다른 모든 정점으로의 최단 경로를 구함.
- 다익스트르와 다르게 음의 가중치도 계산할 수 있지만, 시간 복잡도가 더 높기때문에 주의

## 플로이드-워셜 알고리즘  
- 전체-쌍 최단 경로 문제를 풀 수있다. (모든 꼭짓점 쌍들 사이의 최단 경로)
