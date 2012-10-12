package net.zoocraftia.core.client;

import java.util.ArrayList;

import net.minecraft.src.EntityList;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import net.zoocraftia.api.BaseEntity;
import net.zoocraftia.api.EntityEnums.Sex;

public class GuiEntity extends GuiScreen {

	private EntityPlayer player;
	private BaseEntity entity;
	private int xSize;
	private int ySize;
	private int barLenght = 149;
	private int barHeight = 5;
	private String message;
	private GuiButton buttonNext;

	public GuiEntity(EntityPlayer player, BaseEntity entity) {
		this.player = player;
		this.entity = entity;
		xSize = 176;
		ySize = 208;
		message = entity.getMessageForGUI();
	}
	
	public void initGui()
	{
		super.initGui();
		controlList.clear();
		controlList.add(buttonNext = new GuiButton(1, (width / 2) + 4, (height / 2) + 80, 80, 20, "Next message"));
	}
	
	protected void actionPerformed(GuiButton gb) {
		if(gb.id == buttonNext.id)
		{
			message = entity.getMessageForGUI();
		}
	}
	
	public void updateScreen() {
		super.updateScreen();
		if(entity.getNextMessageInLine() == null)
		{
			buttonNext.enabled = false;
			return;
		}
	}
	
	public void drawScreen(int moseX, int mouseZ, float partialTicks)
	{
		drawDefaultBackground();
		int gui = mc.renderEngine.getTexture("/zoocraftia/core/gui/entity.png");
		mc.renderEngine.bindTexture(gui);
		int left = (width - xSize) / 2;
		int top = (height - ySize) / 2;
		drawTexturedModalRect(left, top, 0, 0, xSize, ySize);
		
		drawCenteredString(fontRenderer, "Health", width / 2, top + 86, 0xFFFFFF);
		drawCenteredString(fontRenderer, "Hunger", width / 2, top + 110, 0xFFFFFF);
		drawCenteredString(fontRenderer, EntityList.getEntityString(entity), width / 2, top + 10, 0xFFFFFF);
		drawCenteredString(fontRenderer, "Age: " + entity.getActualAge() + " days", width / 2, top + 20, 0xFFFFFF);
		mc.renderEngine.bindTexture(gui);
		
		drawTexturedModalRect((width - barLenght) / 2, top + 97, 0, ySize + 3 + barHeight, barLenght, barHeight);
		int healthbarLenght = (int)(((float)entity.getHealth() / (float)entity.getMaxHealth()) * (float)barLenght);
		drawTexturedModalRect((width - barLenght) / 2, top + 97, 0, ySize + 1,  healthbarLenght, barHeight);
		
		drawTexturedModalRect((width - barLenght) / 2, top + 122, 0, ySize + 7 + 3 * barHeight, barLenght, barHeight);
		int hungerbarLenght = (int)(((float)entity.getHunger() / (float)entity.getMaxHunger()) * (float)barLenght);
		drawTexturedModalRect((width - barLenght) / 2, top + 122, 0, ySize + 5 + 2 * barHeight, hungerbarLenght, barHeight);
		
		if(entity.getSex() == Sex.FEMALE)
		{
			drawTexturedModalRect(left + 30, top + 39, barLenght, ySize + 1, 16, 25);
		}else if(entity.getSex() == Sex.MALE){
			drawTexturedModalRect(left + 121, top + 39, barLenght + 16, ySize + 1, 25, 25);
		}
		
		drawMessage();
		
		super.drawScreen(moseX, mouseZ, partialTicks);
	}
	
	private void drawMessage() {
		ArrayList<String> lines = new ArrayList<String>();
		String s = message;
		lines.addAll(fontRenderer.listFormattedStringToWidth(s, 105));
		int x = 135;
		for(int i = 0; i < lines.size(); i++)
		{
			if(i >= 6)
			{
				return;
			}
			s = lines.get(i);
			drawString(fontRenderer, s, (width / 2) - 80, ((height - ySize) / 2) + x, 0xFFFFFF);
			x += 11;
		}
		
	}

	public boolean doesGuiPauseGame()
    {
        return false;
    }
}
