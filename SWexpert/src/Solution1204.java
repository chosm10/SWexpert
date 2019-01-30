
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1204 {
 static final int SCORE_MAX = 101;
 static final int STUDENT_MAX = 1000;
 static int[] nums;
 public static void main(String[] args) throws Exception {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  StringTokenizer st = new StringTokenizer(br.readLine());;
  
  int T = Integer.parseInt(st.nextToken());
  for(int tc = 1; tc <= T; tc++) {
   st = new StringTokenizer(br.readLine());
   int t = Integer.parseInt(st.nextToken());
   nums = new int[SCORE_MAX];
   
   int most_frequent = 0;
   int max = -1;
   int num = 0;
   st = new StringTokenizer(br.readLine());
   for(int i = 0; i < STUDENT_MAX; i++) {
    num = Integer.parseInt(st.nextToken());
    nums[num]++;
    if(max <= nums[num]) {
     max = nums[num];
     most_frequent = num;
    }
   }

   System.out.println("#" + t + " " + most_frequent);
  }
 }
}
