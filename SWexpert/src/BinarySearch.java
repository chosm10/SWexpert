

public class BinarySearch {
	public static int binarySearch(int arr[], int key) {
		int start = 0;
		int end = arr.length - 1;
		int mid;
		while(start <= end) {
			mid = (start + end) >> 1;
			if(key > arr[mid]) {
				start = mid + 1;
			}
			else if(key < arr[mid]) {
				end = mid - 1;
			}
			else {
				return mid;
			}
		}
		return -(start + 1);
	}

	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5, 8, 9, 10};
		int a = binarySearch(arr, 4);

		System.out.println(a);
	}
}
