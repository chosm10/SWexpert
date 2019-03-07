import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution3752 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] score;
	static HashSet<Integer> set;
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			int N = Integer.parseInt(br.readLine().trim());
			score = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				score[i] = Integer.parseInt(st.nextToken());
			}
			set = new HashSet<>();
			power(score, 0, 0);
			sb.append(set.size()).append("\n");
		}
		System.out.println(sb);
	}
	
	
	static void power(int[] score, int depth, int sum) {
		if(depth == score.length) {
			set.add(sum);
			return;
		}
		
		power(score, depth + 1, sum + score[depth]);
		power(score, depth + 1, sum);
	}
}
