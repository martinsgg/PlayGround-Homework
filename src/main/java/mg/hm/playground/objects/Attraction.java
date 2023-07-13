package mg.hm.playground.objects;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import java.util.ArrayList;

@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "attractionName")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DoubleSwings.class, name = "DoubleSwing"),
        @JsonSubTypes.Type(value = BallPit.class, name = "BallPit"),
        @JsonSubTypes.Type(value = Slide.class, name = "Slide"),
        @JsonSubTypes.Type(value = Carousel.class, name = "Carousel")
})
public abstract class Attraction {

    public Attraction(int slots) {
        this.slots = slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

    private int slots = 0;
    private long id;

    public ArrayList<Guest> getVisitors() {
        return visitors;
    }

    public void addVisitor(Guest visitor) throws Exception {
        if(this.getSlots() > this.getTakenSlots()){
            this.visitors.add(visitor);
        } else {
            throw new Exception("PlayGround is full!");
        }
    }

    public boolean removeGuest(Guest visitor){
        return this.visitors.remove(visitor);
    }

    ArrayList<Guest> visitors = new ArrayList<Guest>();

    public Attraction(){}

    public Attraction(long id, int slots){
        this.id = id;
        this.slots = slots;
    }

    public int getSlots(){
        return this.slots;
    }

    public int getTakenSlots(){
        return visitors.size();
    }
    public int getAvailableSlots(){
        return (slots-this.getTakenSlots());
    }

    public Double getUtilization(){
        return ((double)this.getTakenSlots()/(double)this.slots)*100;
    }

    public String getAttractionName(){
        return this.getClass().getSimpleName();
    }


}
