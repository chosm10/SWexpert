import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution1213rg {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception {
		int tc = 0;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 10; i++) {
			tc = Integer.parseInt(br.readLine());
			String find = br.readLine();
			String sentence = br.readLine();
			
			String[] arr = sentence.split(find);
			int result = arr.length - 1;
			boolean flag1 = false;
			boolean flag2 = false;
			if(find.equals(sentence.substring(0, find.length()))) {
				flag1 = true;
			}
			if(find.equals(sentence.substring(sentence.length() - find.length(), sentence.length()))) {
				flag1 = true;
			}

			if(flag1)
				result++;
			if(flag2)
				result++;
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
