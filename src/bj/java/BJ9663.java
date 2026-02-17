package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ9663 {

	static int N;
	static boolean[][] isImpossible;
	static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
	static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
	static int cnt = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		isImpossible = new boolean[N][N];

		for(int i = 0; i < N; i++){
			dfs(0, i, N - 1);
		}

		System.out.println(cnt);
	}

	static void dfs(int x, int y, int leftCnt){
		System.out.println("x : " + x + " y : " + y + " cnt : " + cnt);
		if(leftCnt == 0){
			cnt++;
			return;
		}

		isImpossible[x][y] = true;

		for(int i = 0 ; i < 8; i++){
			int k = 1;
			while(true){
				int nX = x + dx[i] * k;
				int nY = y + dy[i] * k;
				k++;
				if(!(nX >= 0 && nX < N && nY >= 0 && nY < N)){
					break;
				}
				if(isImpossible[nX][nY]){
					break;
				}
				isImpossible[nX][nY] = true;
			}
		}

		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				if(!isImpossible[i][j]){
					dfs(i, j, leftCnt - 1);
				}
			}
		}

		isImpossible[x][y] = false;

		for(int i = 0 ; i < 8; i++){
			int k = 1;
			while(true){
				int nX = x + dx[i] * k;
				int nY = y + dy[i] * k;
				k++;
				if(!(nX >= 0 && nX < N && nY >= 0 && nY < N)){
					break;
				}
				if(!isImpossible[nX][nY]){
					break;
				}
				isImpossible[nX][nY] = false;
			}
		}
	}

}
