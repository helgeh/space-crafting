package net.jelgue.mc.mixin;

import net.jelgue.mc.client.Recraftinator;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.recipe.RecipeEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {

	@Inject(method = "clickRecipe", at = @At("RETURN"))
	public void clickRecipe(int syncId, RecipeEntry<?> recipe, boolean craftAll, CallbackInfo info) {
		// System.out.println("Recipe clicked!");
        Recraftinator.getInstance().onRecipeClicked(recipe);
	}

}
