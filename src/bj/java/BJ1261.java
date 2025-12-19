package bj.java;

import java.io.*;
import java.util.*;

public class BJ1261 {

	/*
	벽이 방향을 갖는 것이 아닌 하나의 방 전체에 대해서기 때문에 방향성을 크게 따지지 않아도 된다.
	가중치가 1이기 때문에 PriorityQueue보다는 Deque를 사용했다.
	 */
	static int n, m;
	static int[][] map;
	static int[][] dist;

	static final int INF = Integer.MAX_VALUE;
	static final int[] dx = {-1, 1, 0, 0};
	static final int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // 가로
		m = Integer.parseInt(st.nextToken()); // 세로

		map = new int[m][n];
		dist = new int[m][n];

		for (int i = 0; i < m; i++) {
			String line = br.readLine();
			for (int j = 0; j < n; j++) {
				map[i][j] = line.charAt(j) - '0';
				dist[i][j] = INF;
			}
		}

		System.out.println(bfs());
	}

	static int bfs() {
		Deque<Node> deque = new ArrayDeque<>();
		deque.add(new Node(0, 0));
		dist[0][0] = 0;

		while (!deque.isEmpty()) {
			Node cur = deque.pollFirst();

			int x = cur.x;
			int y = cur.y;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

				int cost = dist[y][x] + map[ny][nx];

				if (cost < dist[ny][nx]) {
					dist[ny][nx] = cost;

					// 벽이 없으면 앞에, 벽이면 뒤에
					if (map[ny][nx] == 0) {
						deque.addFirst(new Node(nx, ny));
					} else {
						deque.addLast(new Node(nx, ny));
					}
				}
			}
		}

		return dist[m - 1][n - 1];
	}

	static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
