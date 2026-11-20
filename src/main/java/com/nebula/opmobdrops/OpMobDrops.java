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
        return switch (ThreadLocalRandom.current().nextInt(22)) {
            case 0 -> new ItemStack(Items.NETHERITE_SWORD);
            case 1 -> new ItemStack(Items.NETHERITE_PICKAXE);
            case 2 -> new ItemStack(Items.NETHERITE_CHESTPLATE);
            case 3 -> new ItemStack(Items.NETHERITE_HELMET);
            case 4 -> new ItemStack(Items.NETHERITE_LEGGINGS);
            case 5 -> new ItemStack(Items.NETHERITE_BOOTS);
            case 6 -> new ItemStack(Items.ELYTRA);
            case 7 -> new ItemStack(Items.ENCHANTED_GOLDEN_APPLE, 8);
            case 8 -> new ItemStack(Items.DIAMOND_BLOCK, 16);
            case 9 -> new ItemStack(Items.NETHERITE_INGOT, 8);
            case 10 -> new ItemStack(Items.TOTEM_OF_UNDYING, 5);
            case 11 -> new ItemStack(Items.TRIDENT);
            case 12 -> new ItemStack(Items.MACE);
            case 13 -> new ItemStack(Items.NETHERITE_AXE);
            case 14 -> new ItemStack(Items.NETHERITE_BLOCK, 4);
            case 15 -> new ItemStack(Items.NETHER_STAR, 2);
            case 16 -> new ItemStack(Items.BEACON, 2);
            case 17 -> new ItemStack(Items.SHULKER_BOX, 4);
            case 18 -> new ItemStack(Items.HEAVY_CORE, 2);
            case 19 -> new ItemStack(Items.ANCIENT_DEBRIS, 32);
            case 20 -> new ItemStack(Items.END_CRYSTAL, 16);
            default -> new ItemStack(Items.DIAMOND, 64);
        };
    }
}
