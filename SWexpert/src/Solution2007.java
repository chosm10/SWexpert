import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution2007 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			String s = br.readLine();
			sb.append("#").append(tc).append(" ");
			for (int i = 1; i <= 10; i++) {

				String stringA = s.substring(0, i);
				String stringB = s.substring(i, 2 * i);
				if (stringA.equals(stringB)) {
					sb.append(i).append("\n");
					break;
				}
			}

		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
