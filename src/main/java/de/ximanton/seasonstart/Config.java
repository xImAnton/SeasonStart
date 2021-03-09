package de.ximanton.seasonstart;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Config {

    private boolean immovable;
    private boolean invulnerable;
    private boolean modifySpawnLocation;
    private Location spawnLocation;
    private boolean seasonStarted;
    private List<ItemStack> startItems;
    private boolean ignoreCreatives;

    public void reload(FileConfiguration config) {
        this.immovable = config.getBoolean("players.immovable");
        this.invulnerable = config.getBoolean("players.invulnerable");
        this.modifySpawnLocation = config.getBoolean("modifySpawnLocation");
        double x = config.getDouble("spawnLocation.x");
        double y = config.getDouble("spawnLocation.y");
        double z = config.getDouble("spawnLocation.z");
        World world;
        switch (config.getString("spawnLocation.dimension")) {
            case "overworld":
                world = Bukkit.getWorld("world");
                break;
            case "nether":
                world = Bukkit.getWorld("world_nether");
                break;
            case "end":
                world = Bukkit.getWorld("world_the_end");
                break;
            default:
                world = null;
        }
        this.spawnLocation = new Location(world, x, y, z);
        startItems = new ArrayList<>();
        for (String key : config.getConfigurationSection("startItems").getKeys(false)) {
            startItems.add(new ItemStack(Material.valueOf(key), config.getInt("startItems." + key)));
        }
        this.ignoreCreatives = config.getBoolean("ignoreCreativePlayers");
        this.seasonStarted = config.getBoolean("seasonStarted");
    }

    public boolean isImmovable() {
        return immovable && !seasonStarted;
    }

    public boolean isInvulnerable() {
        return invulnerable && !seasonStarted;
    }

    public boolean isModifySpawnLocation() {
        return modifySpawnLocation;
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public boolean isSeasonStarted() {
        return seasonStarted;
    }

    public List<ItemStack> getStartItems() {
        return startItems;
    }

    public void setSeasonStarted(boolean seasonStarted) {
        this.seasonStarted = seasonStarted;
    }

    public boolean isIgnoreCreatives() {
        return ignoreCreatives;
    }
}
