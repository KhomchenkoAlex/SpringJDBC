package model.autoparts;

public class Engine {

    private String engineType;
    private int engineCapacity;

    public Engine(int engineCapacity, String engineType) {
        this.engineCapacity = engineCapacity;
        this.engineType = engineType;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getEngineType() {
        return engineType;
    }

    @Override
    public String toString() {
        String result = "engineType:-" + engineType + " & " +
                "engineCapacity:-" + engineCapacity;
        return result;
    }
}
