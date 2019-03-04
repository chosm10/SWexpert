
public class Power {
	public static void main(String[] args) {
		System.out.println(power(2, 5));
	}
	static long power(int a, int n) {
		int cnt = 0;
		int log = n;
		while(log != 1) {
			log >>= 1;
			cnt++;
		}
		long[] memo = new long[cnt + 1];
		memo[0] = a;
		for(int i = 1; i < memo.length; i++) {
			memo[i] = memo[i - 1] * memo[i - 1];
		}
		long result = 1;
		for(int i = 0; i < cnt + 1; i++) {
			if(((1 << i) & n) != 0) {
				result *= memo[i];
			}
		}
		return result;
	}
}
