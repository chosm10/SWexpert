
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Solution1218 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); 
	public static void main(String[] args) throws Exception {
		for(int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine());
			char[] arr = new char[N];
			String tmp = br.readLine();
			for(int i = 0; i < arr.length; i++) {
				arr[i] = tmp.charAt(i);
			}
			Stack<Character> s = new Stack<>();
			s.push(arr[0]);
			boolean ok = true;
			for(int i = 1; i < arr.length; i++) {
				if(arr[i] == '[' || arr[i] == '{' || arr[i] == '(' || arr[i] == '<') 
					s.push(arr[i]);
				else {
					if(arr[i] - s.peek() != 1 && arr[i] - s.peek() != 2) {
						bw.write(new StringBuilder("#").append(tc).append(" ").append(0 + "\n").toString());
						ok = false;
						break;
					}
					s.pop();
				}
			}
			if(ok) {
				bw.write(new StringBuilder("#").append(tc).append(" ").append(1 + "\n").toString());
			}
		}
		bw.flush();
		bw.close();
	}
}
