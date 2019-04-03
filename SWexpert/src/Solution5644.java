import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution5644 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int time;
	static int[] directA, directB;
	static int A;
	static BC[] bcs;
	static int[] dx = { 0, 0, 1, 0, -1 };
	static int[] dy = { 0, -1, 0, 1, 0 };

	static class BC {
		int x, y, range, power;

		public BC(int x, int y, int range, int power) {
			this.x = x;
			this.y = y;
			this.range = range;
			this.power = power;
		}
	}

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			time = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			directA = new int[time + 1];
			directB = new int[time + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < time; i++) {
				directA[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < time; i++) {
				directB[i] = Integer.parseInt(st.nextToken());
			}

			bcs = new BC[A];
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				bcs[i] = new BC(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())
						, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			int ax = 1, ay = 1, bx = 10, by = 10;
			int total = 0;
			for (int k = 0; k < time + 1; k++) {
				int sum = 0;
				for (int i = 0; i < A; i++) {
					for (int j = 0; j < A; j++) {
						if (i == j) {
							sum = Math.max(sum, charge(ax, ay, i));
							sum = Math.max(sum, charge(bx, by, j));
						} else {
							sum = Math.max(sum, charge(ax, ay, i) + charge(bx, by, j));
						}
					}
				}
				total += sum;
				ax += dx[directA[k]];
				ay += dy[directA[k]];
				bx += dx[directB[k]];
				by += dy[directB[k]];
			}

			sb.append("#").append(tc).append(" ").append(total).append("\n");
		}
		System.out.println(sb);
	}

	static int charge(int x, int y, int bc) {
		int dist = Math.abs(bcs[bc].x - x) + Math.abs(bcs[bc].y - y);
		if (dist <= bcs[bc].range)
			return bcs[bc].power;

		return 0;
	}
}
