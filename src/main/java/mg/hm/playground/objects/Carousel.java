package mg.hm.playground.objects;

public class Carousel extends Attraction {

    public Carousel( long id, int slots){
        super(id, slots);
    }

    public Carousel(long id, int slots, int taken) {
        super(id, slots);
    }

    public Carousel() {
        super();
    }
}
