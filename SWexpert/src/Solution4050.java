import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution4050 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static Integer price[];

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			price = new Integer[N];

			st = new StringTokenizer(br.readLine());
			int sum = 0;
			for (int i = 0; i < N; i++) {
				price[i] = Integer.parseInt(st.nextToken());
				sum += price[i];
			}
			Arrays.sort(price, new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					// TODO Auto-generated method stub
					return o2 - o1;
				}
			});

			int sub = 0;
			for (int i = 0; i < N; i++) {
				if (i % 3 == 2) {
					sub += price[i];
				}
			}
			sum -= sub;
			sb.append("#").append(tc).append(" ").append(sum).append("\n");
		}
		System.out.println(sb);
	}
}
