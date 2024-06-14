import java.util.Arrays;
import java.util.StringJoiner;

/**
 * Resizable array structure for integers. Internal array doubles at 100% 
 * capacity and halves at 25% capacity.
 */
public class IntegerList {
	
	/** Internal array, holds contents of list. */
	private int[] array;
	/** Number of occupied cells in internal array. */
	private int size;
	/** Minimum length allowed for internal array. */
	private static final int MIN_CAPACITY = 2;
	
	/**
	 * Construct an empty list with an initial capacity of MIN_CAPACITY.
	 */
	public IntegerList() {
		array = new int[MIN_CAPACITY];
		size = 0;
	}
	
	/**
	 * Construct an empty list with the given capacity.
	 * 
	 * @param capacity  the initial length of the internal array
	 * @throws IllegalArgumentException  if capacity is less than MIN_CAPACITY
	 */
	public IntegerList(int capacity) {
		if (capacity < MIN_CAPACITY) {
			throw new IllegalArgumentException("The capacity cannot be less than 2.");
		}
		array = new int[capacity];
		
	}
	
	/**
	 * Append the given integer to the end of the list.
	 * 
	 * @param integer  the integer to be appended
	 */
	public void add(int integer) {
		insert(size, integer);
	}
	
	/**
	 * Return the first index of the given integer if it is in the list; 
	 * otherwise, return -1
	 * 
	 * @param integer  the integer to search for
	 * @return first index if integer in list else -1
	 */
	public int indexOf(int integer) {
		for (int i = 0; i < size; i++) {
			if (array[i] == integer) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Return the integer at the given index in the list.
	 * 
	 * @param index  the index of the integer to return
	 * @return the integer at the given index
	 * @throws IndexOutOfBoundsException  if index is negative or greater than 
	 *                                    or equal to size
	 */
	public int get(int index) {
		// TODO: Implement this method.
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("The index is outside the range [0, " + (size - 1) + "].");
		}
		// return int at the given index in the list
		return array[index];
	}
	
	/**
	 * Insert the given integer at the given index in the list. Shift the 
	 * existing integers to the right to make room.
	 * 
	 * @param index  the index at which the given integer is to be inserted
	 * @param integer  the integer to be inserted
	 * @throws IndexOutOfBoundsException  if index is negative or greater than 
	 *                                    size
	 */
	
	public void insert(int index, int element) {
        // Check if the index is within the bounds of the array
        if (index < 0 || index > size) {
			String message = "The index is outside the range [0, " + size 
					+ "].";
			throw new IndexOutOfBoundsException(message);
		}

        // If the array is full, expand it by 1
        if (size == array.length) {
            expandArray();
        }

        // Shift elements to the right to make room for the new element
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }

        // Insert the new element
        array[index] = element;
        size++;
    }
	// helper func
    private void expandArray() {
        int[] newArray = new int[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }
	
	/**
	 * Remove and return the integer at the given index. Shift the subsequent 
	 * integers in the list to the left to fill the gap.
	 * 
	 * @param index  the index of the integer to remove
	 * @return the removed integer
	 * @throws IndexOutOfBoundsException  if index is negative or greater than 
	 *                                    or equal to size
	 */
	public int remove(int index) {
		if (index < 0 || index >= size) {
			String message = "The index is outside the range [0, "
					+ (size - 1) + "].";
			throw new IndexOutOfBoundsException(message);
		}
		
		// Save the integer to be returned in a temporary variable.
		int removedInt = array[index];
		
		// Shift the integers above the given index to fill the gap.
		for (int idx = index + 1; idx < size; ++idx) {
			array[idx - 1] = array[idx];
		}
		
		// Reduce the size. (Note: This variable determines the range of valid 
		// indices, so there is no need to overwrite any array elements.)
		--size;
		
		// If the array is 1/4 or less full and the length is greater than the 
		// minimum capacity, halve the capacity.
		if (4*size <= array.length && array.length > MIN_CAPACITY) {
			
			// Don't allow the capacity to drop below the minimum.
			int newCapacity = Math.max(array.length / 2, MIN_CAPACITY);
			array = Arrays.copyOf(array, newCapacity);
		}
		
		return removedInt;
	}
	
	/**
	 * Return the number of integers in the list.
	 * 
	 * @return the number of integers in the list
	 */
	public int size() {
		return size;
	}

	public void setSize(int a) {
		this.size = a;
	}
	
	/**
	 * Return a String representation of the list with "[" as the prefix, "]" 
	 * as the suffix, and ", " as the delimiter between integers.
	 */
	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(", ", "[", "]");
		// TODO: Complete this method.
		for (int i = 0; i < size; i++) {
			joiner.add(Integer.toString(array[i]));
		}
		return joiner.toString();
	}
}
