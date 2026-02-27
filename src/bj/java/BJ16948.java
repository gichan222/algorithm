package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ16948 {

	static int N;
	static boolean[][] visited;
	static int r2, c2;
	// (r-2, c-1), (r-2, c+1), (r, c-2), (r, c+2), (r+2, c-1), (r+2, c+1)
	static int[] dx = new int[]{-2, -2, 0, 0, 2, 2};
	static int[] dy = new int[]{-1, 1, -2, 2, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		visited = new boolean[N][N];

		st = new StringTokenizer(br.readLine());
		int r1 = Integer.parseInt(st.nextToken());
		int c1 = Integer.parseInt(st.nextToken());
		r2 = Integer.parseInt(st.nextToken());
		c2 = Integer.parseInt(st.nextToken());

		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[]{r1, c1, 0});
		visited[r1][c1] = true;
		while(!queue.isEmpty()){
			int[] cur = queue.poll();
			if(cur[0] == r2 && cur[1] == c2){
				System.out.println(cur[2]);
				return;
			}
			for(int i = 0; i < 6; i++){
				int nX = cur[0] + dx[i];
				int nY = cur[1] + dy[i];
				if(nX >= 0 && nX < N && nY >= 0 && nY < N){
					if(!visited[nX][nY]){
						visited[nX][nY] = true;
						queue.add(new int[]{nX, nY, cur[2] + 1});
					}
				}
			}
		}
		System.out.println(-1);
	}
}
