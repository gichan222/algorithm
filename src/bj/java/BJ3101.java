package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ3101 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		long sum = 1; // 시작점 (1,1)
		String line = br.readLine();

		long x = 1;
		long y = 1;

		for (int i = 0; i < line.length(); i++) {
			char command = line.charAt(i);

			if (command == 'D') x++;
			else if (command == 'U') x--;
			else if (command == 'L') y--;
			else if (command == 'R') y++;

			long d = x + y - 1; // 대각선 번호

			long num = 0;
			if (d > N) {
				num = N * N + 1 - num(N+1-x, N+1-y);
			}else{
				num = num(x, y);
			}

			sum += num;
		}

		System.out.println(sum);
	}

	static long num(long x, long y){
		long d = x + y - 1; // 대각선 번호
		long start = (long) d * (d - 1) / 2 + 1;
		long num;

		if (d % 2 == 1) { // 홀수 대각선
			num = start + (y - 1);
		} else {          // 짝수 대각선
			num = start + (x - 1);
		}
		return num;
	}
}
