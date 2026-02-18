package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11048 {

	static int N, M;
	static int[][] map;
	static int[][] direction = new int[][]{{-1, 0}, {0, -1}};
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		dp = new int[N][M];

		for(int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++){
				int cnt = Integer.parseInt(st.nextToken());
				map[i][j] = cnt;
			}
		}

		for(int i = 0; i < N; i++){
			for(int j = 0; j < M; j++){
				int max = 0;
				for(int k = 0; k < 2; k++){
					int x = i + direction[k][0];
					int y = j + direction[k][1];
					if(x >= 0 && x < N && y >= 0 && y < M){
						max = Math.max(max, map[x][y]);
					}
				}
				map[i][j] += max;
			}
		}

		System.out.println(map[N-1][M-1]);
	}
}
