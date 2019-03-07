import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1494 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static Point[] arr;
	static int sumX, sumY;
	static long min;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine().trim());
			arr = new Point[N];

			sumX = 0;
			sumY = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				sumX += x;
				sumY += y;
				arr[i] = new Point(x, y);
			}
			min = Long.MAX_VALUE;
			com(new Point[N / 2], 0, 0);
			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}
		System.out.println(sb);
	}

	static void com(Point[] select, int depth, int cnt) {
		if (cnt == select.length) {
			int selectX = 0;
			int selectY = 0;
			for (int i = 0; i < select.length; i++) {
				selectX += select[i].x;
				selectY += select[i].y;
			}
			int otherX = sumX - selectX;
			int otherY = sumY - selectY;
			long sum = (long) (Math.pow((long) otherX - selectX, 2) + Math.pow((long) otherY - selectY, 2));
			if (min > sum)
				min = sum;
			return;
		}
		if (depth == arr.length) {
			return;
		}
		select[cnt] = arr[depth];
		com(select, depth + 1, cnt + 1);
		select[cnt] = null;
		com(select, depth + 1, cnt);
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}
}
