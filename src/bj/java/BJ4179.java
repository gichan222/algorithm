package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ4179 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken()); // 지도의 행
		int C = Integer.parseInt(st.nextToken()); // 지도의 열

		int[][] mapDP = new int[R][C]; // 불이 퍼지는 시간를 가진 지도
		char[][] map = new char[R][C]; // 지도
		Point start = null; // 시작점
		Queue<Point> q = new LinkedList<>(); // 불과 지훈이의 이동을 파악할 큐
		for(int i = 0; i < R; i++){
			String s = br.readLine();
			Arrays.fill(mapDP[i], Integer.MAX_VALUE); // 최초값 MAX
			for(int j = 0; j < C; j++){
				map[i][j] = s.charAt(j);
				if(map[i][j] == 'J'){ // 시작점이면
					start = new Point(i, j, 0); // 시작점 초기화
				} else if (map[i][j] == 'F') { // 불이난 지점이면
					q.add(new Point(i,j)); // 큐에 추가
					mapDP[i][j] = 0; // 0초에 발화
				}
			}
		}

		if(start.r == R-1 || start.r == 0 || start.c == C-1 || start.c == 0){ // 만약 이미 가장자리라면
			System.out.println(1); // 정답 출력
			return; // 종료
		}

		int[] dR = new int[]{0, 0, -1, 1}; // 4방향
		int[] dC = new int[]{-1, 1, 0, 0}; // 4방향

		while(!q.isEmpty()){
			Point cur = q.poll();
			for(int i = 0; i < 4; i++){ // 4방향 탐색
				int nR = cur.r + dR[i]; // 다음 행
				int nC = cur.c + dC[i]; // 다음 열
				if(nR < R && nR >= 0 && nC < C && nC >= 0){ // 지도 안에 있다면
					// 불이난 적이 없고, 벽이 아니라 불이 퍼질 수 있다면
					if(mapDP[nR][nC] == Integer.MAX_VALUE && map[nR][nC] != '#'){
						// 불 난 것으로 체크
						mapDP[nR][nC] = mapDP[cur.r][cur.c] + 1;
						// 다음 발화를 위해 큐에 추가
						q.add(new Point(nR, nC));
					}
				}
			}
		}

		q.add(start);
		while(!q.isEmpty()){
			Point cur = q.poll();
			for(int i = 0; i < 4; i++) { // 4방향 탐색
				int nR = cur.r + dR[i]; // 다음 행
				int nC = cur.c + dC[i]; // 다음 열
				if (nR < R && nR >= 0 && nC < C && nC >= 0) { // 지도 안에 있다면
					// 이동 시간 내 불이 나지 않았고, 벽이 아니라면
					if(mapDP[nR][nC] > cur.time + 1 && map[nR][nC] != '#'){
						// 가장자리이면 끝
						if(nR == R-1 || nR == 0 || nC == C-1 || nC ==0){
							System.out.println(cur.time + 2);
							return;
						}
						// 방문한 위치를 벽으로 바꿈으로써 방문 체크
						map[nR][nC] = '#';
						q.add(new Point(nR, nC, cur.time + 1));
					}
				}
			}
		}

		// 가장자리로 끝나지 못했다면 불가능한 상황임
		System.out.println("IMPOSSIBLE");
	}

	static class Point{
		int r, c, time;

		public Point(int r, int c){
			this.r = r;
			this.c = c;
		}

		public Point(int r, int c, int time){
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
}
