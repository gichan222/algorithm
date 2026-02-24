package bj.java;

import java.io.*;
import java.util.*;

public class BJ10972 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

		int startIdx = 0;
		for (int i = 1; i < N; i++) {
			if (arr[i] > arr[i - 1]) startIdx = i; // pivot+1
		}

		if (startIdx == 0) {
			System.out.println(-1);
			return;
		}

		// swap 대상 찾기
		for (int i = N - 1; i >= startIdx; i--) {
			if (arr[i] > arr[startIdx - 1]) {
				int temp = arr[i];
				arr[i] = arr[startIdx - 1];
				arr[startIdx - 1] = temp;
				break;
			}
		}

		// 뒤쪽 구간 reverse
		int l = startIdx, r = N - 1;
		while (l < r) {
			int temp = arr[l];
			arr[l] = arr[r];
			arr[r] = temp;
			l++;
			r--;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(arr[i]).append(' ');
		}
		System.out.println(sb);
	}
}