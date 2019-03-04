

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution3214Prim {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[] parents;
	static int[] rank;
	static ArrayList<Node>[] arr;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			arr = new ArrayList[V + 1];

			for (int i = 0; i < arr.length; i++) {
				arr[i] = new ArrayList<>();
			}
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				long weight = Long.parseLong(st.nextToken());
				arr[a].add(new Node(b, weight));
				arr[b].add(new Node(a, weight));
			}
			
			boolean[] check = new boolean[V + 1];
			check[1] = true;
			PriorityQueue<Node> q = new PriorityQueue<>();
			q.addAll(arr[1]);
			
			long sum = 0;
			int cnt = 0;
			while(!q.isEmpty()) {
				if(cnt == V)
					break;
				Node n = q.poll();
				
				if(check[n.dst]) 
					continue;
				
				check[n.dst] = true;
				q.addAll(arr[n.dst]);
				sum += n.weight;
				cnt++;
			}

			sb.append("#").append(tc).append(" ").append(sum).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static class Node implements Comparable<Node> {
		int dst;
		long weight;

		public Node(int dst, long weight) {
			this.dst = dst;
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

}
