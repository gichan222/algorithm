package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2823 {

	static int R, C;
	static boolean[][] map;
	static int[] dx = new int[]{-1, 1, 0, 0};
	static int[] dy = new int[]{0, 0, -1, 1};
	static boolean flag = true;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new boolean[R][C];

		for(int i = 0; i < R; i++){
			String line = br.readLine();
			for(int j = 0; j < C; j++) {
				if (line.charAt(j) == '.') {
					map[i][j] = true;
				}
			}
		}
		// 단순하게 2 방향과 이어지면 문제 없다.
		for(int i = 0; i < R; i++){
			for(int j = 0; j < C; j++){
				if(map[i][j]){
					int cnt = 0;
					for(int k = 0; k < 4; k++){
						int nR = i + dx[k];
						int nC = j + dy[k];
						if(nR >= 0 && nR < R && nC >= 0 && nC < C){
							if(map[nR][nC]){
								cnt++;
							}
						}
					}
					if(cnt <= 1){
						flag = false;
					}

				}
			}
		}

		if(flag){
			System.out.println(0);
			return;
		}

		System.out.println(1);
	}

}
