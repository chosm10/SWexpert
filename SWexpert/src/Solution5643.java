import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution5643 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static ArrayList<Integer>[][] graph;
	static int vertex;
	static Queue<Integer> q = new LinkedList<>();
	static int ans = 0;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine().trim());
			M = Integer.parseInt(br.readLine().trim());
			graph = new ArrayList[2][N + 1];
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < graph[i].length; j++) {
					graph[i][j] = new ArrayList<>();
				}
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int shortt = Integer.parseInt(st.nextToken());
				int longg = Integer.parseInt(st.nextToken());
				graph[0][shortt].add(longg);
				graph[1][longg].add(shortt);
			}

			ans = 0;
			// i가 고른거, j가 나머지
			for (int i = 1; i <= N; i++) {
				bfs(i);
			}

			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static void bfs(int node) {
		q.clear();
		boolean[] visited = new boolean[N + 1];
		visited[node] = true;
		q.offer(node);

		int cnt = 0;
		while (!q.isEmpty()) {
			int n = q.poll();
			cnt++;
			for (int i = 0; i < graph[0][n].size(); i++) {
				int next = graph[0][n].get(i);

				if (!visited[next]) {
					visited[next] = true;
					q.offer(next);
				}
			}
		}
		q.clear();
		q.offer(node);
		while (!q.isEmpty()) {
			int n = q.poll();
			cnt++;
			for (int i = 0; i < graph[1][n].size(); i++) {
				int next = graph[1][n].get(i);

				if (!visited[next]) {
					visited[next] = true;
					q.offer(next);
				}
			}
		}
		if (cnt == N + 1)
			ans++;
	}
}
