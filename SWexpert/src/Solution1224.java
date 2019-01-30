
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Solution1224 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws Exception {
		for(int tc = 1; tc <= 10; tc++) {
			br.readLine();
			String tmp = br.readLine();	
			StringBuilder sb = new StringBuilder();
			Stack<Integer> dop = new Stack<>();
			Stack<Character> op = new Stack<>();
			for (int i = 0; i < tmp.length(); i++) {
				char c = tmp.charAt(i);
				if(Character.isDigit(c)) {
					sb.append(c);
				}
				else {
					if( c == '(') {
						op.push(c);
					}
					else if( c == ')') {
						while(!op.isEmpty() && op.peek() != '(') {
							sb.append(op.pop());
						}
						op.pop();
					}
					else {
						while (!op.isEmpty() && pre(op.peek()) >= pre(c)) {
							sb.append(op.pop());
						}
						op.push(c);
					}
				}
			}
			while(!op.isEmpty()) {
				sb.append(op.pop());
			}

			for(int i = 0; i < sb.length(); i++) {
				char c = sb.charAt(i);
				if(Character.isDigit(c)) {
					dop.push(c - '0');
				}
				else {
					int num1 = dop.pop();
					int num2 = dop.pop();
					int result = 0;
					switch(c) {
					case '+':
						result = num1 + num2;
						break;
					case '-':
						result = num1 - num2;
						break;
					case '*':
						result = num1 * num2;
						break;
					case '/':
						result = num1 / num2;
						break;
					}
					dop.push(result);
				}
			}
			bw.write(new StringBuilder("#").append(tc).append(" ").append(dop.pop()).append("\n").toString());
		}
		bw.flush();
		bw.close();
	}

	static int pre(char c) {
		switch (c) {
		case '*':
		case '/':
			return 2;
		case '+':
		case '-':
			return 1;
		}
		return 0;
	}
}
