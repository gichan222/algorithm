package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1600 {

	static int K, W, H;
	static int[][] map;

	// 원숭이 이동
	static final int[][] DIRECTIONS = {
		{1, 0}, {-1, 0}, {0, 1}, {0, -1}
	};

	// 말 이동
	static final int[][] HORSE_DIRECTIONS = {
		{1, 2}, {2, 1}, {2, -1}, {1, -2},
		{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}
	};

	static class Node {
		int x, y, horseUsed, dist;

		Node(int x, int y, int horseUsed, int dist) {
			this.x = x;
			this.y = y;
			this.horseUsed = horseUsed;
			this.dist = dist;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		K = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][W];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(bfs());
	}

	static int bfs() {
		Queue<Node> queue = new LinkedList<>();
		boolean[][][] visited = new boolean[H][W][K + 1];

		queue.add(new Node(0, 0, 0, 0));
		visited[0][0][0] = true;

		while (!queue.isEmpty()) {
			Node cur = queue.poll();

			// 도착
			if (cur.x == W - 1 && cur.y == H - 1) {
				return cur.dist;
			}

			// 원숭이 이동
			for (int[] d : DIRECTIONS) {
				int nx = cur.x + d[0];
				int ny = cur.y + d[1];

				if (isValid(nx, ny, cur.horseUsed, visited)) {
					visited[ny][nx][cur.horseUsed] = true;
					queue.add(new Node(nx, ny, cur.horseUsed, cur.dist + 1));
				}
			}

			// 말 이동
			if (cur.horseUsed < K) {
				for (int[] d : HORSE_DIRECTIONS) {
					int nx = cur.x + d[0];
					int ny = cur.y + d[1];

					if (isValid(nx, ny, cur.horseUsed + 1, visited)) {
						visited[ny][nx][cur.horseUsed + 1] = true;
						queue.add(new Node(nx, ny, cur.horseUsed + 1, cur.dist + 1));
					}
				}
			}
		}

		return -1;
	}

	static boolean isValid(int x, int y, int horseUsed, boolean[][][] visited) {
		return x >= 0 && x < W &&
			y >= 0 && y < H &&
			map[y][x] == 0 &&
			!visited[y][x][horseUsed];
	}
}
