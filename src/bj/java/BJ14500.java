package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class BJ14500 {

	static int N, M;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int max = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (String d : directions) {
					int val = calcSum(i, j, d);
					if (val != -1) max = Math.max(max, val);
				}
				for (String d : otherDirections) {
					int val = calcSum(i, j, d);
					if (val != -1) max = Math.max(max, val);
				}
			}
		}

		System.out.println(max);
	}

	static final List<String> directions = List.of(
		"RRR", "DDD", "RDL", "DDR", "URR",
		"URU", "RDR", "RUU", "DRR", "LDD",
		"ULL", "RDD", "DLL", "DRD", "RUR"
	);

	static final List<String> otherDirections = List.of(
		"RDUR", "DLRD", "RUDR", "DRLD"
	);

	// 방향
	static int[] dr = {0, 0, 1, -1}; // R L D U
	static int[] dc = {1, -1, 0, 0};

	static int dir(char c) {
		return switch (c) {
			case 'R' -> 0;
			case 'L' -> 1;
			case 'D' -> 2;
			case 'U' -> 3;
			default -> -1;
		};
	}

	static int calcSum(int r, int c, String path) {
		int sum = map[r][c];
		int cr = r, cc = c;

		for (char d : path.toCharArray()) {
			int idx = dir(d);
			cr += dr[idx];
			cc += dc[idx];

			if (cr < 0 || cr >= N || cc < 0 || cc >= M) {
				return -1; // 범위 벗어나면 무효
			}
			sum += map[cr][cc];
		}
		return sum;
	}
}
