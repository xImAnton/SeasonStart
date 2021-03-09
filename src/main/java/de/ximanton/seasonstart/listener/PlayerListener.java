package de.ximanton.seasonstart.listener;

import de.ximanton.seasonstart.Main;
import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener {

    private boolean gamemode(Player p) {
        return !((p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR) && Main.getInstance().getCustomConf().isIgnoreCreatives());
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (Main.getInstance().getCustomConf().isImmovable() && gamemode(e.getPlayer())) {
            if (e.getFrom().getX() != e.getTo().getX() | e.getFrom().getZ() != e.getTo().getZ())
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (Main.getInstance().getCustomConf().isImmovable() && gamemode(e.getPlayer())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (Main.getInstance().getCustomConf().isImmovable() && gamemode(e.getPlayer())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager().getType() == EntityType.PLAYER && gamemode((Player) e.getDamager()) && Main.getInstance().getCustomConf().isInvulnerable()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntity().getType() == EntityType.PLAYER && Main.getInstance().getCustomConf().isInvulnerable()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (!e.getPlayer().hasPlayedBefore()) {
            if (Main.getInstance().getCustomConf().isModifySpawnLocation()) {
                e.getPlayer().teleport(Main.getInstance().getCustomConf().getSpawnLocation());
            }
            for (ItemStack item : Main.getInstance().getCustomConf().getStartItems()) {
                e.getPlayer().getInventory().addItem(item);
            }
        }
    }
}
