package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ3273 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);
		int target = Integer.parseInt(br.readLine());
		int s = 0;
		int e = n-1;
		int cnt = 0;
		while(s < e){
			int num = arr[s] + arr[e];
			if(num == target){
				cnt++;
				s++;
				e--;
				continue;
			}
			if(num < target){
				s++;
				continue;
			}
			e--;
		}
		System.out.println(cnt);
	}
}
