package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BJ25498 {

	static int N;              // 정점 개수
	static String S;           // 각 정점에 적힌 알파벳 문자열
	static ArrayList<Integer>[] adj; // 트리 인접 리스트

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 정점 개수 입력
		N = Integer.parseInt(br.readLine());

		// 각 정점의 알파벳 (0번 정점부터)
		S = br.readLine();

		// 인접 리스트 초기화
		adj = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
		}

		// 트리 간선 입력 (양방향)
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			adj[u].add(v);
			adj[v].add(u);
		}

		// 방문 체크 배열
		boolean[] visited = new boolean[N];

		// BFS를 위한 큐
		Queue<Integer> queue = new LinkedList<>();

		// 루트(1번 정점 = 0번 인덱스)부터 시작
		queue.add(0);
		visited[0] = true;

		// 정답 문자열
		StringBuilder answer = new StringBuilder();

		// 루트의 알파벳은 무조건 포함
		answer.append(S.charAt(0));

		// BFS 시작
		while (!queue.isEmpty()) {

			// 다음 깊이에 사용할 큐
			Queue<Integer> nextQueue = new LinkedList<>();

			// 다음 깊이에서 선택할 가장 큰 알파벳
			char maxChar = 0;

			// 현재 깊이에 있는 모든 노드 탐색
			while (!queue.isEmpty()) {
				int cur = queue.poll();

				// 현재 노드의 자식(인접 노드) 확인
				for (int nxt : adj[cur]) {
					if (!visited[nxt]) {
						visited[nxt] = true;
						char c = S.charAt(nxt);

						// 더 큰 알파벳을 발견한 경우
						if (c > maxChar) {
							maxChar = c;
							nextQueue.clear(); // 이전 후보 제거
							nextQueue.add(nxt);
						}
						// 같은 알파벳인 경우 함께 후보로 유지
						else if (c == maxChar) {
							nextQueue.add(nxt);
						}
					}
				}
			}

			// 다음 깊이에 갈 수 있는 노드가 있다면
			if (maxChar != 0) {
				// 가장 큰 알파벳을 정답에 추가
				answer.append(maxChar);

				// 다음 깊이 후보들로 큐 갱신
				queue = nextQueue;
			}
		}

		// 결과 출력
		System.out.println(answer.toString());
	}
}
