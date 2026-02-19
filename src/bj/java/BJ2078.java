package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2078 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		int lCnt = 0;
		int rCnt = 0;
		while(!(A == 1 && B == 1)){
			if(A > B){
				int nextA = A - B;
				int nextB = B;
				A = nextA;
				B = nextB;
				lCnt++;
			}else{
				int nextA = A;
				int nextB = B - A;
				A = nextA;
				B = nextB;
				rCnt++;
			}
		}

		System.out.println(lCnt + " " + rCnt);
	}
}
