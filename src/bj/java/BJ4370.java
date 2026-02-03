package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ4370 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;

		while ((line = br.readLine()) != null) {
			long n = Long.parseLong(line.trim());

			long p = 1;
			boolean baekjoonTurn = true;

			while (p < n) {
				if (baekjoonTurn) {
					p *= 9;
					if (p >= n) {
						System.out.println("Baekjoon wins.");
						break;
					}
				} else {
					p *= 2;
					if (p >= n) {
						System.out.println("Donghyuk wins.");
						break;
					}
				}
				baekjoonTurn = !baekjoonTurn;
			}
		}
	}
}
