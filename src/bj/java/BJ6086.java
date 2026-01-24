package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ6086 {

	static int H, W;
	static boolean[][] map;
	static int[][][] dp; // dp[r][c][dir]
	static int[] dx = {-1, 1, 0, 0}; // 상 하
	static int[] dy = {0, 0, -1, 1}; // 좌 우
	static Point start, end;
	static final int INF = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken()); // 열
		H = Integer.parseInt(st.nextToken()); // 행

		map = new boolean[H][W];
		dp = new int[H][W][4];

		for (int i = 0; i < H; i++) {
			String line = br.readLine();
			for (int j = 0; j < W; j++) {
				Arrays.fill(dp[i][j], INF);
				char c = line.charAt(j);
				if (c == 'C') {
					map[i][j] = true;
					if (start == null) start = new Point(i, j, -1);
					else end = new Point(i, j, -1);
				} else if (c == '.') {
					map[i][j] = true;
				}
			}
		}

		Deque<Point> dq = new ArrayDeque<>();

		// 시작점: 4방향 모두 비용 0
		for (int d = 0; d < 4; d++) {
			dp[start.r][start.c][d] = 0;
			dq.add(new Point(start.r, start.c, d));
		}

		while (!dq.isEmpty()) {
			Point cur = dq.pollFirst();

			for (int nd = 0; nd < 4; nd++) {
				int nr = cur.r + dx[nd];
				int nc = cur.c + dy[nd];

				if (nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
				if (!map[nr][nc]) continue;

				int cost = dp[cur.r][cur.c][cur.dir]
					+ (cur.dir == nd ? 0 : 1);

				if (dp[nr][nc][nd] > cost) {
					dp[nr][nc][nd] = cost;
					if (cur.dir == nd) {
						dq.addFirst(new Point(nr, nc, nd)); // 비용 0
					} else {
						dq.addLast(new Point(nr, nc, nd));  // 비용 1
					}
				}
			}
		}

		int answer = INF;
		for (int d = 0; d < 4; d++) {
			answer = Math.min(answer, dp[end.r][end.c][d]);
		}

		System.out.println(answer);
	}

	static class Point {
		int r, c, dir;

		public Point(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}
}
