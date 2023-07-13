package mg.hm.playground;

import mg.hm.playground.objects.Guest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playground")
public class PlayGroundController {

    @GetMapping(value = "/{id}")
    public PlayGround getPlayGround(@PathVariable( "id" ) long id) throws Exception {
        return PlayGroundApplication.getPlayGround(id);
    }

    @GetMapping(value = "/all-visitor-count")//Current visitor count from all PlayGrounds without count in guests in queues
    public Long getAllVisitorCount() {
        return PlayGroundApplication.getAllVisitorCount();
    }

    @PostMapping
    public PlayGround addPlayGround(@RequestBody PlayGround pg) throws Exception{
        return PlayGroundApplication.addPlayGround(pg);
    }

    @PostMapping(value = "/{id}")
    public void addGuestToPlayGround(@PathVariable( "id" ) long PlayGroundId, @RequestBody Guest guest) throws Exception {
        PlayGroundApplication.addGuestToPlayGround(PlayGroundId, guest);
    }

    @DeleteMapping(value = "/{id}/guest/{guest_id}")
    public void removeGuestFromPlayGround(@PathVariable( "id" ) long PlayGroundId, @PathVariable( "guest_id" ) long guestId) throws Exception {
        PlayGroundApplication.removeGuestFromPlayGround(PlayGroundId, PlayGroundApplication.getGuestById(guestId));
    }

}
