package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class BJ8972 {

	// 9개 방향
	static int[] dx = new int[]{1, 1, 1, 0, 0, 0, -1, -1, -1};
	static int[] dy = new int[]{-1, 0, 1, -1, 0, 1, -1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		char[][] map = new char[R][C];
		// 종수의 위치
		Point jongsu = null;
		// 아두이노 위치
		List<Point> arduinos = new ArrayList<>();
		for(int i = 0 ; i < R; i++){
			String line = br.readLine();
			for(int j = 0; j < C; j++){
				map[i][j] = line.charAt(j);
				if(map[i][j] == 'I'){
					jongsu = new Point(i, j);
				}else if(map[i][j] == 'R'){
					arduinos.add(new Point(i, j));
				}
			}
		}

		String command = br.readLine();
		for(int i = 0; i < command.length(); i++){
			// 종수의 다음 위치
			int nextCommand = command.charAt(i) - '0' - 1;
			int nX = jongsu.R + dx[nextCommand];
			int nY = jongsu.C + dy[nextCommand];
			map[jongsu.R][jongsu.C] = '.';
			// 아두이노를 밟는다면 게임 끝
			if(map[nX][nY] == 'R'){
				System.out.println("kraj " + (i+1));
				return;
			}
			jongsu.R = nX;
			jongsu.C = nY;
			map[jongsu.R][jongsu.C] = 'I';

			// 다음 아두이노의 위치
			List<Point> nextArduinos = new ArrayList<>();
			List<Point> boom = new ArrayList<>();
			for(Point p : arduinos){
				nX = p.R;
				nY = p.C;
				map[nX][nY] = '.';

				if(nX < jongsu.R){
					nX++;
				} else if (nX > jongsu.R) {
					nX--;
				}

				if(nY < jongsu.C){
					nY++;
				}else if(nY > jongsu.C){
					nY--;
				}

				Point next = new Point(nX, nY);
				if(map[nX][nY] == 'I'){
					System.out.println("kraj " + (i+1));
					return;
				}
				if(nextArduinos.contains(next)){
					boom.add(next);
					continue;
				}
				nextArduinos.add(next);
			}

			// 다음 아두이노 위치 설정하기
			arduinos.clear();
			for(Point p : nextArduinos){
				// 터지기
				if(boom.contains(p)){
					map[p.R][p.C] = '.';
					continue;
				}
				map[p.R][p.C] = 'R';
				arduinos.add(p);
			}

		}

		for(int i = 0; i < R; i++){
			for(int j = 0; j < C; j++){
				System.out.print(map[i][j]);
			}
			System.out.println();
		}

	}


	static class Point{
		int R, C;

		public Point(int R, int C){
			this.R = R;
			this.C = C;
		}

		@Override
		public boolean equals(Object o) {
			if (o == null || getClass() != o.getClass())
				return false;
			Point point = (Point)o;
			return R == point.R && C == point.C;
		}

		@Override
		public int hashCode() {
			return Objects.hash(R, C);
		}
	}
}
