
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Solution1222 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static Stack<Integer> s = new Stack<>();
	public static void main(String[] args) throws Exception {
		for(int tc = 1; tc <= 10; tc++) {
			int len = Integer.parseInt(br.readLine());
			String tmp = br.readLine();
			for(int i = 0; i < len; i++) {
				if(tmp.charAt(i) != '+') {
					s.push(tmp.charAt(i) - '0');
				}
			}
			int result = 0;
			while(!s.isEmpty()) {
				result += s.pop();
			}
			bw.write(new StringBuilder("#").append(tc).append(" ").append(result).append("\n").toString());
		}
		bw.flush();
		bw.close();
	}
}
