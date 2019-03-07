import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution2383 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int[][] board = new int[N][N];
	static ArrayList<People> peoples;
	static Floor[] floors;
	static int peopleNum;
	static int min;

	static class People implements Comparable<People> {
		int x, y, dist, time;

		public People(int x, int y) {
			this.x = x;
			this.y = y;
			this.time = 0;
		}

		public void setDist(int fx, int fy) {
			dist = Math.abs(fx - x) + Math.abs(fy - y);
		}

		@Override
		public int compareTo(People o) {
			return Integer.compare(this.dist, o.dist);
		}
	}

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			board = new int[N][N];
			peoples = new ArrayList<>();
			floors = new Floor[2];

			int fIdx = 0;
			peopleNum = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if (board[i][j] == 1) {
						peoples.add(new People(j, i));
						peopleNum++;
					} else if (board[i][j] > 1) {
						floors[fIdx++] = new Floor(j, i, board[i][j]);
					}
				}
			}
			boolean[] select = new boolean[peopleNum];
			min = Integer.MAX_VALUE;
			setFloor(select, 0);
			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}
		System.out.println(sb);
	}

	static class Floor {
		int x, y, time;
		ArrayList<People> peopleQ = new ArrayList<>(); // 계단에 선택된 사람들이 들어감
		ArrayList<People> readyQ = new ArrayList<>(); // 계단에 도착한 사람들이 들어감
		ArrayList<People> floorQ = new ArrayList<>(); // 3명 올라갈수 있는 계단

		public Floor(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

	static void setFloor(boolean[] select, int depth) {
		// 끝까지가면 종료
		if (depth == peoples.size()) {
			// select가 true면 1번 계단에, false면 2번 계단에 넣자
			for (int i = 0; i < select.length; i++) {
				People p = peoples.get(i);
				if (select[i]) {
					floors[0].peopleQ.add(p);
					p.setDist(floors[0].x, floors[0].y);
				} else {
					floors[1].peopleQ.add(p);
					p.setDist(floors[1].x, floors[1].y);
				}
			}
			for (int i = 0; i < 2; i++) {
				if (!floors[i].peopleQ.isEmpty())
					Collections.sort(floors[i].peopleQ); // 거리순 정렬
			}

			int timeCnt = 1; // 1분후 부터 시작함
			int peopleCnt = 0;

			while (true) {
				for (int floorIdx = 0; floorIdx < 2; floorIdx++) {
					// 레디큐에서 계단 큐로 보낼수 있으면 보내고 -> 내려 보내고 -> 피플큐에서 레디큐로 도착하고

					// 레디큐 -> 계단큐
					int nowFloorSize = floors[floorIdx].floorQ.size();
					if (nowFloorSize < 3) {
						for (int i = 0; i < 3 - nowFloorSize; i++) {
							// 레디큐에 사람이 있으면 앞에서 빼와서 계단큐에 넣음
							if (!floors[floorIdx].readyQ.isEmpty()) {
								floors[floorIdx].floorQ.add(floors[floorIdx].readyQ.remove(0));
							}
						}
					}

					// 내려보내기 우선 계단 큐에 있는놈들 시간++ 그후 시간이 계단 크기와 같으면 아웃
					for (int i = 0; i < floors[floorIdx].floorQ.size(); i++) {
						floors[floorIdx].floorQ.get(i).time++;
						if (floors[floorIdx].floorQ.get(i).time == floors[floorIdx].time) {
							floors[floorIdx].floorQ.remove(0).time = 0; // 다음 경우를 위해 people의 time을 다시 0으로 초기화
							i--; // 감소해줘야하나 헷갈림!
							peopleCnt++; // 사람수 세서 마지막에 전체사람수랑 같으면 종료
						}
					}

					// 종료조건

					// 시간되면 피플큐에서 레디큐로 넘김
					for (int i = 0; i < floors[floorIdx].peopleQ.size(); i++) {
						if (timeCnt == floors[floorIdx].peopleQ.get(i).dist) {
//						삭제해서 레디 큐에 넘김
							floors[floorIdx].readyQ.add(floors[floorIdx].peopleQ.remove(0)); // 정렬되있으니 걸리면 맨앞부터 걸림
							i--;
						}
					}
				}

				timeCnt++;
				// 전체 사람수와 계단 탈출한 사람수가 같으면 종료
				if (peopleCnt == peopleNum) {
					break;
				}
			}
			if (timeCnt < min) {
				min = timeCnt;
			}
			return;
		}

		select[depth] = true;
		setFloor(select, depth + 1);
		select[depth] = false;
		setFloor(select, depth + 1);
	}
}
