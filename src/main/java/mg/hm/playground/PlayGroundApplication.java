package mg.hm.playground;

import mg.hm.playground.objects.Attraction;
import mg.hm.playground.objects.Guest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootApplication
@ComponentScan
public class PlayGroundApplication {

	private static HashMap<Long, PlayGround> allPlayGrounds = new HashMap<Long, PlayGround>();
	private static HashMap<Long, Guest> visitors = new HashMap<Long, Guest>();
	private static ReentrantLock changeLock = new ReentrantLock();

	public static void main(String[] args) {
		SpringApplication.run(PlayGroundApplication.class, args);
	}

	public static PlayGround addPlayGround(PlayGround pg)  {
		try {
			changeLock.lock();
			if (allPlayGrounds.keySet().size() == 0) {
				pg.setId(1);
				allPlayGrounds.put(1l, pg);
			} else {
				pg.setId(allPlayGrounds.keySet().size() + 1);
				allPlayGrounds.put(pg.getId(), pg);
			}
		} catch(Exception e) {

		} finally {
			changeLock.unlock();
		}
		return pg;
	}

	public static PlayGround getPlayGround(Long id) throws Exception{
		PlayGround pg = null;
		try {
			changeLock.lock();
			try {
				pg = allPlayGrounds.get(id);
			} catch(Exception e){
			}
		} catch(Exception e) {

		} finally {
			changeLock.unlock();
		}
		return pg;
	}

	public static void addGuestToPlayGround(Long id, Guest guest) throws Exception {
		try {
			changeLock.lock();
			PlayGround pg = allPlayGrounds.get(id);
			if(guest.getId()<1){
				guest.setId(visitors.size()+1);
				visitors.put(guest.getId(),guest);
			} else {
				if(!visitors.containsKey(guest.getId())){
					visitors.put(guest.getId(),guest);
				}
			}
			pg.addGuestToRandomAtraction(guest,true);
			allPlayGrounds.replace(id,pg);
		} catch(Exception e) {

		} finally {
			changeLock.unlock();
		}
	}

	public static Guest getGuestById(Long guestId){
		return visitors.get(guestId);
	}

	public static void removeGuestFromPlayGround(Long playGroundId, Guest guest){
		try {
			changeLock.lock();
			PlayGround pg = allPlayGrounds.get(playGroundId);
			pg.removeGuest(guest);
			allPlayGrounds.put(playGroundId,pg);
		} catch(Exception e) {

		} finally {
			changeLock.unlock();
		}
	}

	public static long getAllVisitorCount(){
		long VisitorCount = 0;
		for(PlayGround pg : allPlayGrounds.values()){
			for(Object atrraction : pg.getAttractions()){
				VisitorCount+= ((Attraction) atrraction).getTakenSlots();
			}
		}
		return VisitorCount;
	}

}
