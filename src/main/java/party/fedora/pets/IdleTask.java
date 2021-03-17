package party.fedora.pets;

import org.bukkit.Bukkit;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class IdleTask implements Runnable {

    private Pets main;

    public IdleTask(Pets main){
        this.main = main;
        run();
    }

    public void run() { // Main method in task
        for (Player player : Bukkit.getOnlinePlayers()) { // Loop all online players on the server
            if (main.playersIdle.containsKey(player)) { // If player is already in the hashmap
                main.playersIdle.put(player, main.playersIdle.get(player) + 1); // Add 1 to the player's value
            } else { // If not
                main.playersIdle.put(player, 1); // Put new player to the hashmap with value 1 (1 second)
            }
            if (main.playersIdle.get(player) < 20) { // If player's value is 120 (120 seconds)
                if (main.playersPets.containsKey(player)) {
                    if (player.getFacing() == BlockFace.NORTH) {
                        main.playersPets.get(player).teleport(player.getLocation().add(.75, .5, .75));
                    } else if (player.getFacing() == BlockFace.SOUTH) {
                        main.playersPets.get(player).teleport(player.getLocation().add(-.75, .5, -.75));
                    }else if (player.getFacing() == BlockFace.WEST) {
                        main.playersPets.get(player).teleport(player.getLocation().add(.75, .5, -.75));
                    }else if (player.getFacing() == BlockFace.EAST) {
                        main.playersPets.get(player).teleport(player.getLocation().add(-.75, .5, .75));
                    }
                }
            }
            if (main.playersIdle.get(player) > 19) {
                if (main.playersPets.containsKey(player)) {
                    if (!player.isSneaking())
                    main.playersPets.get(player).teleport(player.getLocation().add(0, 1.2, 0));
                    else{
                        main.playersPets.get(player).teleport(player.getLocation().add(0, .8, 0));
                    }
                }
            }
        }
    }
}