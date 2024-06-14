class UniqueList extends IntegerList {
    public UniqueList() {
        super();
    }

    public UniqueList(int capacity) {
        super(capacity);
    }

    @Override
    public void add(int integer) {
        if (indexOf(integer) != -1) {
            throw new IllegalArgumentException("The integer " + integer + " is already in the list.");
        }
        super.add(integer); 
    }

    @Override
    public void insert(int index, int integer) {
        if (indexOf(integer) != -1) {
            throw new IllegalArgumentException("The integer " + integer + " is already in the list.");
        }
        super.insert(index, integer); 
    }
}
