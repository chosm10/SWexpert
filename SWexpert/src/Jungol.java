import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Jungol {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		int N = 0;
		Stack<Building> stack = new Stack<>(); 
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			int[] record = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				int num = Integer.parseInt(st.nextToken());
				while(!stack.isEmpty() && stack.peek().height < num) {
					stack.pop();
				}
				if(stack.isEmpty()) {
					record[i] = 0;
				}
				else {
					record[i] = stack.peek().index + 1;
				}
				
				stack.push(new Building(num, i));
			}
			
			StringBuilder sb = new StringBuilder("#").append(tc).append(" ");
			for(int i = 0; i < N ; i++) {
				sb.append(record[i]).append(" ");
			}
			sb.append("\n");
			bw.write(sb.toString());
		}
		bw.flush();
		bw.close();
	}
	static class Building {
		int height, index;
		public Building(int height, int index) {
			this.height = height;
			this.index = index;
		}
	}
}
