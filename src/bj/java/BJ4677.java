package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ4677 {
	static int N, M;
	static boolean[][] map;
	static int[] dx = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dy = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		while(M != 0){

			map = new boolean[N][M];
			for(int i = 0; i < N; i++){
				String line = br.readLine();
				for(int j = 0; j < M; j++){
					if(line.charAt(j) == '@'){
						map[i][j] = true;
					}
				}
			}

			int cnt = 0;
			for(int i = 0; i < N; i++){
				for(int j = 0; j < M; j++){
					if(map[i][j]){
						cnt++;
						dfs(i, j);
					}
				}
			}

			System.out.println(cnt);

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
		}
	}

	// dfs를 통해 탐색
	static void dfs(int x, int y){
		map[x][y] = false;
		for(int i = 0; i < 8; i++){
			int nX = x + dx[i];
			int nY = y + dy[i];
			if(nX >= 0 && nX < N && nY >= 0 && nY < M){
				if(map[nX][nY]){
					dfs(nX, nY);
				}
			}
		}
	}
}
