package mg.hm.playground;

import mg.hm.playground.objects.*;

import java.util.*;

public class PlayGround {

    private String name = "NoName";
    private long id = 0;
    private ArrayList<Attraction> attractions = new ArrayList<Attraction>();
    private Stack<Guest> guestQueue = new Stack<Guest>();

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void addAtraction(Attraction at){
        attractions.add(at);
    }

    public ArrayList getAttractions(){
        return attractions;
    }

    public void addGuestToRandomAtraction(Guest guest, boolean canWait) throws Exception {
        boolean guestAdded = false;
        for(int a=0;a<attractions.size();a++){
            if(attractions.get(a).getAvailableSlots() >0){
                attractions.get(a).addVisitor(guest);
                guestAdded=true;
                break;
            }
        }
        if(!guestAdded){
            if(canWait){
                guestQueue.add(guest);
                //Added to Queue
            } else {
                throw new Exception("No available spots in PlayGround!");
            }
        }
    }

    public void removeGuest(Guest guest){
        if(guestQueue.contains(guest)){ //We should remove kids even from queue
            guestQueue.remove(guest);
        }
        for(int a=0;a<attractions.size();a++){
            if(attractions.get(a).removeGuest(guest) && !guestQueue.empty()) {
                Guest nextGuest = guestQueue.firstElement();
                try {
                    addGuestToRandomAtraction(nextGuest, false);
                    guestQueue.remove(nextGuest);
                } catch(Exception e){}

            }
        }
    }

    public Double getUtilization(){
        int allSlots = 0;
        for(Attraction attraction : attractions){
            allSlots+=attraction.getSlots();
        }
        Double slotWeight = 100d / allSlots;
        Double wholePlayGroundUtilization = 0d;
        for(Attraction attraction : attractions){
            wholePlayGroundUtilization+=(attraction.getUtilization()/100)*slotWeight*attraction.getSlots();
        }
        return wholePlayGroundUtilization;
    }

}

