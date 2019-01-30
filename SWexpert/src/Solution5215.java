
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution5215 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, L;
	static int[][] components;
	public static void main(String[] args) throws Exception {
		int T;
		T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			components = new int[N][2];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				components[i][0] = Integer.parseInt(st.nextToken());
				components[i][1] = Integer.parseInt(st.nextToken());
			}
			int max = 0;
			for(int i = 1; i <= N; i++) {
				int[] cmb = new int[N];
				for(int j = 0; j < N; j++) {
					cmb[j] = 1;
				}
				for(int j = 0; j < i; j++) {
					cmb[j] = 0;
				}
				int sumFull = 0;
				int sumKal = 0;
				do {
					for(int j = 0; j < N; j++) {
						if(cmb[j] == 0) {
							sumFull += components[j][0];
							sumKal += components[j][1];
						}
						if(sumKal > L) {
							break;
						}
						if(max < sumFull) {
							max = sumFull;
						}
					}
					sumFull = 0;
					sumKal = 0;
				} while(permutation(cmb));
			}
			
			System.out.println("#" + tc + " " + max);
		}
	}
	public static boolean permutation(int arr[]) {
		int length = arr.length;
		int i = length - 1;
		while(i > 0 && arr[i - 1] >= arr[i])
			i--;
		if(i <= 0)
			return false;
		
		int j = length - 1;
		while(arr[j] <= arr[i - 1])
			j--;
		swap(arr, i - 1, j);
		j = length - 1;
		while(i < j) {
			swap(arr, i, j);
			i++;
			j--;
		}
			
		return true;
	}
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
