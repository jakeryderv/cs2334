public class Piles {
    private int[] sizes;

    public Piles(int... initSizes) {
        if (initSizes == null || initSizes.length == 0) {
            throw new IllegalArgumentException("Pile sizes must not be null and must have at least one pile.");
        }
        for (int size : initSizes) {
            if (size <= 0) {
                throw new IllegalArgumentException("All piles must have a positive number of objects.");
            }
        }
        this.sizes = initSizes.clone();
    }

    public int[] getSizes() {
        return sizes.clone();
    }

    public void removeObjects(int[] move) throws IllegalMoveException {
        if (move == null) {
            throw new IllegalMoveException("null move");
        }
        if (move.length != 2) {
            throw new IllegalMoveException("Invalid length: " + move.length);
        }
        int index = move[0];
        int number = move[1];
        if (index < 0 || index >= sizes.length) {
            throw new IllegalMoveException("Index out of bounds: " + index);
        }
        if (sizes[index] == 0) {
            throw new IllegalMoveException("Pile " + index + " is empty.");
        }
        if (number <= 0) {
            throw new IllegalMoveException("Nonpositive object number: " + number);
        }
        if (number > sizes[index]) {
            throw new IllegalMoveException("Object number greater than pile size: " + number + " > " + sizes[index]);
        }
        sizes[index] -= number;
    }

    public boolean isEmpty() {
        for (int size : sizes) {
            if (size > 0) {
                return false;
            }
        }
        return true;
    }
}
