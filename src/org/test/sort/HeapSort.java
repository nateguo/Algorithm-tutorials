package org.test.sort;

public class HeapSort {
	
	public void sort(int[] array) {
		buildMaxHeap(array);
		for(int i=array.length-1; i > 0; i--) {
			array[0] = array[0] ^ array[i];
			array[i] = array[0] ^ array[i];
			array[0] = array[0] ^ array[i];
			maxHeapify(array, 0, i);
		}
	}
	
	public void buildMaxHeap(int[] array) {
		
		for(int i=(array.length << 1); i >= 0; i--) {
			maxHeapify(array, i, array.length);
		}
	}
	
	public void maxHeapify(int[] array, int loc, int len) {
		int left = (loc << 1) + 1;
		int right = (loc << 1) + 2;
		int largest;
		if(left < len && array[left] > array[loc])
			largest = left;
		else
			largest = loc;
		if(right < len && array[right] > array[largest])
			largest = right;
		if(largest != loc){
			array[loc] = array[loc] ^ array[largest];
			array[largest] = array[loc] ^ array[largest];
			array[loc] = array[loc] ^ array[largest];
			maxHeapify(array, largest,len);
		}
		
	}
	
	public static void main(String[] args) {
		HeapSort hs = new HeapSort();
		int[] arr = {16,4,9,14,7,3,10,2,8,1};
//		hs.buildMaxHeap(arr);
		hs.sort(arr);
		for(int i=0; i<arr.length;i++) {
			System.out.print("   " + arr[i]);
		}
		
	}

}
