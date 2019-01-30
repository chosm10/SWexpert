
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution6782 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static boolean binarySearch(ArrayList<Long> arr, int key) {
		int start = 0;
		int end = arr.size() - 1;
		int mid;
		while(start <= end) {
			mid = (start + end) / 2;
			if(key > arr.get(mid)) {
				start = mid + 1;
			}
			else if(key < arr.get(mid)) {
				end = mid - 1;
			}
			else {
				return true;
			}	
		}
		return false;
	}
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		int limit = (int)Math.pow(10, 6);
		ArrayList<Long> arr = new ArrayList<>();
		for(long i = 2; i <= limit; i++) {
			arr.add(i*i);
		}

		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int result = 0;
			while(N != 2) {
				if(binarySearch(arr, N)) {
					N = (int)Math.sqrt(N);
				}
				else
					N++;
				result++;
			}
			System.out.println(new StringBuilder("#").append(tc).append(" ").append(result));
		}
	}
	
}
