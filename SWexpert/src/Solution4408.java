import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution4408 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		StringBuilder result = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine().trim());
			int[] way = new int[201];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int start = get(Math.min(a, b));
				int end = get(Math.max(a, b));
				for (int j = start; j <= end; j++) {
					way[j]++;
				}
			}
			int max = 0;
			for (int i = 0; i < way.length; i++) {
				if (way[i] > max)
					max = way[i];
			}

			result.append("#").append(tc).append(" ").append(max).append("\n");
		}
		System.out.println(result);
	}

	static int get(int a) {
		if (a % 2 == 0)
			return a / 2;
		else
			return (a + 1) / 2;
	}
}
