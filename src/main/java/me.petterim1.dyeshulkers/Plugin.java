package me.petterim1.dyeshulkers;

import cn.nukkit.block.BlockID;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.ItemDye;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.DyeColor;

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
                        int color;
                        switch (e.getItem().getDamage()) {
                            case 19:
                                color = DyeColor.WHITE.getWoolData();
                                break;
                            case 18:
                                color = DyeColor.BLUE.getWoolData();
                                break;
                            case 17:
                                color = DyeColor.BROWN.getWoolData();
                                break;
                            case 16:
                                color = DyeColor.BLACK.getWoolData();
                                break;
                            default:
                                color = ((ItemDye) e.getItem()).getDyeColor().getWoolData();
                        }
                        e.getBlock().level.setBlockDataAt((int) e.getBlock().x, (int) e.getBlock().y, (int) e.getBlock().z, color);
                        PlayerInventory inv = e.getPlayer().getInventory();
                        inv.decreaseCount(inv.getHeldItemIndex());
                    }
                } else if (e.getBlock().getId() == BlockID.UNDYED_SHULKER_BOX) {
                    e.getBlock().level.setBlockIdAt((int) e.getBlock().x, (int) e.getBlock().y, (int) e.getBlock().z, BlockID.SHULKER_BOX);
                    int color;
                    switch (e.getItem().getDamage()) {
                        case 19:
                            color = DyeColor.WHITE.getWoolData();
                            break;
                        case 18:
                            color = DyeColor.BLUE.getWoolData();
                            break;
                        case 17:
                            color = DyeColor.BROWN.getWoolData();
                            break;
                        case 16:
                            color = DyeColor.BLACK.getWoolData();
                            break;
                        default:
                            color = ((ItemDye) e.getItem()).getDyeColor().getWoolData();
                    }
                    e.getBlock().level.setBlockDataAt((int) e.getBlock().x, (int) e.getBlock().y, (int) e.getBlock().z, color);
                    PlayerInventory inv = e.getPlayer().getInventory();
                    inv.decreaseCount(inv.getHeldItemIndex());
                }
            }
        }
    }
}
