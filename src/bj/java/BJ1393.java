package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1393 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int sX = Integer.parseInt(st.nextToken());
		int sY = Integer.parseInt(st.nextToken());
		int dX = Integer.parseInt(st.nextToken());
		int dY = Integer.parseInt(st.nextToken());

		int gcd = gcd(Math.abs(dX), Math.abs(dY)); // 기울기를 정수화 하기 위함

		dX /= gcd;
		dY /= gcd;

		int startLength = (x - sX) * (x - sX) + (y-sY) * (y-sY);
		int minLength = startLength;
		int minX = sX;
		int minY = sY;
		int nX = sX + dX;
		int nY = sY + dY;
		while(true){
			int tempLength = (x - nX) * (x - nX) + (y-nY) * (y-nY);
			if(tempLength > startLength){
				System.out.println(minX + " " + minY);
				return;
			}
			if(minLength > tempLength){
				minX = nX;
				minY = nY;
				minLength = tempLength;
			}
			nX += dX;
			nY += dY;
		}
	}

	static int gcd(int a, int b){
		if(b == 0){
			return a;
		}
		return gcd(b, a % b);
	}
}
