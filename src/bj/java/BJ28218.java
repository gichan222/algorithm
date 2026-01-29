package bj.java;

import java.io.*;
import java.util.*;

public class BJ28218 {

	static int N, M, K;
	static boolean[][] open;
	static boolean[][] win;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		open = new boolean[N][M];
		win = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				open[i][j] = (s.charAt(j) == '.');
			}
		}

		// (N-1, M-1)은 Losing
		win[N - 1][M - 1] = false;

		for (int i = N - 1; i >= 0; i--) {
			for (int j = M - 1; j >= 0; j--) {
				if (!open[i][j]) continue;
				if (i == N - 1 && j == M - 1) continue;

				boolean canWin = false;

				// 아래
				if (i + 1 < N && open[i + 1][j] && !win[i + 1][j]) {
					canWin = true;
				}

				// 오른쪽
				if (!canWin && j + 1 < M && open[i][j + 1] && !win[i][j + 1]) {
					canWin = true;
				}

				// 대각선 점프 (중간 칸 무시!)
				if (!canWin && K > 0) {
					for (int d = 1; d <= K; d++) {
						int ni = i + d;
						int nj = j + d;
						if (ni >= N || nj >= M) break;
						if (!open[ni][nj]) continue; // ⭐ 중요
						if (!win[ni][nj]) {
							canWin = true;
							break;
						}
					}
				}

				win[i][j] = canWin;
			}
		}

		int Q = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;

			sb.append(win[x][y] ? "First\n" : "Second\n");
		}

		System.out.print(sb);
	}
}