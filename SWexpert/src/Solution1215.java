import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution1215 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception {
		System.out.println(palindrom("abcba"));
		for(int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine());
			String[] board = new String[8];
			for(int i = 0; i < 8; i++) {
				board[i] = br.readLine();
			}
			
		}
	}
	static boolean palindrom(String s) {
		int len = s.length();
		if(len % 2 == 0) {
			if(s.substring(0, len / 2).equals(reverse(s.substring(len / 2, s.length())))) {
				return true;
			}
			else 
				return false;
		}
		else {
			if(s.substring(0, (len / 2)).equals(reverse(s.substring((len / 2) + 1, s.length())))) {
				return true;
			}
			else return false;
		}
	}
	
	static String reverse(String s) {
		StringBuilder sb = new StringBuilder();
		for(int i = s.length() - 1; i >= 0; i--) {
			sb.append(s.charAt(i));
		}
		return sb.toString();
	}
}
