import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution1209 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	final static int MAX = 100;
	static int[][] board;
	public static void main(String[] args) throws Exception {
		StringBuilder result = new StringBuilder();
		for(int t = 0; t < 10; t++) {
			int tc = Integer.parseInt(br.readLine());
			board = new int[MAX][MAX];
			for(int i = 0; i < MAX; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < MAX; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int sum1 = 0;
			int sum2 = 0;
			int sum3 = 0;
			int sum4 = 0;
			int max = -1;
			for(int i = 0; i < MAX; i++) {
				sum1 = 0;
				sum2 = 0;
				sum3 += board[i][i];
				sum4 += board[i][MAX - 1 - i];
				for(int j = 0; j < MAX; j++) {
					sum1 += board[i][j];
					sum2 += board[j][i];
				}
				if(max < sum1)
					max = sum1;
				if(max < sum2)
					max = sum2;
			}
			if(max < sum3)
				max = sum3;
			if(max < sum4)
				max = sum4;
			result.append("#").append(tc).append(" ").append(max).append("\n");
		}
		bw.write(result.toString());
		bw.flush();
		bw.close();
	}

}
