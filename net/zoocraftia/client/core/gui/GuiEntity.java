package net.zoocraftia.client.core.gui;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.zoocraftia.api.BaseEntity;
import net.zoocraftia.api.EntityMessages;
import net.zoocraftia.api.EntityEnums.Sex;

public class GuiEntity extends GuiScreen {

	private EntityPlayer player;
	private int xSize;
	private int ySize;
	private int barLenght = 149;
	private int barHeight = 5;
	private String message;
	private GuiButton buttonNext;
	private int age;
	private int hunger;
	private Sex sex;
	private int health;
	private LinkedList<EntityMessages> messages = new LinkedList<EntityMessages>();
	private int maxHunger;
	private int maxHealth;
	private String name;

	public GuiEntity(EntityPlayer player, int age, Sex sex, int hunger, int health, LinkedList messages, int maxHealth, int maxHunger, String name) {
		this.player = player;
		xSize = 176;
		ySize = 208;
		this.age = age;
		this.sex = sex;
		this.hunger = hunger;
		this.health = health;
		this.messages = new LinkedList<EntityMessages>(messages);
		this.maxHealth = maxHealth;
		this.maxHunger = maxHunger;
		this.name = name;
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
			if(messages.size() == 0)
			{
				messages.addFirst(EntityMessages.ok);
			}
			Collections.sort(messages, new Comparator(){
				public int compare(Object arg0, Object arg1) {
					return Integer.valueOf(((EntityMessages)arg1).getPriorirty()).compareTo(Integer.valueOf(((EntityMessages)arg0).getPriorirty()));
				}
				
			});
			message = messages.removeFirst().message;
		}
	}
	
	public void updateScreen() {
		super.updateScreen();
		if(getNextMessageInLine() == null)
		{
			buttonNext.enabled = false;
			return;
		}
	}
	
	public EntityMessages getNextMessageInLine()
	{
		EntityMessages message = null;
		try{
			message = messages.getFirst();
		}catch(NoSuchElementException e){}
		return message;
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
		drawCenteredString(fontRenderer, name, width / 2, top + 10, 0xFFFFFF);
		
		drawCenteredString(fontRenderer, "Age: " + getActualAge() + " days", width / 2, top + 20, 0xFFFFFF);
		mc.renderEngine.bindTexture(gui);
		
		drawTexturedModalRect((width - barLenght) / 2, top + 97, 0, ySize + 3 + barHeight, barLenght, barHeight);
		int healthbarLenght = (int)(((float)health / (float)maxHealth) * (float)barLenght);
		drawTexturedModalRect((width - barLenght) / 2, top + 97, 0, ySize + 1,  healthbarLenght, barHeight);
		
		drawTexturedModalRect((width - barLenght) / 2, top + 122, 0, ySize + 7 + 3 * barHeight, barLenght, barHeight);
		int hungerbarLenght = (int)(((float)hunger / (float)maxHunger) * (float)barLenght);
		drawTexturedModalRect((width - barLenght) / 2, top + 122, 0, ySize + 5 + 2 * barHeight, hungerbarLenght, barHeight);
		
		if(sex == Sex.FEMALE)
		{
			drawTexturedModalRect(left + 30, top + 39, barLenght, ySize + 1, 16, 25);
		}else if(sex == Sex.MALE){
			drawTexturedModalRect(left + 121, top + 39, barLenght + 16, ySize + 1, 25, 25);
		}
		
		drawMessage();
		
		super.drawScreen(moseX, mouseZ, partialTicks);
	}
	
	public double getActualAge()
	{
		String s = new DecimalFormat("#.0").format(age / 12000.0);
		if(s.startsWith(".") || s.startsWith(","))
		{
			s = 0 + s;
		}
		return Double.valueOf(s.replace(",", "."));
	}
	
	private void drawMessage() {
		ArrayList<String> lines = new ArrayList<String>();
		String s = message;
		if(s == null)
		{
			s = EntityMessages.ok.message;
		}
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
