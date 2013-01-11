package net.zoocraftia.core;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;

public class TaggedEntityManager extends PerPlayerBase{

	public void addEntity(EntityPlayer player, MovingObjectPosition mop)
	{
		if(mop != null && mop.typeOfHit == EnumMovingObjectType.ENTITY)
		{
			Entity taggedEntity = mop.entityHit;
			if(taggedEntity instanceof EntityLiving && !(taggedEntity instanceof EntityPlayer))
			{
				addTaggedEntity(player, (EntityLiving)taggedEntity);
			}
		}
	}
	
	public void addTaggedEntity(EntityPlayer player, EntityLiving tagged)
	{
		trigger(player);
		PlayerTagEntry pte = (PlayerTagEntry) getEntry(player);
		if(!pte.taggedEntities.contains(EntityList.getEntityString(tagged)))
		{
			ZoocraftiaMain.proxy.handleTagAdd(player, tagged);
			pte.taggedEntities.add(EntityList.getEntityString(tagged));
			player.addChatMessage("You have tagged " + EntityList.getEntityString(tagged) + "!");
			pte.writeToNBT(player.getEntityData());
		}
	}
	
	public void setTagged(EntityPlayer player, ArrayList<String> list)
	{
		trigger(player);
		ZoocraftiaMain.proxy.handleTagSet(player, list);
		PlayerTagEntry pte = ((PlayerTagEntry) getEntry(player));
		pte.taggedEntities = list;
		pte.writeToNBT(player.getEntityData());
	}
	
	public void loadPlayer(EntityPlayer player)
	{
		PlayerTagEntry ptg = new PlayerTagEntry(player);
		addEntry(player, ptg);
		ptg.readFromNBT(player.getEntityData());
	}
	
	public void unloadPlayer(EntityPlayer player)
	{
		((PlayerTagEntry)removeEntry(player)).writeToNBT(player.getEntityData());
	}
	
	
	public static class PlayerTagEntry{
		
		public EntityPlayer player;
		public ArrayList<String> taggedEntities = new ArrayList<String>();
		
		public PlayerTagEntry(EntityPlayer player)
		{
			this.player = player;
		}
		
		public void writeToNBT(NBTTagCompound nbt)
		{
			NBTTagCompound nbt1 = new NBTTagCompound();
			int i = 0;
			for(String s : taggedEntities)
			{
				nbt1.setString("Ent" + i, s);
				i++;
			}
			nbt.setTag("Tagged", nbt1);
		}
		
		public void readFromNBT(NBTTagCompound nbt)
		{
			NBTTagCompound nbt1 = nbt.getCompoundTag("Tagged");
			for(int i = 0; i < nbt1.getTags().size(); i++)
			{
				taggedEntities.add(nbt1.getString("Ent" + i));
			}
		}
	}
}
