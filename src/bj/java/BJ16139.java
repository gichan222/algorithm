package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ16139 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		String S = br.readLine();
		int q = Integer.parseInt(br.readLine());
		int[][] sum = new int[26][S.length()+1];
		for(int i = 1 ; i < S.length()+1; i++){
			for(int j = 0; j < 26; j++){
				sum[j][i] += sum[j][i-1];
			}
			sum[S.charAt(i-1) - 'a'][i]++;
		}
		StringBuilder sb = new StringBuilder();
		while(q-- > 0){
			st = new StringTokenizer(br.readLine());
			char alphabet = st.nextToken().charAt(0);
			int l = Integer.parseInt(st.nextToken()); // 자기 자신 포함 위함
			int r = Integer.parseInt(st.nextToken()) + 1; // 자기 자신 포함 위함
			sb.append(sum[alphabet - 'a'][r] - sum[alphabet - 'a'][l]).append("\n");
		}
		System.out.println(sb);
	}
}
