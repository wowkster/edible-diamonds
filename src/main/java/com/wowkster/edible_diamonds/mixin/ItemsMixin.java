package com.wowkster.edible_diamonds.mixin;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(Items.class)
public abstract class ItemsMixin {
    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Items;register(Ljava/lang/String;Lnet/minecraft/item/Item;)Lnet/minecraft/item/Item;"))
    private static void injected(Args args) {
        if (!args.get(0).equals("diamond")) return;

        Item diamond = args.get(1);

        diamond.foodComponent = ((new FoodComponent.Builder()).hunger(8).saturationModifier(0.8F).alwaysEdible().build());
    }

    @Shadow
    private static Item register(String id, Item item) {
        System.out.println("Fake method invoked");
        return null;
    }
}
