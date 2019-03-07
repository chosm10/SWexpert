import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
public class Solution1767 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] board;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static ArrayList<Point> procs; // 벽에 붙지 않은 프로세서들을 저장한 리스트
    static int N;
    static int minSum; // 프로세서 값이 최대일때 길이가 최소인 전선
    static int maxProc; // 선택 가능한 프로세서 수중 최대값
 
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());
 
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            N = Integer.parseInt(br.readLine().trim());
            board = new int[N][N];
            procs = new ArrayList<>();
 
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if (board[i][j] == 0)
                        continue;
                    // 벽에 붙은건 리스트에 않넣어
                    if (i + 1 >= N || i - 1 < 0 || j + 1 >= N || j - 1 < 0)
                        continue;
                    // 0도 아니고 벽에 붙은것도 아닌 프로세서면 리스트에 넣어
                    procs.add(new Point(j, i));
                }
            }
            minSum = 0;
            maxProc = 0;
 
            proc(board, 0, 0, 0);
 
            sb.append(minSum).append("\n");
        }
        System.out.println(sb);
    }
 
    static class Point {
        int x, y;
 
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
 
    // 프로세서 판, 프로세서 선의 길이 합, 연결한 프로세서 수, 재귀 단계 -> 프로세서 수만큼 가면 종료
    static void proc(int[][] board, int sum, int cnt, int depth) {
        // 종료조건
        if (depth == procs.size()) {
            // 현재 최대 프로세서수보다 많으면 프로세서 수랑 전선길이합을 바꿔
            if (maxProc < cnt) {
                maxProc = cnt;
                minSum = sum;
            } else if (maxProc == cnt) {
//              프로세서 수가 같으면 전선길이가 짧은걸로 바꿔
                if (minSum > sum) {
                    minSum = sum;
                }
            }
            return;
        }
 
        // depth번째에 있는 프로세서를 가져와
        Point p = procs.get(depth);
 
//      카운트가 4면, 모든 방향에 대해서 이을 선이 없는거임
        int count = 0;
        DIRECT: for (int i = 0; i < 4; i++) {
            int nx = p.x + dx[i];
            int ny = p.y + dy[i];
 
            // 밖에 나가지 않는 동안
            while (!out(nx, ny)) {
                // 중간에 프로세서가 있으면 전선을 못 이으니까 카운트하고 다른 방향으로 진행
                if (board[ny][nx] == 1) {
                    count++;
                    continue DIRECT;
                }
                nx += dx[i];
                ny += dy[i];
            }
            // 여기까지 오면 그 방향으로 전원 연결할수 있는거임
            int length = 0;
            nx = p.x + dx[i];
            ny = p.y + dy[i];
//          끝까지 1로 연결하면서 전선의 길이를 잼
            while (!out(nx, ny)) {
                length++;
                board[ny][nx] = 1;
                nx += dx[i];
                ny += dy[i];
            }
 
//          변경된 배열과 전선길이 누적을 증가시키고 프로세서 갯수증가, 깊이증가
            proc(board, sum + length, cnt + 1, depth + 1);
 
//          끝나고 나와서 원상복구
            nx = p.x + dx[i];
            ny = p.y + dy[i];
            while (!out(nx, ny)) {
                board[ny][nx] = 0;
                nx += dx[i];
                ny += dy[i];
            }
        }
//      4군데 다봤는데 연결할곳이 하나도 없으면 그냥 depth만 증가
        if (count == 4) {
            proc(board, sum, cnt, depth + 1);
        }
    }
 
    static boolean out(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= N)
            return true;
        return false;
    }
}
