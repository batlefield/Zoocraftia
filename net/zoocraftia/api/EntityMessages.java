package net.zoocraftia.api;

public class EntityMessages {
	
	public static final EntityMessages[] messages = new EntityMessages[128];
	
	public String message;
	public int id;
	public int priority;
	
	public static int LOWEST = 0;
	public static int LOW = 1;
	public static int NORMAL = 2;
	public static int HIGH = 3;
	public static int HIGHEST = 4;
	
	public EntityMessages(int id, String s)
	{
		if(messages[id] != null)
		{
			System.out.println("Conflict @ " + id + ", skipping!");
		}else{
			messages[id] = this;
			message = s;
			this.id = id;
			priority = 0;
		}
	}
	
	public EntityMessages setPriority(int i)
	{
		priority = i;
		return this;
	}
	
	public int getPriorirty()
	{
		return priority;
	}
	
	public static final EntityMessages ok = new EntityMessages(0, "The entity is OK and doesn't require anything");
	public static final EntityMessages lowHealth = new EntityMessages(1, "The entity is low on health! Take some food and feed it to regain health!").setPriority(HIGHEST);
	public static final EntityMessages starving = new EntityMessages(2, "This animal is starving, it will die if you don't feed it").setPriority(HIGH);
	public static final EntityMessages agedDeath = new EntityMessages(3, "This animals will die very soon. It's very old.").setPriority(HIGHEST);
}
