
import java.util.Scanner;

public class Solution1979 {
	static int[][] board;
	static int[] d = {-1, 1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int K = sc.nextInt();
			
			board = new int[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					board[i][j] = sc.nextInt();
				}
			}
			int cnt = 0;
			int[] x = new int[2];
			int[] y = new int[2];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(board[i][j] == 1) {
						
						for(int l = 0; l < 2; l++) {
							int nx = j;
							int ny = i;	
							while(true) {
								nx += d[l];
								if((nx < 0 || nx >= N) || (board[i][nx] != 1)) {
									nx -= d[l];
									break;
								}
							}
							while(true) {
								ny += d[l];
								if((ny < 0 || ny >= N) || (board[ny][j] != 1)) {
									ny -= d[l];
									break;
								}
							}
							x[l] = nx;
							y[l] = ny;
						}
						if(x[1] - x[0] + 1  == K) {
							cnt++;
						}
							
						if(y[1] - y[0] + 1 == K)
							cnt++;
					}
				}
			}
			cnt /= K;
			System.out.println(new StringBuilder("#").append(tc).append(" ").append(cnt));
		}
	}
}
