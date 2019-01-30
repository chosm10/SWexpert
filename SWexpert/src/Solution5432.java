
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Solution5432 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<Integer>();
		for(int tc = 1; tc <= T; tc++) {
			stack.clear();
			String tmp = br.readLine();
			char[] brace = new char[tmp.length()];
			for(int i = 0; i < tmp.length(); i++) {
				brace[i] = tmp.charAt(i);
			}
			
			int cnt = 0;
			for(int i = 0; i < brace.length; i++) {
				if(brace[i] == '(') {
					stack.push(i);
				}
				else if(brace[i] == ')') {
					if(i - stack.peek() == 1) {
						stack.pop();
						cnt += stack.size();
					}
					else {
						stack.pop();
						cnt += 1;
					}
					
				}
			}
			bw.write(new StringBuilder("#").append(tc).append(" ").append(cnt).append("\n").toString());
		}
		
		bw.flush();
		bw.close();
	}
}