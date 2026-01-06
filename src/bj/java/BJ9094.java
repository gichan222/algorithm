package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ9094 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		while(T-- > 0){
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int cnt = 0;
			for(int i = 1; i < n-1; i++){
				for(int j = i+1; j < n; j++){
					if((i*i + j*j + m) % (i*j) == 0){
						cnt++;
					}
				}
			}
			System.out.println(cnt);
		}
	}

}
