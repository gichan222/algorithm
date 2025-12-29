package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2072 {

	static final int SIZE = 19;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[][] board = new int[SIZE][SIZE];   // 0: 없음, 1: 흑, 2: 백
		int[][] order = new int[SIZE][SIZE];   // 돌이 놓인 순서

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;

			board[x][y] = (i % 2 == 0) ? 1 : 2;
			order[x][y] = i + 1; // 1부터 시작
		}

		int answer = Integer.MAX_VALUE;

		// 4방향 (반대 방향은 시작점 체크로 처리)
		int[] dx = {0, 1, 1, -1};
		int[] dy = {1, 0, 1, 1};

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (board[i][j] == 0) continue;

				for (int d = 0; d < 4; d++) {

					int px = i - dx[d];
					int py = j - dy[d];

					// 시작점이 아니면 스킵
					if (px >= 0 && px < SIZE && py >= 0 && py < SIZE) {
						if (board[px][py] == board[i][j]) continue;
					}

					int cnt = 1;
					int maxOrder = order[i][j];

					for (int k = 1; k <= 5; k++) {
						int nx = i + dx[d] * k;
						int ny = j + dy[d] * k;

						if (nx < 0 || nx >= SIZE || ny < 0 || ny >= SIZE) break;
						if (board[nx][ny] != board[i][j]) break;

						cnt++;
						maxOrder = Math.max(maxOrder, order[nx][ny]);
					}

					// 정확히 5목인지 확인 (6목 방지)
					if (cnt == 5) {
						int nx = i + dx[d] * 5;
						int ny = j + dy[d] * 5;

						if (nx < 0 || nx >= SIZE || ny < 0 || ny >= SIZE ||
							board[nx][ny] != board[i][j]) {
							answer = Math.min(answer, maxOrder);
						}
					}
				}
			}
		}

		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}
}
