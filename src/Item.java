public class Item {
    private int id;
    private int size;
    private int value;

    public Item(int id, int size, int value) {
        this.id = id;
        this.size = size;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "[" +
                "id=" + id +
                ", size=" + size +
                ", value=" + value +
                ']';
    }
}
