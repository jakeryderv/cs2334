public class RangeList extends IntegerList {

    // Constructors
    public RangeList() {
        super();
    }

    public RangeList(int lowerBound, int upperBound) {
        super();
        if (upperBound < lowerBound) {
            throw new IllegalArgumentException("The upper bound must be greater than or equal to the lower bound.");
        }
        for (int i = lowerBound; i <= upperBound; i++) {
            super.insert(super.size(), i);
        }
    }

    // Methods

    @Override
    public void add(int integer) {
        throw new UnsupportedOperationException("Add operation is not supported. Use RangeList(int lowerBound, int upperBound) constructor instead.");
    }

    @Override
    public void insert(int index, int integer) {
        throw new UnsupportedOperationException("Insert operation is not supported. Use RangeList(int lowerBound, int upperBound) constructor instead.");
    }

    public void remove(int lowerBound, int upperBound) {
        if (upperBound < lowerBound) {
            throw new IllegalArgumentException("The upper bound must be greater than or equal to the lower bound.");
        }

        if (super.size() == 0) {
            throw new UnsupportedOperationException("Cannot remove range from an empty list.");
        }

        int firstElement = super.get(0);
        int lastElement = super.get(super.size() - 1);

        if (lowerBound < firstElement || upperBound > lastElement) {
            throw new IllegalArgumentException("Lower and/or upper bounds are out of the current list range.");
        }

        for (int i = lowerBound; i <= upperBound; i++) {
            int index = super.indexOf(i);
            if (index != -1) {
                super.remove(index);
            }
        }
    }
    public void add(int lowerBound, int upperBound) {
        if (upperBound < lowerBound) {
            throw new IllegalArgumentException("The upper bound must be greater than or equal to the lower bound.");
        }
        for (int i = lowerBound; i <= upperBound; i++) {
            if (indexOf(i) == -1) {
                int insertIndex = 0;
                while (insertIndex < size() && super.get(insertIndex) < i) {
                    insertIndex++;
                }
                super.insert(insertIndex, i);
            }
        }
    }
}
