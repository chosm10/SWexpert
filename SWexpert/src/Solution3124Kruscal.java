import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution3124Kruscal {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[] parents;
	static int[] rank;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			parents = new int[V + 1];
			rank = new int[V + 1];
			for (int i = 0; i < parents.length; i++)
				makeSet(i);

			ArrayList<Node> arr = new ArrayList<>();
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				long weight = Long.parseLong(st.nextToken());
				arr.add(new Node(a, b, weight));
			}
			Collections.sort(arr);
			long sum = 0;
			int cnt = 0;
			for (int i = 0; i < arr.size(); i++) {
				Node n = arr.get(i);
				parents[n.a] = findSet(n.a);
				parents[n.b] = findSet(n.b);
				if (parents[n.a] != parents[n.b]) {
					cnt++;
					union(n.a, n.b);
					sum += n.weight;
					if (cnt == V - 1)
						break;
				}
			}
			sb.append("#").append(tc).append(" ").append(sum).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static class Node implements Comparable<Node> {
		int a, b;
		long weight;

		public Node(int a, int b, long weight) {
			this.a = a;
			this.b = b;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			if (this.weight > o.weight)
				return 1;
			else if (this.weight < o.weight)
				return -1;
			else
				return 0;
		}

	}

	static void makeSet(int x) {
		parents[x] = x;
	}

	static int findSet(int x) {
		if (parents[x] == x)
			return x;

		parents[x] = findSet(parents[x]);
		return parents[x];
	}

	static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);

		if (rank[px] > rank[py]) {
			parents[py] = px;
		} else {
			parents[px] = py;
			if (rank[px] == rank[py])
				rank[py]++;
		}

	}
}
