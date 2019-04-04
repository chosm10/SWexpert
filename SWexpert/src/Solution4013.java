

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution4013 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int K;
	static String[] tob;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			K = Integer.parseInt(br.readLine().trim());
			tob = new String[4];

			for (int i = 0; i < 4; i++) {
				tob[i] = br.readLine().trim().replaceAll(" ", "");
			}

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken()) - 1;
				int direct = Integer.parseInt(st.nextToken());
				solve(num, direct, new boolean[4]);
			}
			int ans = getScore();
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static void solve(int num, int direct, boolean[] visit) {
		visit[num] = true;
		boolean flag0, flag1, flag2, flag3;
		switch (num) {
		case 0:
			flag1 = false;
			if (tob[0].charAt(2) != tob[1].charAt(6))
				flag1 = true;
			rotate(num, direct);
			if (!visit[1]) {
				if (flag1) {
					solve(1, -direct, visit);
				}
			}
			break;
		case 1:
			flag0 = false;
			flag2 = false;
			if (tob[1].charAt(6) != tob[0].charAt(2))
				flag0 = true;
			if (tob[1].charAt(2) != tob[2].charAt(6))
				flag2 = true;
			rotate(num, direct);
			if (!visit[0]) {
				if (flag0) {
					solve(0, -direct, visit);
				}
			}
			if (!visit[2]) {
				if (flag2) {
					solve(2, -direct, visit);
				}
			}
			break;
		case 2:
			flag1 = false;
			flag3 = false;
			if (tob[2].charAt(6) != tob[1].charAt(2))
				flag1 = true;
			if (tob[2].charAt(2) != tob[3].charAt(6))
				flag3 = true;
			rotate(num, direct);
			if (!visit[1]) {
				if (flag1) {
					solve(1, -direct, visit);
				}
			}
			if (!visit[3]) {
				if (flag3) {
					solve(3, -direct, visit);
				}
			}
			break;
		case 3:
			flag2 = false;
			if (tob[3].charAt(6) != tob[2].charAt(2))
				flag2 = true;
			rotate(num, direct);
			if (!visit[2]) {
				if (flag2) {
					solve(2, -direct, visit);
				}
			}
			break;
		}
	}

	static void rotate(int num, int direct) {
		if (direct == 1) {
			tob[num] = tob[num].charAt(tob[num].length() - 1) + tob[num].substring(0, tob[num].length() - 1);
		} else if (direct == -1) {
			tob[num] = tob[num].substring(1) + tob[num].charAt(0);
		}
	}

	static int getScore() {
		return (tob[0].charAt(0) - '0') + (tob[1].charAt(0) - '0') * 2 + (tob[2].charAt(0) - '0') * 4
				+ (tob[3].charAt(0) - '0') * 8;
	}
}
