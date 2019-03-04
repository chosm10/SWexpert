import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution5986 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws Exception{
		boolean[] isPrime = new boolean[1001];
		for(int i = 2; i * i <= 1000; i++) {
			if(!isPrime[i]) {
				for(int j = 2 * i; j <= 1000; j += i) {
					if(j % i == 0) {
						isPrime[j] = true;
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine().trim());
			ArrayList<Integer> arr = new ArrayList<>();
			for(int i = 2; i <= N; i++) {
				if(!isPrime[i])
					arr.add(i);
			}
			
		}
	}
}
