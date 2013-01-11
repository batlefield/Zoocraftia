package net.zoocraftia.core.entityAI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.zoocraftia.api.BaseEntity;
import net.zoocraftia.api.EntityEnums;
import net.zoocraftia.api.EntityEnums.EntityAction;
import net.zoocraftia.core.TypeHelper;
import net.zoocraftia.core.VanillaEntitiesType;

public class EntityAIAttackHungerwise extends EntityAITarget{

	private EntityLiving target;
	
	public EntityAIAttackHungerwise(BaseEntity entity, float targetDistance, boolean shouldCheckSight) {
		super(entity, targetDistance, shouldCheckSight, false);
		this.targetDistance = targetDistance;
		setMutexBits(1);
	}

	public boolean shouldExecute() {
		if(taskOwner instanceof BaseEntity)
		{
			if(taskOwner.getAttackTarget() != null)
			{
				return false;
			}
			BaseEntity attacker = (BaseEntity)taskOwner;
			if(attacker.getMaxHunger() * 0.3 <= attacker.getHunger())
			{
				List<Entity> list = getEntitiesWithinAABB(taskOwner.boundingBox.expand(targetDistance, 4.0D, targetDistance));
				
				Collections.sort(list, new Comparator(){

					public int compare(Entity par1Entity, Entity par2Entity)
				    {
				        double var3 = taskOwner.getDistanceSqToEntity(par1Entity);
				        double var5 = taskOwner.getDistanceSqToEntity(par2Entity);
				        return var3 < var5 ? -1 : (var3 > var5 ? 1 : 0);
				    }

				    public int compare(Object par1Obj, Object par2Obj)
				    {
				        return compare((Entity)par1Obj, (Entity)par2Obj);
				    }
				});
				
				for(Entity entity : list)
				{
					EntityEnums.EntityType type = null;
					if(entity.getClass().isAssignableFrom(EntityLiving.class) && !entity.getClass().isAssignableFrom(BaseEntity.class))
					{
						type = VanillaEntitiesType.getEntityType((EntityLiving)entity);
					}else if(entity.getClass().isAssignableFrom(BaseEntity.class))
					{
						type = ((BaseEntity)entity).getEntityType();
					}else{
						type = null;
					}
					
					if(type == null)
					{
						continue;
					}
					
					if(!isSuitableTarget((EntityLiving)entity, false))
					{
						continue;
					}
					
					if(TypeHelper.getHungerAction(attacker.getEntityType(), type, attacker.getHunger() / attacker.getMaxHunger(), attacker.getFoodType()) == EntityAction.ATTACK)
					{
						return true;
					}
				}
				
				return false;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	private List<Entity> getEntitiesWithinAABB(AxisAlignedBB aabb) {
		World world = taskOwner.worldObj;
		int var3 = MathHelper.floor_double((aabb.minX - world.MAX_ENTITY_RADIUS) / 16.0D);
        int var4 = MathHelper.floor_double((aabb.maxX + world.MAX_ENTITY_RADIUS) / 16.0D);
        int var5 = MathHelper.floor_double((aabb.minZ - world.MAX_ENTITY_RADIUS) / 16.0D);
        int var6 = MathHelper.floor_double((aabb.maxZ + world.MAX_ENTITY_RADIUS) / 16.0D);
        ArrayList<Entity> list = new ArrayList<Entity>();

        for (int var8 = var3; var8 <= var4; ++var8)
        {
            for (int var9 = var5; var9 <= var6; ++var9)
            {
                if (world.getChunkProvider().chunkExists(var8, var9))
                {
                	Chunk chunk = world.getChunkFromChunkCoords(var8, var9);
                	int i = MathHelper.floor_double((aabb.minY - World.MAX_ENTITY_RADIUS) / 16.0D);
                    int j = MathHelper.floor_double((aabb.maxY + World.MAX_ENTITY_RADIUS) / 16.0D);

                    if (i < 0)
                    {
                        i = 0;
                    }
                    else if (i >= chunk.entityLists.length)
                    {
                        i = chunk.entityLists.length - 1;
                    }

                    if (j >= chunk.entityLists.length)
                    {
                        j = chunk.entityLists.length - 1;
                    }
                    else if (j < 0)
                    {
                        j = 0;
                    }

                    for (int k = i; k <= j; ++k)
                    {
                        List var7 = chunk.entityLists[k];

                        for (int i1 = 0; i1 < var7.size(); ++i1)
                        {
                            Entity entity = (Entity)var7.get(i1);

                            if (entity.boundingBox.intersectsWith(aabb))
                            {
                                list.add(entity);
                            }
                        }
                    }
                }
            }
        }

        return list;
	}

	public void startExecuting()
    {
		taskOwner.setAttackTarget(target);
		super.startExecuting();
    }

}
