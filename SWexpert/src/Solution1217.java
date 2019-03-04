import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution1217 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	public static void main(String[] args) throws Exception {
		StringBuilder result = new StringBuilder();
		int N, M;
		for(int t = 0; t < 10; t++) {
			int tc = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			result.append("#").append(tc).append(" ").append(power(N, M)).append("\n");
		}
		bw.write(result.toString());
		bw.flush();
		bw.close();
	}
	static int power(int N, int M) {
		if(M == 1)
			return N;
		
		if(M % 2 == 0) {
			return power(N, M / 2) * power(N, M / 2);
		}
		else {
			return N * power(N, M / 2) * power(N, M / 2);
		}
	}
}
