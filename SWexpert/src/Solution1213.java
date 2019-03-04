import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution1213 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int ans = 0;
	public static void main(String[] args) throws Exception {
		int tc = 0;
		String find = null;
		String sentence = null;
		int length = 0;
		for (int t = 0; t < 10; t++) {
			tc = Integer.parseInt(br.readLine());
			find = br.readLine();
			sentence = br.readLine();
			char first = find.charAt(0);
			char last = find.charAt(find.length() - 1);
			length = find.length();
			ans = 0;
			for(int i = 0; i < sentence.length() - length; i++) {
				if(sentence.charAt(i) == first && find.charAt(i + length - 1) == last) {
					boolean flag = true;
					for(int j = 0; j < length; j++) {
						if(find.charAt(j) != sentence.charAt(i + j))
							flag = false;
					}
					if(flag) {
						ans++;
						i += length - 1;
					}
				}
			}
			
			
		}
	}
}
