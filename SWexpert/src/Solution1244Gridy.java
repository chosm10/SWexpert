import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
 
public class Solution1244Gridy {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static Integer[] numbers;
 
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            String pan = st.nextToken();
            int turn = Integer.parseInt(st.nextToken());
 
            numbers = new Integer[pan.length()];
            int[] cnt = new int[10];
            Integer[] maxNum = new Integer[pan.length()];
            int max = 0;
            for (int i = 0; i < pan.length(); i++) {
                numbers[i] = pan.charAt(i) - '0';
                maxNum[i] = numbers[i];
                if (max < numbers[i])
                    max = numbers[i];
                cnt[numbers[i]]++;
            }
             
            //큰수가 중복되어 있을 때 뒤에서부터 수정했을때 발생하는 문제를 해결하기 위해 필요 88832
            ArrayList<Info>[] history = new ArrayList[10];
            for(int i = 0; i < 10; i++) {
                history[i] = new ArrayList<>();
            }
            Arrays.sort(maxNum, cmp);
            for (int i = 0; i < numbers.length && turn > 0; i++) {
 
                if (numbers[i] != maxNum[i]) {
                    turn--;
                    int index = find(maxNum[i]); 
                    history[maxNum[i]].add(new Info(index, numbers[i]));//바뀐 숫자의 인덱스와 위치를 저장
                    swap(i, index);
                }
            }
 
            boolean flag = false;
            //숫자 같은게 있으면 한바퀴돌았으면 같은거끼리 걍 바꾸면됨
            for (int i = 0; i < cnt.length; i++) {
                if (cnt[i] >= 2) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {//같은게 1개도 없으면 맨뒤에꺼끼리 바꾸는거 반복
                while (turn > 0) {
                    swap(numbers.length - 1, numbers.length - 2);
                    turn--;
                }
            }
             
            //88823->88832해결
            //수정하는데 중복되게 있던 숫자들이 위치했던 곳에 바뀌어 들어간 숫자들은 무조건 내림차순 소팅
            for(int i = 0; i < history.length; i++) {
                if(history[i].size() >= 2) {
                    int[] idx = new int[history[i].size()];
                    for(int j = 0; j < idx.length; j++) {
//                      숫자를 바꿔나갈때 맨뒤에서부터 발견된걸 바꾸고 있어서 바뀐 인덱스 위치가 역순으로 뒷번호부터있다.
//                      그래서 원래 배열 순서대로 바뀐 위치의 인덱스번호를 저장해논다
                        idx[j] = history[i].get(idx.length - j - 1).index;
                    }
//                  바뀐 값들을 크기 순으로 내림차순 정렬한다
                    Collections.sort(history[i]);
//                  앞에 저장한 바뀐 인덱스 번호들에 앞에서부터 차례대로 소팅된 숫자를 넣어준다
                    for(int j = 0; j < idx.length; j++) {
                        numbers[idx[j]] = history[i].get(j).value;
                    }
                }
            }
             
            result.append("#").append(tc).append(" ");
            for (int i = 0; i < numbers.length; i++) {
                result.append(numbers[i]);
            }
            result.append("\n");
        }
        bw.write(result.toString());
        bw.flush();
        bw.close();
    }
 
    static Comparator<Integer> cmp = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            if(o1 > o2)
                return -1;
            else if(o1 < o2)
                return 1;
            else
                return 0;
        }
    };
 
    public static void swap(int i, int j) {
        int tmp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = tmp;
    }
 
    public static int find(int num) {
        for (int i = numbers.length - 1; i >= 0; i--) {
            if (numbers[i] == num)
                return i;
        }
        return 0;
    }
    static class Info implements Comparable<Info>{
        int index, value;
 
        public Info(int index, int value) {
            this.index = index;
            this.value = value;
        }
 
        @Override
        public int compareTo(Info o) {
            if(this.value > o.value)
                return -1;
            else if(this.value < o.value)
                return 1;
            else
                return 0;
        }
         
    }
}
