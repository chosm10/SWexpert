import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
public class Solution7208 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static ArrayList<Integer>[] arr;
    static int[] color;
    static int[] originColor;
 
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());
 
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            arr = new ArrayList[N + 1];
            color = new int[N + 1];
            originColor = new int[N + 1];
 
            for (int i = 1; i <= N; i++) {
                arr[i] = new ArrayList<>();
            }
 
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                color[i] = Integer.parseInt(st.nextToken());
                originColor[i] = color[i];
            }
 
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    if (num == 1) {
                        arr[i].add(j);
                    }
                }
            }
            min = Integer.MAX_VALUE;
            change(color, 1);
            sb.append("#").append(tc).append(" ").append(min).append("\n");
        }
        System.out.println(sb);
    }
 
    static int min;
 
    static boolean isOk(int[] color) {
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < arr[i].size(); j++) {
                if (color[i] == color[arr[i].get(j)]) {
                    return false;
                }
            }
        }
        return true;
    }
 
    static void change(int[] color, int depth) {
        if (color.length == depth) {
            if (isOk(color)) {
                int count = 0;
                for (int i = 1; i < color.length; i++) {
                    if (color[i] != originColor[i])
                        count++;
                }
                if (min > count) {
                    min = count;
                }
            }
            return;
        }
        int nowColor = color[depth];
        for (int i = 1; i <= 4; i++) {
            if (i == color[depth]) {
                change(color, depth + 1);
            } else {
                color[depth] = i;
                change(color, depth + 1);
                color[depth] = nowColor;
            }
        }
    }
}
