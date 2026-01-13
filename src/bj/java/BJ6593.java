package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ6593 {

	static int L, R, C;
	static boolean[][][] map;
	static int[] dx = new int[]{-1, 1, 0, 0, 0, 0};
	static int[] dy = new int[]{0, 0, -1, 1, 0, 0};
	static int[] dz = new int[]{0, 0, 0, 0, -1, 1};
	static Queue<Point> q = new ArrayDeque<>();
	static Point start;
	static Point end;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		while(L != 0 || R != 0 || C != 0){
			map = new boolean[L][R][C]; // L층 R행 C열
			for(int i = 0; i < L; i++){
				for(int j = 0; j < R; j++){
					String line = br.readLine();
					for (int k = 0; k < C; k++){
						if(line.charAt(k) == '.'){
							map[i][j][k] = true;
						} else if (line.charAt(k) == 'S') {
							start = new Point(i, j, k, 0);
							map[i][j][k] = false;
						} else if (line.charAt(k) == 'E') {
							map[i][j][k] = true;
							end = new Point(i, j, k, 0);
						}
					}
				}
				br.readLine();
			}

			q.clear();
			q.add(start);
			int num = bfs();
			if(num == 0){
				sb.append("Trapped!\n");
			}else{
				sb.append("Escaped in ").append(num).append(" minute(s).\n");
			}
			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
		}

		System.out.println(sb);

	}

	static int bfs(){
		while(!q.isEmpty()){
			Point cur = q.poll();
			if(cur.z == end.z && cur.x == end.x && cur.y == end.y){
				return cur.cnt;
			}
			for(int i = 0; i < 6; i++){
				int nz = cur.z + dz[i];
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if(nz >= 0 && nx >= 0 && ny >= 0 && nz < L && nx < R && ny < C){
					if(map[nz][nx][ny]){
						q.add(new Point(nz, nx, ny, cur.cnt + 1));
						map[nz][nx][ny] = false;
					}
				}
			}
		}
		return 0;
	}

	static class Point {
		int z, x, y, cnt;

		public Point(int z, int x, int y, int cnt) {
			this.z = z;
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
}
