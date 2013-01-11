package net.zoocraftia.client.core.listeners;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.zoocraftia.core.lib.Sounds;
import net.zoocraftia.core.managers.LogManager;

public class SoundLoadlistener {

	@ForgeSubscribe
	public void onSoundLoad(SoundLoadEvent event)
	{
		for(String file : Sounds.soundFiles)
		{
			try{
				event.manager.addSound(file, new File(this.getClass().getResource("/" + file).toURI()));
			}catch(Exception e)
			{
				LogManager.log(Level.WARNING, "Failed to load sound file: " + file);
			}
		}
	}
	
}
