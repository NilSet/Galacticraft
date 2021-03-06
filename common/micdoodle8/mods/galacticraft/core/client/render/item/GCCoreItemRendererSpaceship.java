package micdoodle8.mods.galacticraft.core.client.render.item;

import micdoodle8.mods.galacticraft.core.client.model.GCCoreModelSpaceship;
import micdoodle8.mods.galacticraft.core.entities.GCCoreEntityRocketT1;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;
import cpw.mods.fml.client.FMLClientHandler;

/**
 * Copyright 2012-2013, micdoodle8
 * 
 * All rights reserved.
 * 
 */
public class GCCoreItemRendererSpaceship implements IItemRenderer
{
    GCCoreEntityRocketT1 spaceship = new GCCoreEntityRocketT1(FMLClientHandler.instance().getClient().theWorld);
    GCCoreModelSpaceship modelSpaceship = new GCCoreModelSpaceship();
    private final ModelChest chestModel = new ModelChest();

    public static RenderItem drawItems = new RenderItem();

    private void renderPipeItem(ItemRenderType type, RenderBlocks render, ItemStack item, float translateX, float translateY, float translateZ)
    {
        final EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;

        GL11.glPushMatrix();
        long var10 = this.spaceship.entityId * 493286711L;
        var10 = var10 * var10 * 4392167121L + var10 * 98761L;
        final float var12 = (((var10 >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        final float var13 = (((var10 >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        final float var14 = (((var10 >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;

        if (type == ItemRenderType.EQUIPPED)
        {
            GL11.glRotatef(90, 1.0F, 0, 0);
            GL11.glRotatef(-20, 0.0F, 1, 0);
            GL11.glRotatef(20, 0.0F, 1, 1);
            GL11.glTranslatef(1.6F, 0.0F, 0F);
            GL11.glScalef(5.2F, 5.2F, 5.2F);

            if (player != null && player.ridingEntity != null && player.ridingEntity instanceof GCCoreEntityRocketT1)
            {
                GL11.glScalef(0.0F, 0.0F, 0.0F);
            }
        }

        GL11.glTranslatef(var12, var13 - 0.1F, var14);
        GL11.glScalef(-0.4F, -0.4F, 0.4F);
        if (type == ItemRenderType.INVENTORY || type == ItemRenderType.ENTITY)
        {
            if (type == ItemRenderType.INVENTORY)
            {
                GL11.glRotatef(80F, 1F, 0F, 1F);
                GL11.glRotatef(20F, 1F, 0F, 0F);
            }
            else
            {
                GL11.glTranslatef(0, -0.9F, 0);
                GL11.glScalef(0.5F, 0.5F, 0.5F);
            }

            GL11.glScalef(1.3F, 1.3F, 1.3F);
            GL11.glTranslatef(0, -0.6F, 0);
            GL11.glRotatef(Sys.getTime() / 90F % 360F, 0F, 1F, 0F);
        }
        FMLClientHandler.instance().getClient().renderEngine.bindTexture("/micdoodle8/mods/galacticraft/core/client/entities/spaceship1.png");
        this.modelSpaceship.render(this.spaceship, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();

        if (type == ItemRenderType.INVENTORY)
        {
            if (item.getItemDamage() == 1)
            {
                final ModelChest modelChest = this.chestModel;
                FMLClientHandler.instance().getClient().renderEngine.bindTexture("/item/chest.png");

                GL11.glPushMatrix();
                // GL11.glEnable(GL12.GL_RESCALE_NORMAL);
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glScalef(0.5F, -0.5F, -0.5F);
                GL11.glTranslatef(1.5F, 1.95F, 1.7F);
                final short short1 = 0;

                GL11.glRotatef(short1, 0.0F, 1.0F, 0.0F);
                GL11.glTranslatef(-1.5F, -1.5F, -1.5F);
                float f1 = 0;
                f1 = 1.0F - f1;
                f1 = 1.0F - f1 * f1 * f1;
                modelChest.chestLid.rotateAngleX = -(f1 * (float) Math.PI / 2.0F);

                modelChest.chestBelow.render(0.0625F);
                modelChest.chestLid.render(0.0625F);
                modelChest.chestKnob.render(0.0625F);
                // GL11.glDisable(GL12.GL_RESCALE_NORMAL);
                GL11.glEnable(GL11.GL_DEPTH_TEST);
                GL11.glPopMatrix();
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            }
            // else if (item.getItemDamage() == 2)
            // {
            // GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            // RenderHelper.disableStandardItemLighting();
            // GL11.glDisable(GL11.GL_LIGHTING);
            // GL11.glDisable(GL11.GL_DEPTH_TEST);
            //
            // this.renderItemIntoGUI(render,
            // FMLClientHandler.instance().getClient().fontRenderer,
            // FMLClientHandler.instance().getClient().renderEngine, new
            // ItemStack(GCCoreItems.rocketFuelBucket, 1, 60), 0, 0);
            //
            // GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            // RenderHelper.enableStandardItemLighting();
            // GL11.glEnable(GL11.GL_LIGHTING);
            // GL11.glEnable(GL11.GL_DEPTH_TEST);
            // }
        }
    }

    // public void renderItemIntoGUI(RenderBlocks renderBlocks, FontRenderer
    // par1FontRenderer, RenderEngine par2RenderEngine, ItemStack par3ItemStack,
    // int par4, int par5)
    // {
    // // GL11.glEnable(GL12.GL_RESCALE_NORMAL);
    // // GL11.glDisable(GL11.GL_DEPTH_TEST);
    // GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    // short short1 = 0;
    //
    // float f1 = 0;
    // float f2;
    //
    // f1 = 1.0F - f1;
    // f1 = 1.0F - f1 * f1 * f1;
    //
    // // Icon icon =
    // par2RenderEngine.func_96448_c(par3ItemStack.bindTexture());
    //
    // // this.drawItems.zLevel = 300.0F;
    // // this.drawItems.bindTexture(100, 100, icon, 16, 16);
    // // GL11.glDisable(GL12.GL_RESCALE_NORMAL);
    // // GL11.glEnable(GL11.GL_DEPTH_TEST);
    // GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    // }

    /** IItemRenderer implementation **/

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        switch (type)
        {
        case ENTITY:
            return true;
        case EQUIPPED:
            return true;
        case INVENTORY:
            return true;
        default:
            return false;
        }
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        switch (type)
        {
        case EQUIPPED:
            this.renderPipeItem(type, (RenderBlocks) data[0], item, -0.5f, -0.5f, -0.5f);
            break;
        case INVENTORY:
            this.renderPipeItem(type, (RenderBlocks) data[0], item, -0.5f, -0.5f, -0.5f);
            break;
        case ENTITY:
            this.renderPipeItem(type, (RenderBlocks) data[0], item, -0.5f, -0.5f, -0.5f);
            break;
        default:
        }
    }

}
