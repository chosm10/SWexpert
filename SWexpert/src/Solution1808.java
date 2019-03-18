import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution1808 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static boolean[] button;
    static int X;
    static int ans;
 
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());
 
        for (int tc = 1; tc <= T; tc++) {
            button = new boolean[10];
 
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 10; i++) {
                if (st.nextToken().equals("1")) {
                    button[i] = true;
                }
            }
            X = Integer.parseInt(br.readLine().trim());
            ans = Integer.MAX_VALUE;
             
            solve(X);
            sb.append("#").append(tc).append(" ").append(ans == Integer.MAX_VALUE ? -1 : ans + 1).append("\n");
        }
        System.out.println(sb);
    }
 
    static boolean canButton(int num) {
 
        while (num > 0) {
            if (!button[num % 10])
                return false;
            num /= 10;
        }
        return true;
    }
 
    static int solve(int num) {
        if (canButton(num)) {
            if (X == num) {
                ans = String.valueOf(num).length();
            }
            return String.valueOf(num).length();
        } else {
            int result = -1;
            for(int i = 2; i * i <= num; i++) {
                if(num % i == 0 && canButton(i)) {
                    int cntFirst = String.valueOf(i).length() + 1;
                    int cntSecond = solve(num / i);
                    if(cntSecond != -1) {
                        result = cntFirst + cntSecond;
                        if(ans > result && num == X)
                            ans = result;
                    }
                     
                }               
            }
            return result;
        }
         
    }
}
