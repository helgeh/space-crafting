package net.jelgue.mc.mixin;

import net.jelgue.mc.client.Recraftinator;
import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import net.minecraft.recipe.Recipe;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(RecipeBookWidget.class)
public class RecipeBookWidgetMixin {

//    @Inject(method = "drawGhostSlots", at = @At("HEAD"), cancellable = true)
//    public void drawGhostSlots(MatrixStack matrices, int x, int y, boolean notInventory, float delta, CallbackInfo ci) {
//        // System.out.println(" Hmm... showing *OTHER* ghost recipe now? Or...?");
//    }

    //@Shadow @Final protected RecipeBookGhostSlots ghostSlots;

    @Inject(method = "slotClicked", at = @At("HEAD"))
    public void slotClicked(@Nullable Slot slot, CallbackInfo ci) {
        Recraftinator.setGhosting(false);
    }

    @Inject(method = "showGhostRecipe", at = @At("HEAD"))
    public void showGhostRecipe(Recipe<?> recipe, List<Slot> slots, CallbackInfo ci) {
        Recraftinator.setGhosting(true);
//        var r = ghostSlots.getRecipe();
//        if (r != null && r.getId() == recipe.getId()) {
//            System.out.println("recipe is the same, CANCELLING!");
//            ci.cancel();
//        }
//        else if (r == null) {
//            System.out.println("recipe from ghostslots is null..");
//        }
    }

}
