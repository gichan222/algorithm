package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2090 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		long[] arr = new long[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}

		long a = 1L;
		long b = arr[0];

		for (int i = 1; i < N; i++) {
			long gcd = gcd(b, arr[i]);
			long lcm = b * arr[i] / gcd;

			a = a * (lcm / b) + (lcm / arr[i]);
			b = lcm;
		}

		long gcd = gcd(a, b);
		System.out.println(b / gcd + "/" + a / gcd);
	}

	static long gcd(long a, long b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}
}
