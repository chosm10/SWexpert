import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution1238 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, start;
	static ArrayList<Integer>[] arr;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			arr = new ArrayList[101];

			for (int i = 1; i < arr.length; i++) {
				arr[i] = new ArrayList<>();
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N / 2; i++) {
				arr[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
			}

			bfs(start);
			sb.append("#").append(tc).append(" ").append(maxValue).append("\n");
		}
		System.out.println(sb);
	}

	static int max;
	static int maxValue;

	static void bfs(int node) {
		max = 0;
		maxValue = 1;
		boolean[] visited = new boolean[101];
		visited[node] = true;
		Queue<Point> q = new LinkedList<Point>();
		q.offer(new Point(node, 0));

		while (!q.isEmpty()) {
			Point now = q.poll();

			if (max < now.cnt) {
				max = now.cnt;
				maxValue = now.value;
			} else if (max == now.cnt) {
				if (maxValue < now.value)
					maxValue = now.value;
			}
			for (int i = 0; i < arr[now.value].size(); i++) {
				int next = arr[now.value].get(i);

				if (!visited[next]) {
					visited[next] = true;
					q.offer(new Point(next, now.cnt + 1));
				}
			}
		}
	}

	static class Point {
		int value, cnt;

		public Point(int value, int cnt) {
			super();
			this.value = value;
			this.cnt = cnt;
		}

	}
}
