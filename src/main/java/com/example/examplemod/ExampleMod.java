package com.example.examplemod;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import com.example.examplemod.common.ExampleModCommonProxy;

import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

// there appear to be bug in this annotation, Minecraft still loads info from the mcmod.info file.
// however, it complains with a warning if the version is not supplied
@Mod(modid = ExampleMod.MODID, version=ExampleMod.VERSION)
public class ExampleMod
{
    public static final String MODID = "examplemod";
    public static final String VERSION = "1.0";
    private Logger logger;
    
    // Not sure why the example stores the instance as a static variable. The runtime
    // annotations will only initialize 1 instance of the Mod.
    @Instance(ExampleMod.MODID)
    public ExampleMod instance;
    
    // However, the proxy must be static or Minecraft complains
    @SidedProxy(
	    clientSide="com.example.examplemod.client.ExampleModClientProxy",
	    serverSide="com.example.examplemod.common.ExampleModCommonProxy")
    public static ExampleModCommonProxy proxy;
    
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
	
	if (instance != null)
	    logger.log(Level.INFO, "The static instance has been created!");
	else
	    logger.log(Level.WARN, "The static instance has NOT been created!");
    }
    
    /* demonstrate the other to client module events */
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
	// store this for use later
	logger = event.getModLog();
	
	logger.log(Level.INFO, "Example of pre-initialization event:" + event.getModState().toString());
	if (instance != null)
	    logger.log(Level.INFO, "The static instance has been created!");
	else
	    logger.log(Level.WARN, "The static instance has NOT been created!");
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
	logger.log(Level.INFO, "Example of post-initialization event: " + event.getModState().toString());
	
	if (instance != null)
	    logger.log(Level.INFO, "The static instance has been created!");
	else
	    logger.log(Level.WARN, "The static instance has NOT been created!");
    }
}
