package party.fedora.pets;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {

    private Pets main;

    public PlayerMove(Pets main){
        this.main = main;
    }

    @EventHandler
    public void onMove (PlayerMoveEvent e) {
        Player player = e.getPlayer();

        if (main.playersLoc.containsKey(player)) {
            int oldX = main.playersLoc.get(player).getBlockX();
            int oldY = main.playersLoc.get(player).getBlockY();
            int oldZ = main.playersLoc.get(player).getBlockZ();

            int x = e.getPlayer().getLocation().getBlockX();
            int y = e.getPlayer().getLocation().getBlockY();
            int z = e.getPlayer().getLocation().getBlockZ();

            if (x != oldX) {
                oldX = x;
                if (main.playersIdle.containsKey(e.getPlayer())) {
                    main.playersIdle.remove(e.getPlayer());
                }
            }
            if (z != oldZ) {
                oldZ = z;
                if (main.playersIdle.containsKey(e.getPlayer())) {
                    main.playersIdle.remove(e.getPlayer());
                }
            }
            if (y != oldY) {
                oldY = y;
                if (main.playersIdle.containsKey(e.getPlayer())) {
                    main.playersIdle.remove(e.getPlayer());
                }
            }
            main.playersLoc.put(player, player.getLocation());
        }else{
            main.playersLoc.put(player, player.getLocation());
        }
    }
}
