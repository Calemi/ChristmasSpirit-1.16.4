package com.tm.cspirit.event;

import com.tm.cspirit.entity.EntityJackFrost;
import com.tm.cspirit.util.helper.ChatHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;

public class JackFrostEvent {

    private static final int SPAWN_RANGE = 500;

    private boolean canSpawn = true;

    @SubscribeEvent
    public void onTick(TickEvent.WorldTickEvent event) {

        World world = event.world;

        if (world.getPlayers().size() > 0) {

            PlayerEntity player = world.getPlayers().get(0);

            if (world.getDayTime() == 18000) {

                if (world.isRaining()) {

                    if (canSpawn) {

                        Random random = new Random();

                        if (random.nextInt(5) == 0) {

                            canSpawn = false;

                            int randX = world.getWorldInfo().getSpawnX() + (SPAWN_RANGE - random.nextInt(SPAWN_RANGE * 2));
                            int randZ = world.getWorldInfo().getSpawnZ() + (SPAWN_RANGE - random.nextInt(SPAWN_RANGE * 2));

                            EntityJackFrost entity = new EntityJackFrost(world, (int)player.getPosX(), 256, (int)player.getPosZ());
                            world.addEntity(entity);
                            entity.setLocationAndAngles(randX, 256, randZ, 0, 0);

                            if (!world.isRemote) ChatHelper.broadcastMessage(world, TextFormatting.AQUA + "" + TextFormatting.BOLD + "Jack Frost has appeared at [" + randX + ", " + randZ + "]!");
                            world.playBroadcastSound(1023, entity.getPosition(), 0);
                        }
                    }
                }
            }

            else canSpawn = true;
        }
    }
}
