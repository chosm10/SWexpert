import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Solution1244Brute {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			String input = sc.next();
			int N = sc.nextInt();
			int[] num = new int[input.length()];
			for(int i = 0; i < input.length(); i++) {
				num[i] = input.charAt(i) - '0';
			}
			visited = new HashSet<>();
			solve(num, 0, 0, N);
			System.out.println(ans);
		}
	}
	static int ans = 0;
	static HashSet<String> visited;
	//cnt가 N이 되면 끝남
	static void solve(int[] num, int idx, int cnt, int N) {
		int sum = 0;
		for(int i = 0; i < num.length; i++) {
			sum = (sum * 10 + num[i]);
		}
		if(visited.contains(sum + "")) {
			return;
		}
		
		if(cnt == N) {
			//교환횟수 모두 소진
			int score = 0;
			for(int i = 0; i < num.length; i++) {
				score = (score * 10 + num[i]);
			}
			if(score > ans)
				ans = score;
			
			return;
		}
			
		if(idx == num.length - 2) {
			//배열 끝에서 2번째 칸 도착
			if( cnt % 2 == 0)  //cnt가 0번부터 시작하기 때문에 
				solve(num, idx, N, N);
			else {
				swap(num, num.length - 2, num.length - 1);
				solve(num, idx, N, N);
			}
			
			return;
		}
		//idx 다음 칸부터 끝까지 중에서 가장 큰 숫자의 위치를 찾아서, 그놈과 idx와 swap
		
		//가장 큰 수의 위치를 찾자
		int big = idx;
		for(int i = idx + 1; i < num.length; i++) {
			if(num[big] < num[i])
				num[big] = num[i];
		}
		if(num[idx] != num[big]) {
			for (int i = idx + 1; i < num.length; i++) {
				if (num[big] == num[i]) {
					swap(num, i, idx);
					solve(num, idx + 1, cnt + 1, N);
					swap(num, i, idx);
				}
			}
		}
		else {
			solve(num, idx + 1, cnt + 1, N);
		}
	}
	static void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
}
