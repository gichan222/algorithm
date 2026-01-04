package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ4577 {
	static int R, C;
	static char[][] map;
	static Point worker;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int gameCnt = 1;

		while (true) {
			String line = br.readLine();
			if (line == null) break;
			st = new StringTokenizer(line);

			// 입력이 빈 줄일 경우 방어 코드 (백준 데이터 특성상 필요할 수 있음)
			if(!st.hasMoreTokens()) continue;

			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			if (R == 0 && C == 0) break;

			map = new char[R][C];
			worker = null;

			for (int i = 0; i < R; i++) {
				String row = br.readLine();
				for (int j = 0; j < C; j++) {
					char c = row.charAt(j);
					map[i][j] = c;
					// 작업자 위치 찾기 (w: 맨땅 위 작업자, W: 골대 위 작업자)
					if (c == 'w' || c == 'W') {
						worker = new Point(i, j);
					}
				}
			}

			String commands = br.readLine();
			boolean finished = false;

			for (int k = 0; k < commands.length(); k++) {
				char cmd = commands.charAt(k);
				int dr = 0, dc = 0;

				if (cmd == 'U') dr = -1;
				else if (cmd == 'D') dr = 1;
				else if (cmd == 'L') dc = -1;
				else if (cmd == 'R') dc = 1;

				move(dr, dc);

				// 이동 후 게임 클리어 체크
				if (checkClear()) {
					finished = true;
					break;
				}
			}

			sb.append("Game ").append(gameCnt).append(": ");
			if (finished) {
				sb.append("complete\n");
			} else {
				sb.append("incomplete\n");
			}

			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			gameCnt++;
		}
		System.out.print(sb);
	}

	// 이동 로직
	static void move(int dr, int dc) {
		int nr = worker.r + dr;
		int nc = worker.c + dc;

		// 1. 벽이면 이동 불가
		if (map[nr][nc] == '#') return;

		// 2. 상자(b, B)를 만났을 때
		if (map[nr][nc] == 'b' || map[nr][nc] == 'B') {
			int nnr = nr + dr;
			int nnc = nc + dc;

			// 상자 뒤가 벽이거나 또 다른 상자면 이동 불가
			if (map[nnr][nnc] == '#' || map[nnr][nnc] == 'b' || map[nnr][nnc] == 'B') {
				return;
			}

			// 상자 밀기
			// 상자가 이동할 위치(nnr, nnc) 처리
			if (map[nnr][nnc] == '+') {
				map[nnr][nnc] = 'B'; // 골대 위로 이동했으므로 B
			} else {
				map[nnr][nnc] = 'b'; // 맨땅 위로 이동했으므로 b
			}

			// 상자가 있던 자리(nr, nc)는 이제 작업자가 들어올 것임
			// 상자가 떠난 자리가 원래 'B'였다면 '+'가 남고, 'b'였다면 '.'이 남음.
			// 하지만 바로 아래에서 작업자가 덮어쓸 것이므로 여기선 처리 안 해도 됨.
			// 다만, 논리적으로 상자가 있던 자리를 '비워주는' 처리는 아래 worker 이동 로직과 합쳐짐.
			if (map[nr][nc] == 'B') {
				map[nr][nc] = '+'; // 잠시 +로 복구 (작업자 이동 로직에서 W로 바뀔 것임)
			} else {
				map[nr][nc] = '.'; // 잠시 .로 복구
			}
		}

		// 3. 작업자 이동 (빈 공간이거나, 상자를 성공적으로 밀었을 때)

		// 작업자가 떠나는 자리(worker.r, worker.c) 처리
		if (map[worker.r][worker.c] == 'W') {
			map[worker.r][worker.c] = '+'; // 골대에서 나왔으니 골대 남김
		} else {
			map[worker.r][worker.c] = '.'; // 맨땅에서 나왔으니 빈땅 남김
		}

		// 작업자가 들어가는 자리(nr, nc) 처리
		if (map[nr][nc] == '+') { // 혹은 위에서 상자 B를 밀고 난 뒤 +가 된 상태
			map[nr][nc] = 'W';
		} else {
			map[nr][nc] = 'w';
		}

		// 좌표 갱신
		worker.r = nr;
		worker.c = nc;
	}

	// 모든 상자(b)가 없고 모두 골대 위 상자(B)인지 확인
	static boolean checkClear() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'b') { // 골대에 들어가지 않은 상자가 하나라도 있으면
					return false;
				}
			}
		}
		return true;
	}

	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}