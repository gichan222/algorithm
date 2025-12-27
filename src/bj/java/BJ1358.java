package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1358 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken()); // 가로
		int H = Integer.parseInt(st.nextToken()); // 세로
		int X = Integer.parseInt(st.nextToken()); // 왼쪽
		int Y = Integer.parseInt(st.nextToken()); // 오른쪽
		int P = Integer.parseInt(st.nextToken()); // 선수의 수

		int cnt = 0;
		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if (x >= X && x <= X + W) {
				if (y >= Y && y <= Y + H) {
					cnt++;
					continue;
				}
			}
			double length = Math.pow(X - x, 2) + Math.pow(Y + (H / 2) - y, 2);
			if (length <= Math.pow(H / 2, 2)) {
				cnt++;
				continue;
			}
			length = Math.pow(X + W - x, 2) + Math.pow(Y + (H / 2) - y, 2);
			if (length <= Math.pow(H / 2, 2)) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
