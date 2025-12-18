package bj.java;

import java.io.*;
import java.util.*;

public class BJ1450 {

	static int limit;
	static int[] nums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		limit = Integer.parseInt(st.nextToken());

		nums = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		List<Integer> leftSums = new ArrayList<>();
		List<Integer> rightSums = new ArrayList<>();

		generateSums(leftSums, 0, n / 2, 0);
		generateSums(rightSums, n / 2, n, 0);

		Collections.sort(rightSums);

		long count = 0;
		for (int left : leftSums) {
			count += countValidRight(rightSums, left);
		}

		System.out.println(count);
	}

	// 오른쪽 부분에서 (left + right <= limit) 를 만족하는 개수
	static int countValidRight(List<Integer> rightSums, int leftSum) {
		int low = 0;
		int high = rightSums.size() - 1;

		while (low <= high) {
			int mid = (low + high) >>> 1;
			if (rightSums.get(mid) <= limit - leftSum) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return high + 1;
	}

	// 부분 수열의 합 생성
	static void generateSums(List<Integer> result, int start, int end, int sum) {
		if (sum > limit) return;

		if (start == end) {
			result.add(sum);
			return;
		}

		generateSums(result, start + 1, end, sum);
		generateSums(result, start + 1, end, sum + nums[start]);
	}
}
