
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution1211 {
	static final int MAX = 100;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	static int[][] board;
	public static void main(String[] args) throws Exception {
		for(int t = 0; t < 10; t++) {
			int tc = Integer.parseInt(br.readLine());
			board = new int[MAX][MAX];
			int[] x_point = new int[MAX];
			//ArrayList<Integer> x_point = new ArrayList<>();
			for(int i = 0; i < MAX; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < MAX; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int x_size = 0;
			for(int i = 0; i < MAX; i++) {
				if(board[0][i] == 1) {
					x_point[x_size++] = i;
//					x_point.add(i);
				}
			}
			int x_p = 0;
			int dist = 0;
			int x;
			int startX;
			int maxX = 0;
			int minDist = MAX * MAX;
			//기본 y축 100칸 가는건 ++안하고 처음부터 주고 시작하고 x축 움직이는건
			//1인거만 저장해논 리스트에서 그 다음인덱스의 값만큼을 뺀 값이 x축움직인거리여서 그거만큼 더해줌
			//그렇게해서 끝까지 했을때 거리가 짧은걸 계속 저장, 거리가 최소값이랑 같으면 시작점 인덱스를 큰걸로 바꿔줌
			for(int i = 0; i < x_size; i++) {
				startX = i;
				x_p = i;
				dist = MAX;
				x = x_point[x_p];
				for(int j = 0; j < MAX; j++) {
					if(x - 1 >= 0 && board[j][x - 1] == 1) {
						dist += (x - x_point[x_p - 1]);
						x = x_point[--x_p];
					}
					else if(x + 1 < MAX && board[j][x + 1] == 1) {
						dist += (x_point[x_p + 1] - x);
						x = x_point[++x_p];
					}
				}
				if(minDist > dist) {
					minDist = dist;
					maxX = startX;
				}
				else if(minDist == dist) {
					if(maxX < startX) {
						maxX = startX;
					}
				}
			}
			bw.write(new StringBuilder("#").append(tc).append(" ").append(x_point[maxX] + "\n").toString());			
		}
		bw.flush();
		bw.close();
	}
}
