package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ16986 {

	static int N; // 손동작 수
	static int K; // 우승 조건 승수
	static int[][] winning; // 승패표
	static int[][] result; // 기영(1), 현수(2)의 손동작
	static boolean[] visited; // 지수(0번)의 손동작 사용 여부

	static boolean answer = false;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		winning = new int[N+1][N+1];
		visited = new boolean[N+1];
		result = new int[3][21]; // 0=지수, 1=기영, 2=현수

		// 승패표 입력
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				winning[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 기영(1), 현수(2) 입력
		for (int i = 1; i <= 2; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 20; j++) {
				result[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 지수는 손동작 순서가 없음 → 모든 순열 DFS로 만들기
		dfs(0, 0, new int[21]);

		System.out.println(answer ? 1 : 0);
	}

	/**
	 * order : 지수가 사용할 손동작 순서 (최대 20개)
	 */
	static void dfs(int depth, int count, int[] order) {
		if (answer) return;

		if (depth == N) {
			// 손동작 순서를 다 정했으면 게임 실행
			simulate(order);
			return;
		}

		// N개 중에서 사용 안 한 손동작을 선택
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				order[count] = i;
				dfs(depth + 1, count + 1, order);
				visited[i] = false;
			}
		}
	}

	/**
	 * 선택된 지수 순서(order)를 가지고 실제 게임 진행
	 */
	static void simulate(int[] order) {
		int[] index = new int[3]; // 각 플레이어의 현재 손동작 index
		int[] winCount = new int[3]; // 각 플레이어의 승수

		int p1 = 0; // 지수
		int p2 = 1; // 기영
		int wait = 2; // 대기자: 현수

		while (true) {

			// 지수 손동작 다 쓰면 종료
			if (index[0] >= N) return;

			// 기영, 현수 중 하나라도 손동작 20개 다 쓰면 종료
			if (index[1] >= 20 || index[2] >= 20) return;

			int h1 = getHand(p1, index, order);
			int h2 = getHand(p2, index, order);

			int resultVal = winning[h1][h2];

			int winner;
			if (resultVal == 2) winner = p1;
			else if (resultVal == 0) winner = p2;
			else {
				winner = Math.max(p1, p2);
			}

			winCount[winner]++;
			if (winCount[winner] >= K) {
				if (winner == 0) answer = true;
				return;
			}

			// 경기 끝나면 승자가 남고, 패자와 대기자가 교체됨
			int loser = p1 == winner ? p2 : p1;

			p1 = winner;
			p2 = wait;
			wait = loser;
		}
	}

	static int getHand(int player, int[] index, int[] order) {
		if (player == 0) return order[index[player]++];
		else return result[player][++index[player]];
	}
}
