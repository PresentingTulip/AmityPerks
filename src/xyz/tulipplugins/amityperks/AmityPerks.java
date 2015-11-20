package xyz.tulipplugins.amityperks;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

public class AmityPerks extends JavaPlugin implements Listener {

	public void onEnable() {

		getServer().getPluginManager().registerEvents(this, this);

	}

	public void onDisable() {

	}

	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {

		Player player = (Player) sender;
		if (command.getName().equalsIgnoreCase("superjump")) {
			if (player.hasPermission("amityperks.superjump")) {
				if (player.hasPotionEffect(PotionEffectType.JUMP)) {
					player.removePotionEffect(PotionEffectType.JUMP);
					player.sendMessage(ChatColor.GREEN + "Super jump disabled");
				} else {
					player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200000000, 4));
					player.sendMessage(ChatColor.GREEN + "Super jump enabled");
				}
			}
		}
		if (command.getName().equalsIgnoreCase("ninja")) {
			if (player.hasPermission("amityperks.ninja")) {
				if (player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {

					player.removePotionEffect(PotionEffectType.INVISIBILITY);
					player.sendMessage(ChatColor.GREEN + "Ninja mode disabled");
				} else {
					player.sendMessage(ChatColor.GREEN + "Ninja mode enabled");
					player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 300 * 20, 1));

				}

			}

		}
		if (command.getName().equalsIgnoreCase("nofall")) {

			if (player.hasPermission("amityperks.nofall")) {
				player.sendMessage(ChatColor.GREEN + "No fall is enabled. Forever :D");

			}

		}
		if (command.getName().equalsIgnoreCase("wild")) {

			Random rand = new Random();
			double xcoord = rand.nextInt(4000) + -4000;
			double zcoord = rand.nextInt(4000) + -4000;
			final double ycoord = 70;
			Location loc = new Location(player.getWorld(), xcoord, ycoord, zcoord);
			player.teleport(loc);
			player.sendMessage(ChatColor.GREEN + "To the wild!");
		}
		return false;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerFall(EntityDamageEvent event) {
		Player player = (Player) event.getEntity();
		if (event.getEntity() instanceof Player) {
			if (player.hasPermission("amityperks.nofall")) {
				if (event.getCause() == DamageCause.FALL) {
					event.setCancelled(true);
				} else {
					return;
				}

			}

		}

	}

}
