package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17392 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		for(int i = 0; i < N; i++){
			int next = Integer.parseInt(st.nextToken());
			M -= next + 1;
		}

		int[] arr = new int[N+1];
		int idx = 0;
		while(M > 0){
			arr[idx]++;
			M--;
			idx++;
			idx %= N+1;
		}

		int answer = 0;
		for(int i : arr){
			for(int k = 1; k <= i; k++){
				answer += k * k;
			}
		}

		System.out.println(answer);
	}
}
