
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution1206 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static final int[] dx = { -2, -1, 1, 2 };

	public static void main(String[] args) throws Exception {
		int width = 0;
		int result = 0;
		int nx = 0;
		int minDiff = 0;
		for (int tc = 1; tc <= 10; tc++) {
			width = Integer.parseInt(br.readLine());
			int[] height = new int[width];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < height.length; i++) {
				height[i] = Integer.parseInt(st.nextToken());
			}
			result = 0;
			for (int i = 0; i < height.length; i++) {
				minDiff = 256;
				for (int j = 0; j < 4; j++) {
					nx = i + dx[j];
					if (nx < 0 || nx >= height.length)
						continue;
					if (minDiff > height[i] - height[nx]) {
						minDiff = height[i] - height[nx];
					}
				}
				if (minDiff > 0) {
					result += minDiff;
				}
			}
			bw.write(new StringBuilder("#").append(tc).append(" ").append(result).append("\n").toString());
		}
		bw.flush();
		bw.close();
	}
}
