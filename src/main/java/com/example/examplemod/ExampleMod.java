package com.example.examplemod;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

// there appear to be bug in this annotation, Minecraft still loads info from the mcmod.info file.
@Mod(modid = ExampleMod.MODID)
public class ExampleMod
{
    public static final String MODID = "examplemod";
    private Logger logger;
    
    @Instance(ExampleMod.MODID)
    public ExampleMod instance;
    
    /**
     * As described in the EventHandler javadoc,
     * the EventHandler annotation replaces the use of old annotations such as,
     * PreInit, Init and PostInit. Note that some MinecraftForge tutorials still
     * refer to the old annotations. 
     *  
     * @param event
     * @see cpw.mods.fml.common.Mod.EventHandler
     * @see <a href="http://www.minecraftforge.net/wiki/Tutorials/Making_the_base_mod_file">Making_the_base_mod_file</a>
     */
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		// some example code
        System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
	logger.log(Level.INFO, "Example of initialization event:" + event.getModState().toString());
    }
    
    /* demonstrate the other to client module events */
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
	logger = event.getModLog();
	
	logger.log(Level.INFO, "Example of pre-initialization event:" + event.getModState().toString());
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
	logger.log(Level.INFO, "Example of post-initialization event: " + event.getModState().toString());	
    }
}
