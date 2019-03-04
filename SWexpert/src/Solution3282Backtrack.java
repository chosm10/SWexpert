import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution3282Backtrack {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, K;
	
	static class Item {
		int value, volume;
		double weight;
		public Item(int value, int volume) {
			this.value = value;
			this.volume = volume;
			this.weight = value / (double)volume;
		}
		
	}
	static int ans = 0;
	public static void main(String[] args) throws Exception {
		StringBuilder result = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			Item[] items = new Item[N];
			for(int i = 0; i < items.length; i++) {
				st = new StringTokenizer(br.readLine());
				items[i] = new Item(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			Arrays.sort(items, new Comparator<Item>() {

				@Override
				public int compare(Item o1, Item o2) {
					return Double.compare(o2.weight, o1.weight);
				}
			});
			ans = 0;
			backtrack(items, 0, 0, 0);
			result.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(result);
	}
	
	static void backtrack(Item[] items, int depth, int volume, int value) {
		if(depth == items.length) {
			if(ans < value)
				ans = value;
			return;
		}
		
		double bound = value;
		int curr_volume = volume;
		
		for(int i = depth; i < items.length; i++) {
			if(curr_volume + items[i].volume <= K) {
				bound += items[i].value;
				curr_volume += items[i].volume;
			}
			else {
				int remain = (K - curr_volume);
				bound += (remain * items[i].weight);
				break;
			}
		}
		
		if(bound < ans)
			return;
		
		if(items[depth].volume + volume <= K) 
			backtrack(items, depth + 1, items[depth].volume + volume, items[depth].value + value);
		backtrack(items, depth + 1, volume, value);
	}
}
