package org.bukkit.craftbukkit.enchantments;

import java.util.HashMap;

import net.minecraft.server.EnchantmentSlotType;

import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.inventory.ItemStack;

public class CraftEnchantment extends Enchantment {
    private final net.minecraft.server.Enchantment target;
    private final HashMap<Integer, String> enchantName = new HashMap<Integer, String>();
    private final HashMap<EnchantmentSlotType, EnchantmentTarget> enchantType = new HashMap<EnchantmentSlotType, EnchantmentTarget>();

    public CraftEnchantment(net.minecraft.server.Enchantment target) {
        super(target.id);
        this.target = target;
        generateEnchantName();
        generateEnchantType();
    }
    
    private void generateEnchantName() {
    	enchantName.put(0, "PROTECTION_ENVIRONMENTAL");
    	enchantName.put(1, "PROTECTION_FIRE");
    	enchantName.put(2, "PROTECTION_FALL");
    	enchantName.put(3, "PROTECTION_EXPLOSIONS");
    	enchantName.put(4, "PROTECTION_PROJECTILE");
    	enchantName.put(5, "OXYGEN");
    	enchantName.put(6, "WATER_WORKER");
    	enchantName.put(7, "THORNS");
    	enchantName.put(16, "DAMAGE_ALL");
    	enchantName.put(17, "DAMAGE_UNDEAD");
    	enchantName.put(18, "DAMAGE_ARTHROPODS");
    	enchantName.put(19, "KNOCKBACK");
    	enchantName.put(20, "FIRE_ASPECT");
    	enchantName.put(21, "LOOT_BONUS_MOBS");
    	enchantName.put(32, "DIG_SPEED");
    	enchantName.put(33, "SILK_TOUCH");
    	enchantName.put(34, "DURABILITY");
    	enchantName.put(35, "LOOT_BONUS_BLOCKS");
    	enchantName.put(48, "ARROW_DAMAGE");
    	enchantName.put(49, "ARROW_KNOCKBACK");
    	enchantName.put(50, "ARROW_FIRE");
    	enchantName.put(51, "ARROW_INFINITE");
    	enchantName.put(61, "LUCK");
    	enchantName.put(62, "LURE");

    }
    
    private void generateEnchantType() {
    	enchantType.put(EnchantmentSlotType.ALL, EnchantmentTarget.ALL);
    	enchantType.put(EnchantmentSlotType.ARMOR, EnchantmentTarget.ARMOR);
    	enchantType.put(EnchantmentSlotType.ARMOR_FEET, EnchantmentTarget.ARMOR_FEET);
    	enchantType.put(EnchantmentSlotType.ARMOR_HEAD, EnchantmentTarget.ARMOR_HEAD);
    	enchantType.put(EnchantmentSlotType.ARMOR_LEGS, EnchantmentTarget.ARMOR_LEGS);
    	enchantType.put(EnchantmentSlotType.ARMOR_TORSO, EnchantmentTarget.ARMOR_TORSO);
    	enchantType.put(EnchantmentSlotType.DIGGER, EnchantmentTarget.TOOL);
    	enchantType.put(EnchantmentSlotType.WEAPON, EnchantmentTarget.WEAPON);
    	enchantType.put(EnchantmentSlotType.BOW, EnchantmentTarget.BOW);
    	enchantType.put(EnchantmentSlotType.FISHING_ROD, EnchantmentTarget.FISHING_ROD);
    }

    @Override
    public int getMaxLevel() {
        return target.getMaxLevel();
    }

    @Override
    public int getStartLevel() {
        return target.getStartLevel();
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return enchantType.get(target.slot);
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return target.canEnchant(CraftItemStack.asNMSCopy(item));
    }

    @Override
    public String getName() {
        String name = enchantName.get(target.id);
        
        if(name == null) {
        	name = "UNKNOWN_ENCHANT_" + target.id;
        }
        
        return name;
    }

    public static net.minecraft.server.Enchantment getRaw(Enchantment enchantment) {
        if (enchantment instanceof EnchantmentWrapper) {
            enchantment = ((EnchantmentWrapper) enchantment).getEnchantment();
        }

        if (enchantment instanceof CraftEnchantment) {
            return ((CraftEnchantment) enchantment).target;
        }

        return null;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        if (other instanceof EnchantmentWrapper) {
            other = ((EnchantmentWrapper) other).getEnchantment();
        }
        if (!(other instanceof CraftEnchantment)) {
            return false;
        }
        CraftEnchantment ench = (CraftEnchantment) other;
        return !target.a(ench.target);
    }
}
