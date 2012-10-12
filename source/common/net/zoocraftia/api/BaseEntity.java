package net.zoocraftia.api;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityAISwimming;
import net.minecraft.src.EntityAIWatchClosest;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;
import net.zoocraftia.api.EntityEnums.FoodType;
import net.zoocraftia.api.EntityEnums.Sex;
import net.zoocraftia.core.ZoocraftiaMain;
import net.zoocraftia.core.client.GuiEntity;
import net.zoocraftia.core.entityAI.EntityAIAttackHungerwise;
import net.zoocraftia.core.entityAI.EntityAIWander;

public abstract class BaseEntity extends EntityLiving implements IEntityAdditionalSpawnData{

	private EntityEnums.Sex sex;
	private EntityEnums.EntityType entityType;
	private EntityEnums.FoodType type;
	
	private int hunger;
	private int prevHunger;
    private int hungerTimer;
    private float foodSaturationLevel;
    private float foodExhaustionLevel;
    
    private long ageTicks;
    private boolean isWild;
    
    private LinkedList<EntityMessages> messages = new LinkedList<EntityMessages>();
	
    
    //The default constructor for the entity with default settings
	public BaseEntity(World world) {
		super(world);
		texture = getTexture();
		hunger = getMaxHunger();
		setSexRandomly();
		setWild(true);
		entityType = getEntityType();
		type = getFoodType();
		moveSpeed = 0.25F;
		
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIWander(this, moveSpeed));
		tasks.addTask(2, new EntityAIWatchClosest(this, getClass(), 4.0F));
		
		targetTasks.addTask(1, new EntityAIAttackHungerwise(this, 24.0F, true));
	}
	
	public BaseEntity(World world, EntityEnums.Sex sex) {
		this(world);
		this.sex = sex;
	}
	
	//initializes the entity, specifically it sets it to watch it's age
	//12 - breeding age
	//13 - actual age
	//14 - hunger
	//16 - isWild, mostly used to disallow interaction with animals if they are completely wild
	public void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(12, new Integer(0));
		dataWatcher.addObject(13, new Integer(0));
		dataWatcher.addObject(14, new Integer(0));
		dataWatcher.addObject(16, new Integer(0));
	}
	
	public void writeSpawnData(ByteArrayDataOutput data)
	{
		data.writeInt(sex.ordinal());
	}

    public void readSpawnData(ByteArrayDataInput data)
    {
    	int sexint = data.readInt();
    	for(Sex sex : EntityEnums.Sex.values()){
    		if(sex.ordinal() == sexint)
    		{
    			setSex(sex);
    			break;
    		}
    	}
    }
	
	//sets the sex of the entity randomly
	public void setSexRandomly() {
		if(worldObj.rand.nextBoolean())
		{
			setSex(Sex.MALE);
		}else{
			setSex(Sex.FEMALE);
		}
	}
	
	public void onUpdate()
	{
		++ageTicks;
		super.onUpdate();
	}
	
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		
		//handles breeded age updating
		int bage = getAge();
		if(bage < 0)
		{
			bage++;
			setAgeBreeded(bage);
		}else if(bage > 0)
		{
			bage--;
			setAgeBreeded(bage);
		}
		
		handleHungerManagement();
		
		if(getHealth() <= getMaxHealth() * 0.1)
		{
			addMessage(EntityMessages.lowHealth);
		}
		
		//updates the actual age
		if(ageTicks >= (365 * 12000) + (getRNG().nextInt(20) * 12000)) addMessage(EntityMessages.agedDeath);

	}
	
	protected void dropFewItems(boolean par1, int par2)
    {
        ItemStack stack = null;
        
        if(isBurning())
        {
	        if(type == FoodType.CARNIVORE)
	        {
	        	stack = new ItemStack(Items.getItem("meat"), 1, 3);
	        }else if(type == FoodType.HERBIVORE)
	        {
	        	stack = new ItemStack(Items.getItem("meat"), 1, 1);
	        }else if(type == FoodType.OMNIVORE)
	        {
	        	stack = new ItemStack(Items.getItem("meat"), 1, 5);
	        }
        }else{
        	if(type == FoodType.CARNIVORE)
	        {
	        	stack = new ItemStack(Items.getItem("meat"), 1, 2);
	        }else if(type == FoodType.HERBIVORE)
	        {
	        	stack = new ItemStack(Items.getItem("meat"), 1, 0);
	        }else if(type == FoodType.OMNIVORE)
	        {
	        	stack = new ItemStack(Items.getItem("meat"), 1, 4);
	        }
        }
        
        if(stack == null)
        {
        	return;
        }

        int var4 = this.rand.nextInt(3);

        if (par2 > 0)
        {
            var4 += this.rand.nextInt(par2 + 1);
        }

        for (int var5 = 0; var5 < var4; ++var5)
        {
        	entityDropItem(stack, 0.0F);
        }
        
    }
	
	//handles hunger decreasing(copied from FoodStats.class)
	private void handleHungerManagement() {
        prevHunger = getHunger();

        if (this.foodExhaustionLevel > 4.0F)
        {
            this.foodExhaustionLevel -= 4.0F;

            if (this.foodSaturationLevel > 0.0F)
            {
                this.foodSaturationLevel = Math.max(this.foodSaturationLevel - 1.0F, 0.0F);
            }
            setHunger(Math.max(getHunger() - 1, 0));
        }

        if (getHunger() >= getMaxHunger() * 0.9)
        {
            hungerTimer++;

            if (hungerTimer >= delayBeforeHeal())
            {
                heal(1);
                hungerTimer = 0;
            }
        }
        else if (getHunger() <= 0)
        {
            hungerTimer++;

            if (hungerTimer >= delayBeforeDamage())
            {
                if (getHealth() > getMaxHealth() / 2)
                {
                	addMessage(EntityMessages.starving);
                    attackEntityFrom(DamageSource.starve, 1);
                }

                this.hungerTimer = 0;
            }
        }
        else
        {
            hungerTimer = 0;
        }
	}
	
	//reads the data from NBT
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		setHunger(nbt.getInteger("Hunger"));
		hungerTimer = nbt.getInteger("HungerTimer");
        foodSaturationLevel = nbt.getFloat("foodSaturationLevel");
        foodExhaustionLevel = nbt.getFloat("foodExhaustionLevel");
        ageTicks = nbt.getLong("Age");
        setSex(sex.valueOf(nbt.getString("Sex")));
        setAgeBreeded(nbt.getInteger("AgeBreed"));
        setWild(nbt.getBoolean("IsWild"));
        NBTTagCompound nbt1 = nbt.getCompoundTag("Messages");
        for(int i = 0; i < 128; i++)
        {
        	int x = nbt1.getInteger("Message" + i);
        	if(x == 1025)
        	{
        		continue;
        	}else{
        		addMessage(EntityMessages.messages[x]);
        	}
        }
	}
	
	//writes the data to NBT
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setInteger("Hunger", getHunger());
		nbt.setInteger("HungerTimer", hungerTimer);
        nbt.setFloat("foodSaturationLevel", foodSaturationLevel);
        nbt.setFloat("foodExhaustionLevel", foodExhaustionLevel);
		nbt.setString("Sex", sex.name());
		nbt.setInteger("AgeBreed", getAgeBreeded());
		nbt.setLong("Age", ageTicks);
		nbt.setBoolean("IsWild", isWild());
		NBTTagCompound nbt1 = new NBTTagCompound();
		nbt.setTag("Messages", nbt1);
		for(int i = 0; i < 128; i++)
		{
			EntityMessages em;
			int id = 1025;
			try{
				em = messages.get(i);
				id = em.id;
			}catch(IndexOutOfBoundsException e){}
			nbt1.setInteger("Message" + i, id);
		}
	}
	
	//Enables the AI introduced in minecraft 1.2
	protected boolean isAIEnabled()
    {
        return true;
    }
	
	//Opens the entity info gui to the player
	public boolean interact(EntityPlayer player)
    {
		FMLNetworkHandler.openGui(player, ZoocraftiaMain.getInstance(), Zoocraftia.getGuiId(GuiEntity.class), worldObj, this.entityId, 0, 0);
        return true;
    }
	
	//Methods that handle exhaustion
	public void addMovementStat(double par1, double par3, double par5)
    {
        if (this.ridingEntity == null)
        {
            int var7;

            if (this.isInsideOfMaterial(Material.water))
            {
                var7 = Math.round(MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5) * 100.0F);

                if (var7 > 0)
                {
                    this.addExhaustion(0.015F * (float)var7 * 0.01F);
                }
            }
            else if (this.isInWater())
            {
                var7 = Math.round(MathHelper.sqrt_double(par1 * par1 + par5 * par5) * 100.0F);

                if (var7 > 0)
                {
                    this.addExhaustion(0.015F * (float)var7 * 0.01F);
                }
            }
            else if (this.onGround)
            {
                var7 = Math.round(MathHelper.sqrt_double(par1 * par1 + par5 * par5) * 100.0F);

                if (var7 > 0)
                {
                    if (this.isSprinting())
                    {
                        this.addExhaustion(0.099999994F * (float)var7 * 0.01F);
                    }
                    else
                    {
                        this.addExhaustion(0.01F * (float)var7 * 0.01F);
                    }
                }
            }
        }
    }
	
	protected void jump()
    {
        super.jump();
        
        if (this.isSprinting())
        {
            this.addExhaustion(0.8F);
        }
        else
        {
            this.addExhaustion(0.2F);
        }
    }
	
	protected void damageEntity(DamageSource par1DamageSource, int par2)
    {
		super.damageEntity(par1DamageSource, par2);
		addExhaustion(par1DamageSource.getHungerDamage());
    }
	
	//tons of getter and setter methods for all of the data that needs to be send to server
	public double getActualAge()
	{
		String s = new DecimalFormat("#.0").format(ageTicks / 12000.0);
		if(s.startsWith(".") || s.startsWith(","))
		{
			s = 0 + s;
		}
		return Double.valueOf(s.replace(",", "."));
	}
	
	public int getAgeBreeded()
	{
		return dataWatcher.getWatchableObjectInt(12);
	}
	
	public void setAgeBreeded(int i)
	{
		dataWatcher.updateObject(12, Integer.valueOf(i));
	}
	
	public void setSex(EntityEnums.Sex sex)
	{
		this.sex = sex;
	}
	
	public EntityEnums.Sex getSex()
	{
		return sex;
	}
	
	public int getHunger()
	{
		return hunger;
	}
	
	public void setHunger(int i)
	{
		if(i > getMaxHunger())
		{
			return;
		}
		hunger = i;
		dataWatcher.updateObject(14, Integer.valueOf(i));
	}
	
	public boolean needFood()
	{
		if(getHunger() < getMaxHunger())
		{
			return true;
		}
		return false;
	}
	
	public void addExhaustion(float par1)
    {
        this.foodExhaustionLevel = Math.min(this.foodExhaustionLevel + par1, 40.0F);
    }
	
	public void setWild(boolean b)
	{
		int i = 0;
		if(!b)
		{
			i = 1;
		}
		dataWatcher.updateObject(16, Integer.valueOf(i));
		isWild = b;
	}
	
	public boolean isWild()
	{
		if(dataWatcher.getWatchableObjectInt(16) == 0)
		{
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isChild()
	{
		return getAgeBreeded() < 0;
	}
	
	public void addMessage(EntityMessages message)
	{
		if(!messages.contains(message))
		{
			messages.add(message);
		}
	}
	
	public String getMessageForGUI()
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
		return messages.removeFirst().message;
	}
	
	public EntityMessages getNextMessageInLine()
	{
		EntityMessages message = null;
		try{
			message = messages.getFirst();
		}catch(NoSuchElementException e){}
		return message;
	}
	
	public boolean isHungry()
	{
		return getMaxHunger() * 0.1 <= getHunger();
	}
	
	//methods that modder can override(he can override others but it's not recomended unless using super() call)
	public int delayBeforeHeal()
	{
		return 80;
	}
	
	public int delayBeforeDamage()
	{
		return 180;
	}
	
	public ItemStack getDroppedItem(){
		return null;
	}
	
	public float getBlockPathWeight(int x, int y, int z)
    {
        return 0.0F;
    }

	
	
	//ABSTRACT METHODS
	@SideOnly(Side.CLIENT)
	public abstract String getTexture();
	
	public abstract int getMaxHunger();
	
	public abstract EntityEnums.EntityType getEntityType();
	
	public abstract EntityEnums.FoodType getFoodType();
	
	@SideOnly(Side.CLIENT)
	public abstract void doRender(double x, double y, double z, float yaw, float ptt);
	
	@SideOnly(Side.CLIENT)
	public abstract ModelBase getModel();
	
	public abstract int getAttackStrenght();
	
	@SideOnly(Side.CLIENT)
	public abstract float getShadowSize();
	
	public abstract ItemStack[] getFoodThatCanBeFed();
}
