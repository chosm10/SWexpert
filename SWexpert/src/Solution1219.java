
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution1219 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static ArrayList<Integer>[] arr;
	static boolean[] check;
	static boolean possible;
	public static void dfs(int node) {
		check[node] = true;
		if(node == 99) {
			possible = true;
			return;
		}
		for(int i = 0; i < arr[node].size(); i++) {
			int next = arr[node].get(i);
			if(!check[next]) {
				dfs(next);
			}
		}
	}
	public static void main(String[] args) throws Exception {
		for(int t = 0; t < 10; t++) {
			st = new StringTokenizer(br.readLine());
			int tc = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			arr = new ArrayList[100];
			for(int i = 0; i < arr.length; i++) {
				arr[i] = new ArrayList<>();
			}
			check = new boolean[100];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				int start = Integer.parseInt(st.nextToken());
				int dest = Integer.parseInt(st.nextToken());	
				arr[start].add(dest);
			}
			possible = false;
			dfs(0);
			System.out.print("#" + tc + " ");
			if(possible) {
				System.out.println("1");
			}
			else {
				System.out.println("0");
			}
		}
	}
}
