package bj.java;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2194 {
	static int N, M, A, B, K;
	static int arr[][];
	static boolean visit[][];
	static int moveX[] = {0, 1, 0, -1};
	static int moveY[] = {-1, 0, 1, 0};
	static Point start, end;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new int[N + 1][M + 1];
		visit = new boolean[N + 1][M + 1];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			arr[y][x] = -1;
		}

		st = new StringTokenizer(br.readLine());
		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		start = new Point(x, y);

		st = new StringTokenizer(br.readLine());
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		end = new Point(x, y);

		// 시작 위치 유효성 검사
		if (!isValid(start.y, start.x)) {
			System.out.println(-1);
			return;
		}

		System.out.println(bfs());
	}

	static boolean Range(int i, int j) {
		return 1 <= i && i <= N && 1 <= j && j <= M;
	}

	// 시작 위치 & 이동 가능 여부 검사
	static boolean isValid(int i, int j) {
		for (int x = i; x < i + A; x++) {
			for (int y = j; y < j + B; y++) {
				if (!Range(x, y)) return false;
				if (arr[x][y] == -1) return false;
			}
		}
		return true;
	}

	private static int bfs() {
		Queue<Point> queue = new LinkedList<>();
		queue.add(start);
		visit[start.y][start.x] = true;

		int result = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Point po = queue.poll();

				if (po.x == end.x && po.y == end.y) {
					return result;
				}

				for (int d = 0; d < 4; d++) {
					int newX = po.x + moveX[d];
					int newY = po.y + moveY[d];

					if (!Range(newY, newX)) continue;
					if (visit[newY][newX]) continue;
					if (!isValid(newY, newX)) continue;

					visit[newY][newX] = true;
					queue.add(new Point(newX, newY));
				}
			}
			result++;
		}
		return -1;
	}
}
