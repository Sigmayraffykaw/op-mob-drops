package com.nebula.opmobdrops;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerLevel;
import java.util.concurrent.ThreadLocalRandom;

public class OpMobDrops implements ModInitializer {
    @Override public void onInitialize() {
        ServerLivingEntityEvents.AFTER_DEATH.register((LivingEntity mob, DamageSource source) -> {
            if (!(source.getEntity() instanceof Player)) return;
            if (!(mob.level() instanceof ServerLevel level)) return;
            ItemStack reward = randomReward();
            ItemEntity item = new ItemEntity(level, mob.getX(), mob.getY() + 0.5, mob.getZ(), reward);
            item.setDefaultPickUpDelay();
            level.addFreshEntity(item);
        });
    }
    private static ItemStack randomReward() {
        return switch (ThreadLocalRandom.current().nextInt(30)) {
            case 0 -> new ItemStack(Items.NETHERITE_SWORD);
            case 1 -> new ItemStack(Items.NETHERITE_PICKAXE);
            case 2 -> new ItemStack(Items.NETHERITE_CHESTPLATE);
            case 3 -> new ItemStack(Items.NETHERITE_HELMET);
            case 4 -> new ItemStack(Items.NETHERITE_LEGGINGS);
            case 5 -> new ItemStack(Items.NETHERITE_BOOTS);
            case 6 -> new ItemStack(Items.ELYTRA);
            case 7 -> new ItemStack(Items.ENCHANTED_GOLDEN_APPLE, 4);
            case 8 -> new ItemStack(Items.DIAMOND, 32);
            case 9 -> new ItemStack(Items.EMERALD, 64);
            case 10 -> new ItemStack(Items.NETHERITE_INGOT, 4);
            case 11 -> new ItemStack(Items.TOTEM_OF_UNDYING, 3);
            case 12 -> new ItemStack(Items.TRIDENT);
            case 13 -> new ItemStack(Items.MACE);
            case 14 -> new ItemStack(Items.NETHERITE_AXE);
            case 15 -> new ItemStack(Items.NETHERITE_SHOVEL);
            case 16 -> new ItemStack(Items.NETHERITE_HOE);
            case 17 -> new ItemStack(Items.NETHERITE_BLOCK, 2);
            case 18 -> new ItemStack(Items.DIAMOND_BLOCK, 8);
            case 19 -> new ItemStack(Items.EMERALD_BLOCK, 8);
            case 20 -> new ItemStack(Items.NETHER_STAR);
            case 21 -> new ItemStack(Items.BEACON);
            case 22 -> new ItemStack(Items.SHULKER_BOX, 2);
            case 23 -> new ItemStack(Items.END_CRYSTAL, 8);
            case 24 -> new ItemStack(Items.GOLDEN_APPLE, 16);
            case 25 -> new ItemStack(Items.EXPERIENCE_BOTTLE, 64);
            case 26 -> new ItemStack(Items.HEAVY_CORE);
            case 27 -> new ItemStack(Items.BREEZE_ROD, 16);
            case 28 -> new ItemStack(Items.DRAGON_BREATH, 16);
            default -> new ItemStack(Items.ANCIENT_DEBRIS, 16);
        };
    }
}
