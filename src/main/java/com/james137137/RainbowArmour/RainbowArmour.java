/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.james137137.RainbowArmour;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;
import com.james137137.org.mcstats.Metrics;

import java.io.IOException;
import net.gravitydevelopment.updater.Updater;

/**
 *
 * @author James
 */
public class RainbowArmour extends JavaPlugin {

    
    static Logger log;
    protected static final String RainbowNameCheck = "" + ChatColor.BLUE + ChatColor.RED + "" + ChatColor.GOLD
            + ChatColor.RED + "R"
            + ChatColor.GOLD + "a"
            + ChatColor.YELLOW + "i"
            + ChatColor.GREEN + "n"
            + ChatColor.AQUA + "b"
            + ChatColor.BLUE + "o"
            + ChatColor.DARK_PURPLE + "w" + ChatColor.GOLD;
    public static String HelmName = RainbowNameCheck + " Hat";
    public static String ChestName = RainbowNameCheck + " shirt";
    public static String legsName = RainbowNameCheck + " pants";
    public static String bootsName = RainbowNameCheck + " Boots";
    myArmourColourChanger playerChanger;

    @Override
    public void onEnable() {
        runMetrics();
        playerChanger = new myArmourColourChanger(this, 0, 2);
        log = Bukkit.getLogger();
        getServer().getPluginManager().registerEvents(new MyListener(this), this);
        String version = Bukkit.getServer().getPluginManager().getPlugin(this.getName()).getDescription().getVersion();
        log.log(Level.INFO, this.getName() + " : Version {0} enabled", version);
    }

    private void runMetrics() {
        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {
            // Failed to submit the stats :-(
            log.info("[" + this.getName() + "] Metrics: Failed to submit the stats");
        }
    }

    @Override
    public void onDisable() {
        this.getServer().getScheduler().cancelTasks(this);
        //reload();
        log.log(Level.INFO, "{0}: disabled", this.getName());
    }

    public void reload() {
        this.getServer().getScheduler().cancelTasks(this);
        playerChanger = new myArmourColourChanger(this, 0, 2);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        String commandName = command.getName().toLowerCase();
        if (commandName.equalsIgnoreCase("RainbowArmour")) {
            if (sender.isOp()) {
                if (args.length < 1) {
                    sender.sendMessage(ChatColor.RED + "requires /RainbowArmour playerName");
                    return true;
                }
                Player player = this.getServer().getPlayer(args[0]);
                if (player == null || !player.isOnline()) {
                    sender.sendMessage(ChatColor.RED + "Player not found");
                    return true;
                }
                if (args.length >= 2) {
                    Color myColor = Color.fromRGB(255, 255, 255);
                    LeatherArmorMeta lam;
                    ItemMeta meta;
                    int armourType = Integer.parseInt(args[1]);
                    if (armourType == 1) {
                        ItemStack MagicHelm = new ItemStack(Material.LEATHER_HELMET, 1);
                        lam = (LeatherArmorMeta) MagicHelm.getItemMeta();
                        //lam.setColor(myColor);
                        MagicHelm.setItemMeta(lam);
                        meta = MagicHelm.getItemMeta();
                        meta.setDisplayName(HelmName);
                        MagicHelm.setItemMeta(meta);
                        player.getInventory().addItem(MagicHelm);
                    } else if (armourType == 2) {
                        ItemStack MagicChestPlate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
                        lam = (LeatherArmorMeta) MagicChestPlate.getItemMeta();
                        //lam.setColor(myColor);
                        MagicChestPlate.setItemMeta(lam);
                        meta = MagicChestPlate.getItemMeta();
                        meta.setDisplayName(ChestName);
                        MagicChestPlate.setItemMeta(meta);
                        player.getInventory().addItem(MagicChestPlate);
                    } else if (armourType == 3) {
                        ItemStack MagicLeggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
                        lam = (LeatherArmorMeta) MagicLeggings.getItemMeta();
                        //lam.setColor(myColor);
                        MagicLeggings.setItemMeta(lam);
                        meta = MagicLeggings.getItemMeta();
                        meta.setDisplayName(legsName);
                        MagicLeggings.setItemMeta(meta);
                        player.getInventory().addItem(MagicLeggings);
                    } else if (armourType == 4) {
                        ItemStack MagicBoots = new ItemStack(Material.LEATHER_BOOTS, 1);
                        lam = (LeatherArmorMeta) MagicBoots.getItemMeta();
                        //lam.setColor(myColor);
                        MagicBoots.setItemMeta(lam);
                        meta = MagicBoots.getItemMeta();
                        meta.setDisplayName(bootsName);
                        MagicBoots.setItemMeta(meta);
                        player.getInventory().addItem(MagicBoots);
                    }
                    return true;
                }

                

                ItemStack MagicBoots = new ItemStack(Material.LEATHER_BOOTS, 1);
                LeatherArmorMeta lam = (LeatherArmorMeta) MagicBoots.getItemMeta();
                //lam.setColor(myColor);
                MagicBoots.setItemMeta(lam);
                ItemMeta meta = MagicBoots.getItemMeta();
                meta.setDisplayName(bootsName);
                MagicBoots.setItemMeta(meta);
                player.getInventory().addItem(MagicBoots);

                ItemStack MagicHelm = new ItemStack(Material.LEATHER_HELMET, 1);
                lam = (LeatherArmorMeta) MagicHelm.getItemMeta();
                //lam.setColor(myColor);
                MagicHelm.setItemMeta(lam);
                meta = MagicHelm.getItemMeta();
                meta.setDisplayName(HelmName);
                MagicHelm.setItemMeta(meta);
                player.getInventory().addItem(MagicHelm);

                ItemStack MagicChestPlate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
                lam = (LeatherArmorMeta) MagicChestPlate.getItemMeta();
                //lam.setColor(myColor);
                MagicChestPlate.setItemMeta(lam);
                meta = MagicChestPlate.getItemMeta();
                meta.setDisplayName(ChestName);
                MagicChestPlate.setItemMeta(meta);
                player.getInventory().addItem(MagicChestPlate);

                ItemStack MagicLeggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
                lam = (LeatherArmorMeta) MagicLeggings.getItemMeta();
               //lam.setColor(myColor);
                MagicLeggings.setItemMeta(lam);
                meta = MagicLeggings.getItemMeta();
                meta.setDisplayName(legsName);
                MagicLeggings.setItemMeta(meta);
                player.getInventory().addItem(MagicLeggings);
            }
            return true;
        }

        return false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    private void runUpdate() {
        
        Updater updater = new Updater(this, 85971, this.getFile(), Updater.UpdateType.DEFAULT, true);
        if (updater.getResult() == Updater.UpdateResult.SUCCESS) {
            this.getLogger().info("updated to " + updater.getLatestName());
            this.getLogger().info("full plugin reload is required");

        }
    }

}
