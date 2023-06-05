package net.jelgue.mc.mixin;

import net.jelgue.mc.client.Recraftinator;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.CraftingResultSlot;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CraftingResultSlot.class)
public class CraftingResultSlotMixin {

//    @Inject(method = "takeStack", at = @At("RETURN"))
//    public void takeStack(int amount, CallbackInfoReturnable ci) {
//        System.out.println(" takeStack");
//        InventoryHelper.onResultTaken();
//    }
//
//    @Inject(method = "onCrafted*", at = @At("RETURN"))
//    public void onCrafted(ItemStack stack, CallbackInfo ci) {
//        System.out.println(" onCrafted");
//        InventoryHelper.onResultTaken();
//    }

    @Inject(method = "onTakeItem", at = @At("RETURN"))
    public void onTakeItem(PlayerEntity player, ItemStack stack, CallbackInfo ci) {
        // System.out.println(" onTakeItem");
        Recraftinator.onResultTaken();
    }

//    @Inject(method = "setStack(LItemStack)", at = @At("RETURN"))
//    public void setStack(ItemStack stack, CallbackInfo ci) {
//        if (this instanceof CraftingResultSlot)
//            System.out.println("ResultSlot.setStack()");
//    }

}

