package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2098 {

	static int N;
	static int[][] map;
	static int[][] dp;
	static int full;
	static final int INF = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new int[N][1 << N];
		full = (1 << N) - 1;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
			Arrays.fill(dp[i], -1);
		}

		// 0번 도시부터 시작, 0번 도시 방문 표시
		System.out.println(tsp(0, 1));
	}

	static int tsp(int now, int visited) {

		// 모든 도시 방문
		if (visited == full) {
			if (map[now][0] == 0) return INF;
			return map[now][0];
		}

		// 이미 체크된 적이 있다면
		if (dp[now][visited] != -1) return dp[now][visited];

		dp[now][visited] = INF;

		for (int next = 0; next < N; next++) {

			// 이미 방문한 도시
			if ((visited & (1 << next)) != 0) continue;

			// 경로가 존재하지 않는 경우
			if (map[now][next] == 0) continue;

			dp[now][visited] = Math.min(
				dp[now][visited],
				tsp(next, visited | (1 << next)) + map[now][next]
			);
		}

		return dp[now][visited];
	}
}
