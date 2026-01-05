package net.sikorski1.neoforgemod.event;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.sikorski1.neoforgemod.TutorialMod;
import net.sikorski1.neoforgemod.item.custom.HammerItem;

@EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {

    @SubscribeEvent
    public static void onHammerUsage(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        if (mainHandItem.getItem() instanceof HammerItem hammer && player instanceof ServerPlayer serverPlayer) {
            BlockPos initialBlockPos = event.getPos();
            BlockState initialState = event.getLevel().getBlockState(initialBlockPos);

            if (!hammer.isCorrectToolForDrops(mainHandItem, initialState)) {
                return;
            }

            for (BlockPos pos : HammerItem.getBlocksToBeDestroyed(5, initialBlockPos, serverPlayer)) {
                if (pos.equals(initialBlockPos) || !hammer.isCorrectToolForDrops(mainHandItem,
                        event.getLevel().getBlockState(pos))) {
                    continue;
                }

                event.getLevel().destroyBlock(pos, !player.isCreative(), serverPlayer);
                mainHandItem.hurtAndBreak(1, serverPlayer, EquipmentSlot.MAINHAND);
            }
        }
    }
}
