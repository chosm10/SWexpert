import java.util.Arrays;

public class Permute {
	public static void main(String[] args) {
		
		int[] arr = {1,2,3,4,5};
		int[] arr1 = {0, 0, 0, 1, 1};
		next_permutation(arr, 0);
//		do {
//			for(int i = 0; i < arr1.length; i++) {
//				if(arr1[i] == 0) {
//					System.out.print(arr[i] + " ");
//				}
//			}
//			System.out.println();
//		} while(next_permutation(arr1));
		
	}
//	public static boolean next_permutation(int arr[]) {
//		int length = arr.length;
//		int i = length - 1;
//		while(i > 0 && arr[i - 1] >= arr[i])
//			i--;
//		if(i <= 0)
//			return false;
//		
//		int j = length - 1;
//		while(arr[j] <= arr[i - 1])
//			j--;
//		swap(arr, i - 1, j);
//		j = length - 1;
//		while(i < j) {
//			swap(arr, i, j);
//			i++;
//			j--;
//		}
//			
//		return true;
//	}
	public static void next_permutation(int[] arr, int depth) {
        if (depth == arr.length) {
            System.out.println(Arrays.toString(arr));
            return;
        }
        for (int i = depth; i < arr.length; i++) {
            swap(arr, i, depth);
            next_permutation(arr, depth + 1);
            swap(arr, i, depth);
        }
    }
//	public static void next_permutation(int[] arr, int depth, int cnt) {
//        if (depth == arr.length) {
//            return;
//        }
//        if(cnt == 2) {
//        	System.out.println(Arrays.toString(arr));
//        }
//        	
//        for (int i = depth; i < arr.length; i++) {
//        	next_permutation(arr, depth + 1, cnt);
//            swap(arr, i, depth);
//            next_permutation(arr, depth + 1, cnt + 1);
//            swap(arr, i, depth);
//        }
//    }
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
