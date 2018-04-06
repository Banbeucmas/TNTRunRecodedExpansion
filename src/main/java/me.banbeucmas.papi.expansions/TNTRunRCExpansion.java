package me.banbeucmas.papi.expansions;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.tade.tntrun.TNTRun;
import me.tade.tntrun.TNTRunAPI;
import me.tade.tntrun.utils.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TNTRunRCExpansion extends PlaceholderExpansion {

    private TNTRun plugin;

    @Override
    public boolean canRegister() {
        return Bukkit.getPluginManager().getPlugin(getPlugin()) != null;
    }

    @Override
    public boolean register() {
        plugin = (TNTRun) Bukkit.getPluginManager().getPlugin(getPlugin());

        if (plugin == null) {
            return false;
        }

        return super.register();
    }

    @Override
    public String getIdentifier() {
        return "tntrunrc";
    }

    @Override
    public String getPlugin() {
        return "TNTRun";
    }

    @Override
    public String getAuthor() {
        return "Banbeucmas";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if(player == null){
            return "";
        }
        if(identifier.equalsIgnoreCase("wins")){
            return Integer.toString(TNTRunAPI.getStats(player, MySQL.StatsType.VICTORIES));
        }

        if(identifier.equalsIgnoreCase("loses")){
            return Integer.toString(TNTRunAPI.getStats(player, MySQL.StatsType.LOSES));
        }
        if(identifier.equalsIgnoreCase("times_played")){
            return Integer.toString(TNTRunAPI.getStats(player, MySQL.StatsType.PLAYED));
        }

        if(identifier.equalsIgnoreCase("blocks_destroyed")){
            return Integer.toString(TNTRunAPI.getStats(player, MySQL.StatsType.BLOCKS_DESTROYED));
        }

        return null;
    }
}
