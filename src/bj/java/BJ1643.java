package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1643 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String line;
		while ((line = br.readLine()) != null) {
			line = line.trim();
			if (line.isEmpty()) continue;
			long num = Long.parseLong(line);

			long denominator = 1L;
			long numerator = 1L;

			for (int i = 2; i <= num; i++) {
				long curDenominator = i;
				long curNumerator = 1;

				long originDenominator = denominator;
				denominator = lcm(denominator, curDenominator);

				numerator *= denominator / originDenominator;
				numerator += (denominator / curDenominator) * curNumerator;
			}

			long tempNumerator = num * numerator;
			long gcd = gcd(tempNumerator, denominator);

			tempNumerator /= gcd;
			denominator /= gcd;

			long answerNum = tempNumerator / denominator;
			long answerNumerator = tempNumerator % denominator;

			StringBuilder sb = new StringBuilder();

			int space = digitCount(answerNum);
			int hyphen = digitCount(denominator);

			if (answerNumerator != 0 && answerNum != 0) {
				sb.append(" ".repeat(space+1));
			}

			if (answerNumerator != 0) {
				sb.append(answerNumerator).append("\n");
			}

			if (answerNum != 0) {
				sb.append(answerNum);
			}

			if (answerNumerator != 0) {
				sb.append(" ");
				sb.append("-".repeat(hyphen)).append("\n");
				if (answerNum != 0) {
					sb.append(" ".repeat(space+1));
				}
				sb.append(denominator);
			}

			System.out.println(sb);
		}
	}

	static long gcd(long a, long b) {
		if (b == 0) return a;
		return gcd(b, a % b);
	}

	static long lcm(long x, long y) {
		return (x * y) / gcd(x, y);
	}

	static int digitCount(long n) {
		if (n == 0) return 1;
		int cnt = 0;
		while (n != 0) {
			cnt++;
			n /= 10;
		}
		return cnt;
	}
}
