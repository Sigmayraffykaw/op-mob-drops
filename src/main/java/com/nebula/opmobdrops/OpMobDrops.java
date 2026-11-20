package com.nebula.opmobdrops;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import java.util.concurrent.ThreadLocalRandom;

public class OpMobDrops implements ModInitializer {
    @Override public void onInitialize() {
        ServerLivingEntityEvents.AFTER_DEATH.register((LivingEntity mob, DamageSource source) -> {
            if (!(source.getEntity() instanceof Player)) return;
            if (!(mob.level() instanceof ServerLevel level)) return;
            int roll = ThreadLocalRandom.current().nextInt(201);
            if (roll == 200) {
                // The special 2,500-stick reward is split into valid Minecraft stacks.
                drop(level, mob, Items.STICK, 2500);
                return;
            }
            int kind = roll % 40;
            int tier = roll / 40;
            Item item = rewardItem(kind);
            int amount = rewardAmount(kind, tier);
            if ((kind <= 12 || kind == 25 || kind == 26) && tier >= 1) {
                ItemStack enchanted = new ItemStack(item);
                var registry = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
                if (kind >= 3 && kind <= 6) {
                    if (kind == 6) enchanted.enchant(registry.getOrThrow(Enchantments.FEATHER_FALLING), 4);
                    if (kind == 3) enchanted.enchant(registry.getOrThrow(Enchantments.RESPIRATION), 3);
                    enchanted.enchant(registry.getOrThrow(Enchantments.PROTECTION), 4);
                    enchanted.enchant(registry.getOrThrow(Enchantments.UNBREAKING), 3);
                    enchanted.enchant(registry.getOrThrow(Enchantments.MENDING), 1);
                } else if (kind == 0 || kind == 2) {
                    enchanted.enchant(registry.getOrThrow(Enchantments.SHARPNESS), 5);
                    enchanted.enchant(registry.getOrThrow(Enchantments.LOOTING), 3);
                    enchanted.enchant(registry.getOrThrow(Enchantments.UNBREAKING), 3);
                    enchanted.enchant(registry.getOrThrow(Enchantments.MENDING), 1);
                } else if (kind == 1 || kind == 25 || kind == 26) {
                    enchanted.enchant(registry.getOrThrow(Enchantments.EFFICIENCY), 5);
                    enchanted.enchant(registry.getOrThrow(Enchantments.FORTUNE), 3);
                    enchanted.enchant(registry.getOrThrow(Enchantments.UNBREAKING), 3);
                    enchanted.enchant(registry.getOrThrow(Enchantments.MENDING), 1);
                } else if (kind == 8) {
                    enchanted.enchant(registry.getOrThrow(Enchantments.DENSITY), 5);
                    enchanted.enchant(registry.getOrThrow(Enchantments.UNBREAKING), 3);
                    enchanted.enchant(registry.getOrThrow(Enchantments.MENDING), 1);
                } else if (kind == 9) {
                    enchanted.enchant(registry.getOrThrow(Enchantments.LOYALTY), 3);
                    enchanted.enchant(registry.getOrThrow(Enchantments.UNBREAKING), 3);
                    enchanted.enchant(registry.getOrThrow(Enchantments.MENDING), 1);
                } else if (kind == 10) {
                    enchanted.enchant(registry.getOrThrow(Enchantments.POWER), 5);
                    enchanted.enchant(registry.getOrThrow(Enchantments.UNBREAKING), 3);
                    enchanted.enchant(registry.getOrThrow(Enchantments.MENDING), 1);
                } else if (kind == 11) {
                    enchanted.enchant(registry.getOrThrow(Enchantments.QUICK_CHARGE), 3);
                    enchanted.enchant(registry.getOrThrow(Enchantments.UNBREAKING), 3);
                    enchanted.enchant(registry.getOrThrow(Enchantments.MENDING), 1);
                } else {
                    enchanted.enchant(registry.getOrThrow(Enchantments.UNBREAKING), 3);
                    enchanted.enchant(registry.getOrThrow(Enchantments.MENDING), 1);
                }
                dropStack(level, mob, enchanted);
            } else {
                drop(level, mob, item, amount);
            }
        });
    }

    private static Item rewardItem(int kind) {
        return switch (kind) {
            case 0 -> Items.NETHERITE_SWORD;
            case 1 -> Items.NETHERITE_PICKAXE;
            case 2 -> Items.NETHERITE_AXE;
            case 3 -> Items.NETHERITE_HELMET;
            case 4 -> Items.NETHERITE_CHESTPLATE;
            case 5 -> Items.NETHERITE_LEGGINGS;
            case 6 -> Items.NETHERITE_BOOTS;
            case 7 -> Items.ELYTRA;
            case 8 -> Items.MACE;
            case 9 -> Items.TRIDENT;
            case 10 -> Items.BOW;
            case 11 -> Items.CROSSBOW;
            case 12 -> Items.SHIELD;
            case 13 -> Items.TOTEM_OF_UNDYING;
            case 14 -> Items.ENCHANTED_GOLDEN_APPLE;
            case 15 -> Items.GOLDEN_APPLE;
            case 16 -> Items.NETHERITE_INGOT;
            case 17 -> Items.NETHERITE_BLOCK;
            case 18 -> Items.NETHERITE_SCRAP;
            case 19 -> Items.BEACON;
            case 20 -> Items.NETHER_STAR;
            case 21 -> Items.HEAVY_CORE;
            case 22 -> Items.ANCIENT_DEBRIS;
            case 23 -> Items.SHULKER_BOX;
            case 24 -> Items.END_CRYSTAL;
            case 25 -> Items.NETHERITE_SHOVEL;
            case 26 -> Items.NETHERITE_HOE;
            case 27 -> Items.SPECTRAL_ARROW;
            case 28 -> Items.GOLDEN_CARROT;
            case 29 -> Items.ENDER_PEARL;
            case 30 -> Items.ENDER_EYE;
            case 31 -> Items.OBSIDIAN;
            case 32 -> Items.CRYING_OBSIDIAN;
            case 33 -> Items.RESPAWN_ANCHOR;
            case 34 -> Items.ENCHANTING_TABLE;
            case 35 -> Items.ANVIL;
            case 36 -> Items.BREEZE_ROD;
            case 37 -> Items.DRAGON_BREATH;
            case 38 -> Items.EXPERIENCE_BOTTLE;
            case 39 -> Items.GOLD_BLOCK;
            default -> Items.NETHERITE_INGOT;
        };
    }

    private static int rewardAmount(int kind, int tier) {
        if (kind <= 12 || kind == 25 || kind == 26) return 1;
        return Math.min(64, (tier + 1) * (kind >= 27 ? 8 : 2));
    }

    private static void drop(ServerLevel level, LivingEntity mob, Item item, int amount) {
        while (amount > 0) {
            int count = Math.min(amount, item.getDefaultMaxStackSize());
            dropStack(level, mob, new ItemStack(item, count));
            amount -= count;
        }
    }

    private static void dropStack(ServerLevel level, LivingEntity mob, ItemStack stack) {
        ItemEntity entity = new ItemEntity(level, mob.getX(), mob.getY() + 0.5, mob.getZ(), stack);
        entity.setDefaultPickUpDelay();
        level.addFreshEntity(entity);
    }
}
