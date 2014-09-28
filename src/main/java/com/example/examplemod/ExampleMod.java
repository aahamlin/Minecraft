package com.example.examplemod;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import com.example.examplemod.common.ExampleBlock;
import com.example.examplemod.common.ExampleModCommonProxy;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

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
    
    public static Block myBlock;
    
    
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
    	logger.log(Level.INFO, "Example of initialization event:" + event.getModState().toString());

    	// proves that we have an instance of our module
		if (instance != null)
		    logger.log(Level.INFO, "The static instance has been created!");
		else
		    logger.log(Level.WARN, "The static instance has NOT been created!");
		
		// create our example block; these could be placed into the ExampleMod.java file
		myBlock = new ExampleBlock();
		//myBlock.setBlockName("exampleBlock");
		myBlock.setHarvestLevel("pickaxe", 3);
		myBlock.setBlockTextureName("examplemod:exampleBlock");
		// this is the internal name, i.e. used in assets.examplemod.lang
		GameRegistry.registerBlock(myBlock, ExampleBlock.NAME);
    }
    
    /* demonstrate the other to client module events */
    /* read the cpw.mods.fml.common.Mod javadoc for events on the server side */
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
		// store this for use later
		logger = event.getModLog();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	logger.log(Level.INFO, "Example of post-initialization event: " + event.getModState().toString());
    }
}