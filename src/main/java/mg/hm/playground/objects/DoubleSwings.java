package mg.hm.playground.objects;

public class DoubleSwings extends Attraction {

    public DoubleSwings(long id) {
        super(id, 2);
    }

    public DoubleSwings(long id, int taken) {
        super(id, 2);
    }

    public Double getUtilization(){
        if(this.getTakenSlots() == this.getSlots()){
            return 100d;
        }
        return 0d;
    }

    public DoubleSwings() {
        super();
    }
}
