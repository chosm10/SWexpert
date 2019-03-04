import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution1247 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static Point[] info;
	static Point company;
	static Point home;

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		int N = 0;
		StringBuilder result = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			info = new Point[N];
			company = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			home = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			for (int i = 0; i < N; i++) {
				info[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			min = Integer.MAX_VALUE;
			next_permutation(info, 0);
			result.append("#").append(tc).append(" ").append(min).append("\n");
		}
		bw.write(result.toString());
		bw.flush();
		bw.close();
	}

	static int dist(Point a, Point b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}

	static int sum = 0;
	static int min = Integer.MAX_VALUE;

	static void next_permutation(Point[] arr, int step) {
		if (step == arr.length) {
			sum = 0;
			sum += dist(company, arr[0]);
			for (int i = 0; i < arr.length - 1; i++) {
				sum += dist(arr[i], arr[i + 1]);
			}
			sum += dist(home, arr[arr.length - 1]);
			if (sum < min)
				min = sum;
			return;
		}

		for (int i = step; i < arr.length; i++) {
			swap(arr, step, i);
			next_permutation(arr, step + 1);
			swap(arr, step, i);
		}
	}

	static void swap(Point[] arr, int i, int j) {
		Point tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}
}
