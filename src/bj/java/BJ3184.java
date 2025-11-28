package bj.java;

import java.util.*;
import java.io.*;

public class BJ3184 {

	static int R, C;
	static char[][] yard;
	static boolean[][] visited;
	static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};
	static int sheepTotal, wolfTotal;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		yard = new char[R][C];
		visited = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			yard[i] = br.readLine().toCharArray();
		}

		// 가장자리 중 '울타리(#)'가 아닌 공간을 BFS로 먼저 전부 방문 처리
		for (int i = 0; i < R; i++) {
			// 왼쪽 가장자리
			if (!visited[i][0] && yard[i][0] != '#') {
				markOutside(i, 0);
			}
			// 오른쪽 가장자리
			if (!visited[i][C - 1] && yard[i][C - 1] != '#') {
				markOutside(i, C - 1);
			}
		}

		for (int j = 0; j < C; j++) {
			// 위
			if (!visited[0][j] && yard[0][j] != '#') {
				markOutside(0, j);
			}
			// 아래
			if (!visited[R - 1][j] && yard[R - 1][j] != '#') {
				markOutside(R - 1, j);
			}
		}

		// 이제 내부 닫힌 구역만 BFS 탐색 수행
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (!visited[i][j] && yard[i][j] != '#') {
					bfs(i, j);
				}
			}
		}

		System.out.println(sheepTotal + " " + wolfTotal);
	}

	// 바깥과 연결된 공간을 표시하는 BFS
	static void markOutside(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		visited[x][y] = true;
		q.add(new int[]{x, y});

		while (!q.isEmpty()) {
			int[] now = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];

				if (!isRange(nx, ny) || visited[nx][ny] || yard[nx][ny] == '#')
					continue;

				visited[nx][ny] = true;
				q.add(new int[]{nx, ny});
			}
		}
	}

	// 내부 영역 BFS (양/늑대 비교)
	static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();

		visited[x][y] = true;
		q.add(new int[]{x, y});

		int sheepCnt = 0, wolfCnt = 0;

		while (!q.isEmpty()) {
			int[] now = q.poll();

			if (yard[now[0]][now[1]] == 'o') sheepCnt++;
			else if (yard[now[0]][now[1]] == 'v') wolfCnt++;

			for (int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];

				if (!isRange(nx, ny) || visited[nx][ny] || yard[nx][ny] == '#')
					continue;

				visited[nx][ny] = true;
				q.add(new int[]{nx, ny});
			}
		}

		if (sheepCnt > wolfCnt) sheepTotal += sheepCnt;
		else wolfTotal += wolfCnt;
	}

	static boolean isRange(int x, int y) {
		return x >= 0 && x < R && y >= 0 && y < C;
	}
}
