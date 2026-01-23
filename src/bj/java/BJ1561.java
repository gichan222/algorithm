package bj.java;

import java.io.*;
import java.util.StringTokenizer;

public class BJ1561 {

	static long n;
	static int m;
	static int[] time;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Long.parseLong(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		time = new int[m];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<m; i++) {
			time[i] = Integer.parseInt(st.nextToken());
		}

		if(n <= m) {
			System.out.println(n);
			return;
		}

		long ans = binarySearch();
		long child = time(ans-1);

		for(int i=0; i<m; i++) {
			if(ans%time[i]==0) {
				child++;
			}
			if(child == n) {
				System.out.println(i+1);
				break;
			}
		}
	}

	private static long time(long t) {
		long childNum = m;
		for(int i=0; i<m; i++) {
			childNum += t/time[i];
		}
		return childNum;
	}

	private static long binarySearch() {
		long l = 0;
		long r = n*30;
		while(l <= r) {
			long mid = (l+r)/2;
			long childNum = time(mid);

			if(childNum < n) {
				l = mid+1;
			}else {
				r = mid-1;
			}
		}
		return l;
	}
}