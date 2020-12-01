package me.petterim1.dyeshulkers;

import cn.nukkit.block.BlockID;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.ItemDye;
import cn.nukkit.plugin.PluginBase;

public class Plugin extends PluginBase implements Listener {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler(ignoreCancelled = true)
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK || e.getAction() == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
            if (e.getItem().getId() == 351) {
                if (e.getBlock().getId() == BlockID.SHULKER_BOX) {
                    if (e.getPlayer().isSneaking()) {
                        e.getBlock().level.setBlockDataAt((int) e.getBlock().x, (int) e.getBlock().y, (int) e.getBlock().z, ((ItemDye) e.getItem()).getDyeColor().getWoolData());
                        PlayerInventory inv = e.getPlayer().getInventory();
                        inv.decreaseCount(inv.getHeldItemIndex());
                    }
                } else if (e.getBlock().getId() == BlockID.UNDYED_SHULKER_BOX) {
                    e.getBlock().level.setBlockIdAt((int) e.getBlock().x, (int) e.getBlock().y, (int) e.getBlock().z, BlockID.SHULKER_BOX);
                    e.getBlock().level.setBlockDataAt((int) e.getBlock().x, (int) e.getBlock().y, (int) e.getBlock().z, ((ItemDye) e.getItem()).getDyeColor().getWoolData());
                    PlayerInventory inv = e.getPlayer().getInventory();
                    inv.decreaseCount(inv.getHeldItemIndex());
                }
            }
        }
    }
}
