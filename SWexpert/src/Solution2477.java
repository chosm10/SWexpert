import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution2477 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N, M, K, A, B;
	static int[] a, b, t;
 	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		StringBuilder result = new StringBuilder();
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			a = new int[N + 1];
			b = new int[M + 1];
			t = new int[K + 1];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i < a.length; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i < b.length; i++) {
				b[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i < a.length; i++) {
				t[i] = Integer.parseInt(st.nextToken());
			}
			
			result.append("#").append(tc).append(" ");
		}
		bw.write(result.toString());
		bw.flush();
		bw.close();
	}
}
