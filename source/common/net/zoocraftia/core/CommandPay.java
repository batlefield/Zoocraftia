package net.zoocraftia.core;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.CommandBase;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ICommandSender;
import net.minecraft.src.PlayerNotFoundException;
import net.minecraft.src.WrongUsageException;
import net.zoocraftia.api.MoneyHelper;

public class CommandPay extends CommandBase{

	public String getCommandName() {
		return "pay";
	}

	public String getCommandUsage(ICommandSender par1ICommandSender)
    {
        return "/pay <player> <amount>";
    }
	
	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		if(var2.length == 2)
		{
			EntityPlayer sender = getCommandSenderAsPlayer(var1);
			EntityPlayer player = getPlayerFromString(var2[0]);
			int payAmount = parseInt(var1, var2[1]);
			if(payAmount >= 1)
			{
				MoneyHelper.addMoney(player, payAmount);
				MoneyHelper.removeMoney(sender, payAmount);
				sender.addChatMessage("You have paid $" + payAmount + " to " + player.username);
				player.addChatMessage("You've been paid $" + payAmount + " from " + sender.username);
			}
		}else{
			throw new WrongUsageException("/pay <player> <amount>", new Object[0]);
		}
	}
	
	protected EntityPlayer getPlayerFromString(String par1Str)
    {
        EntityPlayerMP var2 = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(par1Str);

        if (var2 == null)
        {
            throw new PlayerNotFoundException();
        }
        else
        {
            return var2;
        }
    }

}
