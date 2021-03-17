package party.fedora.pets;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.UUID;

public final class Pets extends JavaPlugin {

    public HashMap<Player, Integer> playersIdle = new HashMap<>(); // Player is a key, Integer is a value
    public HashMap<Player, Location> playersLoc = new HashMap<>();
    public HashMap<Player, ArmorStand> playersPets = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getServer().getScheduler().runTaskTimer(this, new IdleTask(this), 0L, 0L); // Code in task should execute every 20 ticks (1 second)
        Bukkit.getPluginManager().registerEvents(new PlayerMove(this),this);
        Bukkit.getPluginCommand("call").setExecutor(new CallPet(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ItemStack getCustomSkull(String url) {

        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        if (url.isEmpty()) return head;

        SkullMeta skullMeta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);

        profile.getProperties().put("textures", new Property("textures", url));

        try {
            Method mtd = skullMeta.getClass().getDeclaredMethod("setProfile", GameProfile.class);
            mtd.setAccessible(true);
            mtd.invoke(skullMeta, profile);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            ex.printStackTrace();
        }

        head.setItemMeta(skullMeta);
        return head;
    }

}
