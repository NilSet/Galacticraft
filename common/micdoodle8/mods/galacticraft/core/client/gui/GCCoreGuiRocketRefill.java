package micdoodle8.mods.galacticraft.core.client.gui;

import mekanism.api.EnumColor;
import micdoodle8.mods.galacticraft.core.entities.EntitySpaceshipBase;
import micdoodle8.mods.galacticraft.core.inventory.GCCoreContainerRocketRefill;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GCCoreGuiRocketRefill extends GuiContainer
{
    private final IInventory upperChestInventory;
    /**
     * window height is calculated with this values, the more rows, the heigher
     */
    private int inventoryRows = 0;

    private final int type;

    public GCCoreGuiRocketRefill(IInventory par1IInventory, IInventory par2IInventory, int type)
    {
        super(new GCCoreContainerRocketRefill(par1IInventory, par2IInventory, type));
        this.upperChestInventory = par1IInventory;
        this.allowUserInput = false;
        this.inventoryRows = par2IInventory.getSizeInventory() / 9;
        this.ySize = type == 1 ? 180 : 166;
        this.type = type;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRenderer.drawString(StatCollector.translateToLocal("Fuel"), 8, 2 + 3, 4210752);

        this.fontRenderer.drawString(StatCollector.translateToLocal(this.upperChestInventory.getInvName()), 8, 34 + 2 + 3, 4210752);

        if (this.mc.thePlayer != null && this.mc.thePlayer.ridingEntity != null && this.mc.thePlayer.ridingEntity instanceof EntitySpaceshipBase)
        {
            this.fontRenderer.drawString("Fuel:", 125, 15 + 3, 4210752);
            final double percentage = ((EntitySpaceshipBase) this.mc.thePlayer.ridingEntity).getScaledFuelLevel(100);
            final String color = percentage > 80.0D ? EnumColor.BRIGHT_GREEN.code : percentage > 40.0D ? EnumColor.ORANGE.code : EnumColor.RED.code;
            final String str = percentage + "% full";
            this.fontRenderer.drawString(color + str, 117 - str.length() / 2, 20 + 8, 4210752);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        String var4 = null;

        switch (this.type)
        {
        case 1:
            var4 = "/mods/galacticraftcore/textures/gui/rocketrefill-chest.png";
            break;
        default:
            var4 = "/mods/galacticraftcore/textures/gui/rocketrefill-nochest.png";
            break;
        }

        this.mc.renderEngine.bindTexture(var4);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        final int var5 = (this.width - this.xSize) / 2;
        final int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, 176, 199);

        if (this.mc.thePlayer != null && this.mc.thePlayer.ridingEntity != null && this.mc.thePlayer.ridingEntity instanceof EntitySpaceshipBase)
        {
            final int fuelLevel = ((EntitySpaceshipBase) this.mc.thePlayer.ridingEntity).getScaledFuelLevel(38);

            this.drawTexturedModalRect((this.width - this.xSize) / 2 + (this.type == 1 ? 72 : 71), (this.height - this.ySize) / 2 + 45 - fuelLevel, 176, 38 - fuelLevel, 42, fuelLevel);
        }
    }
}
