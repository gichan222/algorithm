package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ7347 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			int even = 0;
			int odd = 0;

			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());

			int idx = 0;
			while (idx < size) {
				if (!st.hasMoreTokens()) {
					st = new StringTokenizer(br.readLine());
				}
				int val = Integer.parseInt(st.nextToken());

				if (val == 1) {
					if (idx % 2 == 0) even++;
					else odd++;
				}
				idx++;
			}

			if (size % 2 == 1 || Math.abs(even - odd) <= 1) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
	}
}
