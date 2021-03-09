package de.ximanton.seasonstart;

import de.ximanton.seasonstart.command.StartCommand;
import de.ximanton.seasonstart.listener.PlayerListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Main extends JavaPlugin {

    private static Main instance;

    public Config getCustomConf() {
        return config;
    }

    public static Main getInstance() {
        return instance;
    }

    public Main() {
        instance = this;
    }

    private Config config;

    @Override
    public void onEnable() {
        if (!new File(getDataFolder(), "config.yml").exists()) {
            saveDefaultConfig();
        }
        config = new Config();
        config.reload(getConfig());

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerListener(), this);
        getCommand("start").setExecutor(new StartCommand());

    }

    public void startSeason() {
        this.config.setSeasonStarted(true);
        getConfig().set("seasonStarted", true);
        saveConfig();
        config.reload(getConfig());
    }
}
