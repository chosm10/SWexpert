import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution1225 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static Queue<Integer> q = new LinkedList<>();
	public static void main(String[] args) throws Exception {
		StringBuilder result = new StringBuilder();
		for(int tc = 1; tc <= 10; tc++) {
			br.readLine();
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 8; i++) {
				q.offer(Integer.parseInt(st.nextToken()));
			}
			int diff = 1;
			while(!q.isEmpty()) {
				int front = q.poll();
				if(front - diff <= 0) {
					front = 0;
					q.offer(front);
					break;
				}
				front -= diff;
				q.offer(front);
				
				if(diff == 5) {
					diff = 1;
					continue;
				}
				diff++;
			}
			result.append("#").append(tc).append(" ");
			while(!q.isEmpty()) {
				result.append(q.poll()).append(" ");
			}
			result.append("\n");
		}
		bw.write(result.toString());
		bw.flush();
		bw.close();
	}
}
