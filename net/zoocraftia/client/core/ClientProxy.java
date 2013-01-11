package net.zoocraftia.client.core;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.Configuration;
import net.zoocraftia.api.MoneyHelper;
import net.zoocraftia.api.Zoocraftia;
import net.zoocraftia.client.core.gui.GuiEntity;
import net.zoocraftia.client.core.helpers.KeyBindingHelper;
import net.zoocraftia.client.core.listeners.KeyListener;
import net.zoocraftia.core.CommonProxy;
import net.zoocraftia.core.DartEntity;
import net.zoocraftia.core.MinecraftPlayerState;
import net.zoocraftia.core.MoneyHandler.Debt;
import net.zoocraftia.core.ZoocraftiaBlocks;
import net.zoocraftia.core.ZoocraftiaCore;
import net.zoocraftia.core.ZoocraftiaMain;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy{
	
	public void registerKeyBindingHandler()
	{
		KeyBindingRegistry.registerKeyBinding(new KeyListener());
	}
	
	public void setKeyBinding(String name, int value)
	{
		KeyBindingHelper.addKeyBinding(name, value);
		KeyBindingHelper.addIsRepeating(true);
	}
	
	public void initialize()
	{
		super.initialize();
		
		setKeyBinding("Debt Menu", Keyboard.KEY_M);
		
		//GUI's
		Zoocraftia.addGui(GuiEntity.class, 0);
	}	
	
	public void postInitialize(){
		ZoocraftiaCore.saltwaterRenderID = RenderingRegistry.getNextAvailableRenderId();
		ZoocraftiaCore.plexiglassRenderID = RenderingRegistry.getNextAvailableRenderId();
		MinecraftForgeClient.preloadTexture("/zoocraftia/core/blocks.png");
		MinecraftForgeClient.preloadTexture("/zoocraftia/core/items.png");
		MinecraftForgeClient.preloadTexture("/zoocraftia/core/textures/water.png");
		MinecraftForgeClient.preloadTexture("/zoocraftia/core/connectedGlass.png");
		
		RenderingRegistry.registerEntityRenderingHandler(DartEntity.class, new DartRender());
		RenderingRegistry.registerBlockHandler(new SaltwaterRenderHandler());
		RenderingRegistry.registerBlockHandler(new PlexiglassRenderHandler());
		Minecraft.getMinecraft().renderEngine.registerTextureFX(new SaltwaterFX(ZoocraftiaBlocks.saltwaterMoving.blockIndexInTexture, true));
		Minecraft.getMinecraft().renderEngine.registerTextureFX(new SaltwaterFX(ZoocraftiaBlocks.saltwaterStill.blockIndexInTexture, false));
	}
	
	public void triggerKeyEvent(KeyBinding key)
	{
		
	}
	
	public void handleTagSet(EntityPlayer player, ArrayList<String> list)
	{
		if(MinecraftPlayerState.isLan())
		{
			super.handleTagSet(player, list);
		}
	}
	
	public void handleTagAdd(EntityPlayer player, EntityLiving tagged)
	{
		if(MinecraftPlayerState.isLan())
		{
			super.handleTagAdd(player, tagged);
		}
	}
	
	public void versionNotify()
	{
		Minecraft.getMinecraft().thePlayer.addChatMessage("New zoocraftia version available (" + ZoocraftiaMain.newVersion + ")");
	}
	
	public World getClientWorld() {
		return FMLClientHandler.instance().getClient().theWorld;
	}
	
	public void handleDebtSet(EntityPlayer player, ArrayList<Debt> debts)
	{
		if(MinecraftPlayerState.isLan())
		{
			super.handleDebtSet(player, debts);
		}
	}
	
	public void handleMoneySet(EntityPlayer player, int i)
	{
		if(MinecraftPlayerState.isLan())
		{
			super.handleMoneySet(player, i);
		}
	}
	
	public void handleMoneyAdd(EntityPlayer player, int i)
	{
		if(MinecraftPlayerState.isLan())
		{
			super.handleMoneyAdd(player, i);
		}
	}
	
	public void handleDebtAdd(EntityPlayer player, EntityLiving debtOwner, int debt)
	{
		if(MinecraftPlayerState.isLan())
		{
			super.handleDebtAdd(player, debtOwner, debt);
		}
	}
	
	public void handleDebtRemove(EntityPlayer player, EntityLiving debtOwner, int debt, double interestRate)
	{
		if(MinecraftPlayerState.isLan())
		{
			super.handleDebtRemove(player, debtOwner, debt, interestRate);
		}
	}
	
	public File getLocation()
	{
		return Minecraft.getMinecraftDir();
	}
	
	public boolean clientTick(float time, Minecraft minecraftInstance)
    {
		if(!ZoocraftiaMain.INSTANCE.versionDisplayed)
		{
			ZoocraftiaMain.INSTANCE.checkVersion();
			ZoocraftiaMain.INSTANCE.versionDisplayed = true;
		}
		
		int l = 2;

		double money = MoneyHelper.getMoney(minecraftInstance.thePlayer);
		
		int color;
		
		if (money < 0)
		{
			color = 0xFF0000;
		} else if (money >= 100000)
		{
			color = 0x00FF00;
		} else
		{
			color = 0xFFFFFF;
		}
		
		String prefix = "$";
		
		if(ZoocraftiaMain.INSTANCE.getOrCreateBooleanProperty("Euro", false, Configuration.CATEGORY_GENERAL, "Set to true if you want to have your currency displayed in euros"))
		{
			String s = new DecimalFormat("#.00").format(money * 0.79);
			if(s.startsWith(".") || s.startsWith(","))
			{
				s = 0 + s;
			}
			money = Double.valueOf(s.replace(",", "."));
			prefix = new String(Character.toChars(0x20AC));
		}
		
		minecraftInstance.fontRenderer.drawStringWithShadow(StringTranslate.getInstance().translateKey("zoocraftia.money_balance.name") + ": " + prefix + money, 2, l, color);
		
		l += 10;

		return true;
    }
	
}
