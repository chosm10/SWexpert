
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1289 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		int T;
		T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			String tmp = st.nextToken();
			int cnt = 0;
			if(tmp.charAt(0) == '1')
				cnt++;
			for(int i = 0; i < tmp.length() - 1; i++) {
				if(tmp.charAt(i) != tmp.charAt(i + 1))
					cnt++;
			}
			System.out.println("#" + tc + " " + cnt);
		}
	}
}
