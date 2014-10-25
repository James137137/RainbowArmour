/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.james137137.RainbowArmour;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitTask;

/**
 *
 * @author James
 */
public class myArmourColourChanger {

    private static int red, green, blue;
    public BukkitTask myTask;
    Random myRandom = new Random();
    public static List<ItemStack> rainBowArmourSet;

    public myArmourColourChanger(RainbowArmour plugin, long delay, int mode) {
        rainBowArmourSet = new ArrayList<ItemStack>();
        red = myRandom.nextInt(255);
        green = myRandom.nextInt(255);
        blue = myRandom.nextInt(255);
        RunMode1(plugin, delay);

    }

    public BukkitTask getTask() {
        return myTask;
    }

    private void RunMode1(RainbowArmour plugin, Long delay) {
        final int speed = 4;

        plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, new Runnable() {

            @Override
            public void run() {
                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    
                    /*for (ItemStack itemStack : player.getInventory().getContents()) {
                    checkitem(itemStack);
                    }*/
                    for (ItemStack itemStack : player.getInventory().getArmorContents()) {
                        checkitem(itemStack);
                    }
                }
            }

            private void checkitem(ItemStack itemStack) {
                if (!rainBowArmourSet.contains(itemStack)) {
                    if (itemStack != null) {
                        if (itemStack.getItemMeta() != null) {
                            if (itemStack.getItemMeta().getDisplayName() != null) {
                                if (itemStack.getItemMeta().getDisplayName().startsWith(RainbowArmour.RainbowNameCheck)) {
                                    rainBowArmourSet.add(itemStack);
                                }
                            }

                        }
                    }

                }
            }

        }, 0L, 10);

        myTask = plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, new Runnable() {
            Color myColor;
            boolean redUp = true;
            boolean greenUp = false;
            boolean blueUp = true;

            @Override
            public void run() {
                if (redUp) {
                    red += speed * 1;
                } else {
                    red -= speed * 1;
                }
                if (greenUp) {
                    green += speed * 2;
                } else {
                    green -= speed * 2;
                }
                if (blueUp) {
                    blue += speed * 3;
                } else {
                    blue -= speed * 3;
                }
                if (red < 0) {
                    red = 0;
                    redUp = true;
                } else if (red >= 255) {
                    red = 255;
                    redUp = false;
                }
                if (blue < 0) {
                    blue = 0;
                    blueUp = true;
                } else if (blue >= 255) {
                    blue = 255;
                    blueUp = false;
                }
                if (green < 0) {
                    green = 0;
                    greenUp = true;
                } else if (green >= 255) {
                    green = 255;
                    greenUp = false;
                }

                try {
                    myColor = Color.fromRGB(red, green, blue);
                } catch (Exception e) {
                }
                for (int i = 0; i < myArmourColourChanger.rainBowArmourSet.size(); i++) {
                    ItemStack itemStack = rainBowArmourSet.get(i);
                    if (itemStack != null) {
                        LeatherArmorMeta lam = (LeatherArmorMeta) itemStack.getItemMeta();
                        lam.setColor(myColor);
                        itemStack.setItemMeta(lam);
                        if (itemStack.getDurability() >= 1) {
                            itemStack.setDurability((short) 0);
                        }

                    } else {
                        rainBowArmourSet.remove(itemStack);
                    }

                }
            }

        }, 0L, delay);
    }

}
