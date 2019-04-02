import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution7206 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	
	static int rst=0;
	static HashSet<Integer> memo[];
	public static void main(String[] args) throws Exception {
//		ArrayList<Integer> arr = new ArrayList<>();
//		arr.add(1);
//		System.out.println(split(6, arr));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
		 //1. 입력받기
			String a = br.readLine();
			rst=0;
			
			
			
			
		//2. powerset 으로 콤마 짜르기
			boolean split[] = new boolean[a.length()-1];
			powerset(0,a,split,1);
			
			
			sb.append("#").append(tc).append(" ").append("").append("\n");
		}
		System.out.println(sb);
	}

	private static void powerset(int idx, String a, boolean[] split, int turn) {
		// TODO Auto-generated method stub
		if(idx == split.length) {
			//자르지 않는 경우를 먼저 검사 하기
			int cnt=0;
			for(int i=0; i<split.length; i++) {
				if(split[i])cnt++;
			}
			//자르지 않는 경우는 종료
			if(cnt==0)return;
			
			//T는 다 자르기
			String tmp[] = new String[cnt+1];
			cnt=0;
			int sidx=0;
			
			// 1, 1, 1 => [1,1,null]
			for(int i=0; i<split.length; i++) {
				if(split[i]) {
					tmp[cnt++] = a.substring(sidx, i+1);
					sidx = i+1;
				}
			}
			tmp[cnt]= a.substring(sidx, a.length());
			
			// 곱해주기
			int sum=1;
			for(int i=0; i<tmp.length; i++) {
				sum *= Integer.parseInt(tmp[i]);
			}
			
			// 한자리수면 턴을 최대값 비교
			if(sum<10) {
				rst = Math.max(rst, turn);
				return;
			}
			
			else if(sum>=10) {
				// 다음 자리수 쪼개기
				split = new boolean[Integer.toString(sum).length()-1];
				powerset(0,Integer.toString(sum), split,turn+1);
			}
			
			return;
			
			
		}//end option
		
		
		split[idx]=true;
		powerset(idx+1, a, split, turn);
		split[idx]=false;
		powerset(idx+1, a, split, turn);
		
	}

	// idx번까지 자른것과 그다음것까지 자른거 곱
//	static int split(int num, ArrayList<Integer> bar) {
//		String sNum = String.valueOf(num);
//		int result = 1;
//		for (int i = 0; i < bar.size() - 1; i++) {
//			int n = Integer.parseInt(sNum.substring(bar.get(i) + 1, bar.get(i + 1) + 1));
//			result *= n;
//		}
//		String start = sNum.substring(0, bar.get(0) + 1);
//		if (!start.equals(""))
//			result *= Integer.parseInt(start);
//		String end = sNum.substring(bar.get(bar.size() - 1) + 1);
//		if (!end.equals(""))
//			result *= Integer.parseInt(end);
//		System.out.println(result);
//		return result;
//	}
//
//	static int ans;
//
//	static void power(int num, boolean[] select, int depth, int cnt) {
//		if(select.length == 0)
//			return;
//		if (depth == select.length) {
//			ArrayList<Integer> arr = new ArrayList<>();
//			for (int i = 0; i < select.length; i++) {
//				if (select[i])
//					arr.add(i);
//			}
//			System.out.println(Arrays.toString(select));
//			if(arr.size() == 0)
//				return;
//			System.out.println("Hello");
//			System.out.println(num + " " + arr.get(0));
//			int result = split(num, arr);
//			if (result < 10) {
//				if (ans < cnt)
//					ans = cnt;
//			} else {
//				solve(result, cnt + 1);
//			}
//			return;
//		}
//		select[depth] = true;
//		power(num, select, depth + 1, cnt);
//		select[depth] = false;
//		power(num, select, depth + 1, cnt);
//	}
//
//	static void solve(int num, int cnt) {
//
//		String sNum = String.valueOf(num);
//		System.out.println(num);
//		power(num, new boolean[sNum.length() - 1], 1, 0);
//
////		for (int i = 0; i < String.valueOf(num).length() - 1; i++) {
//////			int result = split(num, i);
//////			쪼갠게 10보다 작으면 끝이지
//
////		}
//	}
}
