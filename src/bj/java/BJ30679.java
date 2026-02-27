package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ30679 {

	static int N, M;
	static int[][] map;
	static boolean[][][] visited;
	static int[] dx = new int[]{0, 1, 0, -1};
	static int[] dy = new int[]{1, 0, -1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M][4];
		for(int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int cnt = 0;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++){
			int direction = 0;
			visited = new boolean[N][M][4];
			visited[i][0][0] = true;
			int curX = i;
			int curY = 0;
			while(true){
				// System.out.println("i : " + i + " curX : " + curX + " curY : " + curY + " direction : " + direction);
				visited[curX][curY][direction] = true;
				int nX = curX + map[curX][curY] * dx[direction];
				int nY = curY + map[curX][curY] * dy[direction];
				direction += 1;
				direction %= 4;
				if(nX < 0 || nX >= N || nY < 0 || nY >= M){
					break;
				}
				if(visited[nX][nY][direction]){
					// System.out.println(i);
					cnt++;
					sb.append(i+1).append(" ");
					break;
				}
				curX = nX;
				curY = nY;
			}
		}

		if(cnt == 0){
			System.out.println(0);
			return;
		}
		System.out.println(cnt);
		System.out.println(sb);
	}
}
