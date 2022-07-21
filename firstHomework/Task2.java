package firstHomework;

public class Task2 {

	public static void main(String[] args) {
		int array[] = {3, 7, 6, 13, 33, 9, -100, 25};
		System.out.print("Unsorted Array:");
		for(int i = 0; i < array.length; i++){
			System.out.print(" "+ array[i]);
		}
		
		int min, minIndex, temp, j;
		for(int i = 0; i < array.length - 1; i++){ //Selection sort
			min = array[i];
			minIndex = i;
			for(j = i + 1; j < array.length; j++){
				if(array[j] < min) {
					min = array[j];
					minIndex = j;
				}
			}
			temp = min;
			array[minIndex] = array[i];
			array[i] = temp;
		}
		
		System.out.println("");
		System.out.print("Sorted Array:");
		for(int i = 0; i < array.length; i++){
			System.out.print(" "+ array[i]);
		}
	}
}
