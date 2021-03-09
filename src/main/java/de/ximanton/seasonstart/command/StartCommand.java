package de.ximanton.seasonstart.command;

import de.ximanton.seasonstart.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp() && !Main.getInstance().getCustomConf().isSeasonStarted()) {
            Main.getInstance().startSeason();
            Bukkit.broadcastMessage(ChatColor.GOLD + "Die " + ChatColor.GREEN + "Season " + ChatColor.GOLD + "beginnt!");
        }

        return false;
    }
}
