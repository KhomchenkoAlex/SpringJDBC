package model.autoparts;

public class SummerTyres extends Tyres {

    private int size;
    private String name;
    private String type = "summer";

    public SummerTyres(int size, String name) {
        this.size = size;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        String result = "type:-" + type + " & " +
                "name:-" + name + " & " +
                "size:-" + size;
        return result;
    }
}
