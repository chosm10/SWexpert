import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution3074 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[] t = new int[N];
			
			int max = 0;
			for (int i = 0; i < N; i++) {
				t[i] = Integer.parseInt(br.readLine().trim());
				if(max < t[i]) {
					max = t[i];
				}
			}

			long maxTime = (long)max * M;
			long left = 0;
			long right = maxTime;
			long mid = 0;
			while (left <= right) {
				mid = (left + right) >> 1;
				long people = getPeople(t, mid);
				if (people < M) {
					left = mid + 1;
				} else if (people >= M) {
					right = mid - 1;
				} 
			}

			sb.append("#").append(tc).append(" ").append(left).append("\n");
		}
		System.out.println(sb);
	}

	static long getPeople(int[] t, long time) {
		long sum = 0;
		for (int i = 0; i < t.length; i++) {
			sum += time / t[i];
		}
		return sum;
	}
}
