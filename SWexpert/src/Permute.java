

public class Permute {
	public static void main(String[] args) {
		
		int[] arr = {5, 6, 7, 8, 9};
		int[] arr1 = {0, 0, 0, 1, 1};
		do {
			for(int i = 0; i < arr1.length; i++) {
				if(arr1[i] == 0) {
					System.out.print(arr[i] + " ");
				}
			}
			System.out.println();
		} while(next_permutation(arr1));
		
	}
	public static boolean next_permutation(int arr[]) {
		int length = arr.length;
		int i = length - 1;
		while(i > 0 && arr[i - 1] >= arr[i])
			i--;
		if(i <= 0)
			return false;
		
		int j = length - 1;
		while(arr[j] <= arr[i - 1])
			j--;
		swap(arr, i - 1, j);
		j = length - 1;
		while(i < j) {
			swap(arr, i, j);
			i++;
			j--;
		}
			
		return true;
	}
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
