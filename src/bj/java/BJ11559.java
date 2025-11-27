package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BJ11559 {
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static char[][] map = new char[12][6];
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 12; i++) {
			String s = br.readLine();
			for (int j = 0; j < 6; j++) {
				map[i][j] = s.charAt(j);
			}
		}

		int totalCount = 0;
		boolean changed = true;

		while (changed) {
			visited = new boolean[12][6];
			changed = false;

			// 삭제할 블록 저장
			List<int[]> toDelete = new ArrayList<>();

			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (!visited[i][j] && map[i][j] != '.' && map[i][j] != '#') {
						List<int[]> connected = new ArrayList<>();
						dfs(i, j, map[i][j], connected);

						if (connected.size() >= 4) {
							toDelete.addAll(connected);
							changed = true;
						}
					}
				}
			}

			// 블록 삭제
			for (int[] pos : toDelete) {
				map[pos[0]][pos[1]] = '.';
			}

			// 중력 적용
			for (int col = 0; col < 6; col++) {
				for (int row = 11; row >= 0; row--) {
					if (map[row][col] == '.') {
						int temp = row - 1;
						while (temp >= 0 && map[temp][col] == '.') {
							temp--;
						}
						if (temp >= 0) {
							map[row][col] = map[temp][col];
							map[temp][col] = '.';
						}
					}
				}
			}

			if (changed) {
				totalCount++;
			}
		}

		System.out.println(totalCount);
	}

	static void dfs(int x, int y, char color, List<int[]> connected) {
		visited[x][y] = true;
		connected.add(new int[]{x, y});

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 0 && nx < 12 && ny >= 0 && ny < 6) {
				if (!visited[nx][ny] && map[nx][ny] == color) {
					dfs(nx, ny, color, connected);
				}
			}
		}
	}
}
