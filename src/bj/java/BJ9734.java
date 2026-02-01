package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ9734 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String num;
		while ((num = br.readLine()) != null) {

			// ( 기준 분리
			String[] split = num.split("\\(");
			String left = split[0];
			String repeat = split[1].replace(")", "");

			String integer;
			String nonRepeat;

			if (left.contains(".")) {
				String[] dot = left.split("\\.");
				integer = dot[0];
				nonRepeat = dot.length > 1 ? dot[1] : "";
			} else {
				integer = left;
				nonRepeat = "";
			}

			if (integer.equals("")) {
				integer = "0";
			}

			int n = nonRepeat.length();
			int m = repeat.length();

			long A = Long.parseLong(integer + nonRepeat);
			long B = Long.parseLong(integer + nonRepeat + repeat);

			long numerator = B - A;
			long denominator =
				(long) Math.pow(10, n + m) - (long) Math.pow(10, n);

			long g = gcd(numerator, denominator);
			numerator /= g;
			denominator /= g;

			System.out.println(num + " = " + numerator + " / " + denominator);
		}
	}

	static long gcd(long a, long b) {
		while (b != 0) {
			long t = a % b;
			a = b;
			b = t;
		}
		return a;
	}
}
