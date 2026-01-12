package bj.java;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ4454 {

	static double a, b, c, d, m, t;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;

		while ((line = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(line);
			a = Double.parseDouble(st.nextToken());
			b = Double.parseDouble(st.nextToken());
			c = Double.parseDouble(st.nextToken());
			d = Double.parseDouble(st.nextToken());
			m = Double.parseDouble(st.nextToken());
			t = Double.parseDouble(st.nextToken());

			double left = 0.0;
			double right = 1_000_000.0;

			for (int i = 0; i < 200; i++) {
				double mid = (left + right) / 2;
				if (canGo(mid)) {
					left = mid;
				} else {
					right = mid;
				}
			}

			// 소수점 둘째 자리 버림
			double answer = Math.floor(left * 100) / 100.0;
			System.out.printf("%.2f%n", answer);
		}
	}

	static boolean canGo(double v) {
		double fuel = m * (a * v * v * v + b * v * v + c * v + d);
		return fuel <= t;
	}
}
