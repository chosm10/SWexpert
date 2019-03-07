
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution4530 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static long[] fourCnt;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		fourCnt = new long[13];
		fourCnt[1] = 1;
		for (int i = 2; i < fourCnt.length; i++) {
			fourCnt[i] = 10 * (long) Math.pow(10, i - 1) + fourCnt[i - 1] * (long) Math.pow(9, i - 1);
		}

		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			long A = Long.parseLong(st.nextToken());
			long B = Long.parseLong(st.nextToken());
			long result = 0;
			long resultA = getNum(A);
			long resultB = getNum(B);
			System.out.println(resultA + " " + resultB);
			result = Math.abs(resultA - resultB);
			if(!(A > 0 && B > 0) && !(A < 0 && B < 0)) {
				result -= 1;
			} 
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

	static long getNum(long num) {
		String s = Long.toString(num);
		StringBuilder sb = new StringBuilder();
		boolean isMinus = false;
		if(s.charAt(0) == '-') {
			isMinus = true;
		} 
		sb.reverse();
		long sum = 0;
		for(int i = 0; i < sb.length(); i++) {
			char c = sb.charAt(i);
			if(c != '-') {
				int digit = c - '0';
				if(digit < 4) {
					sum += digit * (Math.pow(10, i) - fourCnt[i]); 
				} else {
					sum += digit * (Math.pow(10, i) - fourCnt[i]) - Math.pow(10, i) + fourCnt[i];
				}
			}
		}
		
		if(isMinus) {
			sum = -sum;
		}
		return sum;
	}
}
