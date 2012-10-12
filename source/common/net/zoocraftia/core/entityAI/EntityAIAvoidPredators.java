package net.zoocraftia.core.entityAI;

import java.util.List;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityAIBase;
import net.minecraft.src.EntityCreature;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityTameable;
import net.minecraft.src.PathEntity;
import net.minecraft.src.PathNavigate;
import net.minecraft.src.RandomPositionGenerator;
import net.minecraft.src.Vec3;

public class EntityAIAvoidPredators extends EntityAIBase
{
    /** The entity we are attached to */
    private EntityCreature theEntity;
    private float field_48242_b;
    private float field_48243_c;
    private Entity closestLivingEntity;
    private float distanceFromEntity;
    private PathEntity field_48238_f;

    /** The PathNavigate of our entity */
    private PathNavigate entityPathNavigate;

    /** The class of the entity we should avoid */
    private Class targetEntityClass;

    public EntityAIAvoidPredators(EntityCreature par1EntityCreature, Class par2Class, float par3, float par4, float par5)
    {
        this.theEntity = par1EntityCreature;
        this.targetEntityClass = par2Class;
        this.distanceFromEntity = par3;
        this.field_48242_b = par4;
        this.field_48243_c = par5;
        this.entityPathNavigate = par1EntityCreature.getNavigator();
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (this.targetEntityClass == EntityPlayer.class)
        {
            if (this.theEntity instanceof EntityTameable && ((EntityTameable)this.theEntity).isTamed())
            {
                return false;
            }

            this.closestLivingEntity = this.theEntity.worldObj.getClosestPlayerToEntity(this.theEntity, (double)this.distanceFromEntity);

            if (this.closestLivingEntity == null)
            {
                return false;
            }
        }
        else
        {
            List var1 = this.theEntity.worldObj.getEntitiesWithinAABB(this.targetEntityClass, this.theEntity.boundingBox.expand((double)this.distanceFromEntity, 3.0D, (double)this.distanceFromEntity));

            if (var1.size() == 0)
            {
                return false;
            }

            this.closestLivingEntity = (Entity)var1.get(0);
        }

        if (!this.theEntity.getEntitySenses().canSee(this.closestLivingEntity))
        {
            return false;
        }
        else
        {
            Vec3 var2 = RandomPositionGenerator.findRandomTargetBlockTowards(this.theEntity, 16, 7, Vec3.getVec3Pool().getVecFromPool(this.closestLivingEntity.posX, this.closestLivingEntity.posY, this.closestLivingEntity.posZ));

            if (var2 == null)
            {
                return false;
            }
            else if (this.closestLivingEntity.getDistanceSq(var2.xCoord, var2.yCoord, var2.zCoord) < this.closestLivingEntity.getDistanceSqToEntity(this.theEntity))
            {
                return false;
            }
            else
            {
                this.field_48238_f = this.entityPathNavigate.getPathToXYZ(var2.xCoord, var2.yCoord, var2.zCoord);
                return this.field_48238_f == null ? false : this.field_48238_f.isDestinationSame(var2);
            }
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !this.entityPathNavigate.noPath();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.entityPathNavigate.setPath(this.field_48238_f, this.field_48242_b);
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.closestLivingEntity = null;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        if (this.theEntity.getDistanceSqToEntity(this.closestLivingEntity) < 49.0D)
        {
            this.theEntity.getNavigator().setSpeed(this.field_48243_c);
        }
        else
        {
            this.theEntity.getNavigator().setSpeed(this.field_48242_b);
        }
    }
}
