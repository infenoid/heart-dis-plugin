package org.local.test;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public final class Hearts extends JavaPlugin implements Listener {

    private static Hearts instance;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("Plugin has been enabled!");

        // Register the event listener
        getServer().getPluginManager().registerEvents(this, this);
    }

    public static Hearts getInstance() {
        return instance;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Player player = event.getPlayer();

        // Create a health objective to display hearts below player name
        Objective objective = board.registerNewObjective(
                Hearts.getInstance().getName() + "_health",
                Criteria.HEALTH,
                ChatColor.RED + "❤"
        );
        objective.setDisplaySlot(DisplaySlot.BELOW_NAME);

        // Assign the scoreboard to the player
        player.setScoreboard(board);
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin has been disabled!");
    }
}
