package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

public class BJ1337 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		Set<Integer> nums = new TreeSet<>();

		for (int i = 0; i < N; i++) {
			nums.add(Integer.parseInt(br.readLine()));
		}

		int min = 4;
		for (int temp : nums) {
			int tempMin = 0;
			for (int i = 1; i <= 4; i++) {
				if (!nums.contains(temp + i)) {
					tempMin++;
				}
			}
			min = Math.min(tempMin, min);
		}

		System.out.println(min);
	}
}
