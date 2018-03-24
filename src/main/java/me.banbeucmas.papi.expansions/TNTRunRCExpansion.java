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

        if (!canRegister()) {
            return false;
        }

        /*
         * "SomePlugin" does not have static methods to access its api so we must
         * create set a variable to obtain access to it
         */
        plugin = (TNTRun) Bukkit.getPluginManager().getPlugin(getPlugin());

        /*
         * if for some reason we can not get our variable, we should return false
         */
        if (plugin == null) {
            return false;
        }
        /*
         * Since we override the register method, we need to manually
         * register this hook
         */
        return PlaceholderAPI.registerPlaceholderHook(getIdentifier(), this);
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
