package model.autoparts;

public class Wheel {
    private Tyres tyres;

    public Wheel(Tyres tyres) {
        this.tyres = tyres;
    }

    public void setTyres(Tyres tyres){
        this.tyres = tyres;
    }

    public Tyres getTyres() {
        return tyres;
    }

    public String toString(){
        String result = "Wheel with " + "tyres: - " + tyres.toString();
        return result;
    }

}
