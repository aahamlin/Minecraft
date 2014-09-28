package com.example.examplemod.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class ExampleBlock extends Block {

	// must match the assets lang entry
	public final static String NAME = "exampleBlock";
	
	public ExampleBlock() {
		super(Material.rock);
		setCreativeTab(CreativeTabs.tabBlock);
		setHardness(89.3F);
		setBlockName(NAME);
		setResistance(89.5F);
		setStepSound(soundTypeMetal);
	}

}
