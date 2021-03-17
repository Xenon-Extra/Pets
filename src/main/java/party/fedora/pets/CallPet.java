package party.fedora.pets;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Skull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import static org.bukkit.entity.ArmorStand.LockType.REMOVING_OR_CHANGING;

public class CallPet implements CommandExecutor {

    Pets main;
    public CallPet(Pets main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)){
            return true;
        }

        Player player = (Player) sender;

        if (!command.getName().equalsIgnoreCase("call")){
            return true;
        }

        if (args.length == 0){

            if (!main.playersPets.containsKey(player)) {
                Location pLoc = player.getLocation().add(0, 1.2, 0);

                ArmorStand as = (ArmorStand) pLoc.getWorld().spawn(pLoc, ArmorStand.class);
                //Customise Part
                // --------
                as.setCustomName(ChatColor.AQUA + "Moo Moo");
                as.setItem(EquipmentSlot.HEAD, main.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2EyYzRjOWRhMzdiNDg2ZDQyM2ExODAxZWMzNTY5YjMzZjBiMmE4ZTU3ZDAxYjI0NjQyYTg0Yzg2NGNkOTFhMSJ9fX0="));
                as.setItem(EquipmentSlot.HAND, new ItemStack(Material.DIAMOND_BLOCK));
                as.setItem(EquipmentSlot.OFF_HAND, new ItemStack(Material.DIAMOND_BLOCK));
                as.setItem(EquipmentSlot.FEET, new ItemStack(Material.DIAMOND_BOOTS));
                //--------

                as.setGravity(false);
                as.setCanPickupItems(false);
                as.setCustomNameVisible(true);
                as.setRotation(player.getLocation().getYaw(), player.getLocation().getPitch());
                as.setSmall(true);
                as.setArms(true);
                as.addEquipmentLock(EquipmentSlot.FEET,REMOVING_OR_CHANGING);
                as.addEquipmentLock(EquipmentSlot.LEGS,REMOVING_OR_CHANGING);
                as.addEquipmentLock(EquipmentSlot.CHEST,REMOVING_OR_CHANGING);
                as.addEquipmentLock(EquipmentSlot.HEAD,REMOVING_OR_CHANGING);
                as.addEquipmentLock(EquipmentSlot.HAND,REMOVING_OR_CHANGING);
                as.addEquipmentLock(EquipmentSlot.OFF_HAND,REMOVING_OR_CHANGING);
                as.setVisible(false);
                as.setLeftArmPose(new EulerAngle(0f,0f,89.5f));
                as.setRightArmPose(new EulerAngle(0f,0f,281.15f));
                as.setLeftLegPose(new EulerAngle(90.5f,0f,.5f));
                as.setRightLegPose(new EulerAngle(90.5f,0f,-.5f));
                main.playersPets.put(player, as);

                player.sendMessage(ChatColor.GREEN + "You called your pet.");
            }else{
                player.sendMessage(ChatColor.RED + "You already have a pet.");
            }
        }else if(args.length == 1){
            if (args[0].equalsIgnoreCase("remove")){
                if (!main.playersPets.containsKey(player)) {
                    player.sendMessage(ChatColor.RED + "You don't have a pet.");
                }else{
                    main.playersPets.get(player).remove();
                    main.playersPets.remove(player);
                    player.sendMessage(ChatColor.GREEN + "You sent your pet to sleep.");
                }
            }
        }
        return false;
    }
}
