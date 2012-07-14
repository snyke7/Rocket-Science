package net.minecraft.src;

import org.lwjgl.opengl.GL11;

import ic2.api.Ic2Recipes;
import ic2.common.Ic2Items;
import ic2.common.ItemIC2;
import ic2.common.StackUtil;
import ic2.common.TileEntityCompressor;
import ic2.common.TileEntityExtractor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import net.minecraft.client.Minecraft;
import java.util.*;
import rocketScience.*;


public class mod_ZZZMissile extends RocketScience{
	
	public static int height=1;
	public static Block booster;
	public static Block warhead;
	public static Block superconductor;
	public static Block RSmachine;
	public static Block RSgenerator;
	public static ItemRS parachute;
	public static ItemRS passengerModule;
	public static ItemRS boosterModule;
	public static ItemRS tntModule;
	public static ItemRS incendiaryModule;
	public static ItemRS nuclearModule;
	public static ItemRS rangefinderItem;
	public static ItemRS superconductorUncompressed;
	public static ItemRS cellDeuterium;
	public static ItemRS copperCoil;
	public static ItemRS copperCoils;
	public static ItemRS superCoil;
	public static ItemRS superCoils;
	public static ItemRS ohmicHeater;
	public static ItemRS neutralHeater;
	public static ItemRS rfHeater;
	public static ItemRS ionDrive;
	public static ItemRS passengerDepleted;
	public static ItemRS lithium;
	public static ItemRS lithiumCell;
	public static ItemRS lithium6Cell;
	public static ItemRS tritiumCell;
	public static ItemRS thermoModule;
	public static ItemVacuum vacuum;
	public static ItemArmorRS parachuteArmor;
	public static ItemHandPump handPump;
	public static ItemAutominerFinder finder;
	public static File optionsFile;
	public static final String saveFileVersion = "1.1.0";
	public static int boosterID = 201;
	public static int warheadID = 202;
	public static int superconductorID = 203;
	public static int machineID = 204;
	public static int generatorID = 205;
	public static int parachuteID = 20000;
	public static int passengerModuleID = 20001;
	public static int boosterModuleID = 20002;
	public static int tntModuleID = 20003;
	public static int incendiaryModuleID = 20004;
	public static int nuclearModuleID = 20005;
	public static int rangefinderItemID = 20006;
	public static int parachuteArmorID = 20007;
	public static int superconductorUncompressedID=20008;
	public static int deuteriumID=20009;
	public static int copperCoilID=20010;
	public static int copperCoilsID=20011;
	public static int superCoilID=20012;
	public static int superCoilsID=20013;
	public static int ohmicHeaterID=20014;
	public static int neutralHeaterID=20015;
	public static int rfHeaterID=20016;
	public static int vacuumID=20017;
	public static int handPumpID=20020;
	public static int ionDriveID=20021;
	public static int passengerDepletedID=20022;
	public static int finderID=20023;
	public static int lithiumID=20024;
	public static int lithiumCellID=20025;
	public static int lithium6CellID=20026;
	public static int tritiumCellID=20027;
	public static int thermoModuleID=20028;
	public static int guiIsotopeID=40;
	public static int guiFusionID=41;
	public static int guiAutoMinerID=42;
	public static int guiDefenseID=43;
	public static int guiOffenseID=44;
	public static int guiLaserID=45;
	public static int missileModelID;
	public static int warheadModelID;
	public static int machineModelID;
	
	boolean parachuteDeployed=false;
	
	static mod_ZZZMissile instance;
	
	public mod_ZZZMissile()
	{
		instance = this;
	}
	
	public static void init()
	{
		try
	    {
			/*ModLoaderMp.registerNetClientHandlerEntity(MissileBoosterEntity.class, 144);
			ModLoaderMp.registerNetClientHandlerEntity(MissileMinerBoosterEntity.class, 145);
			ModLoaderMp.registerNetClientHandlerEntity(MissilePassengerBoosterEntity.class, 146);
			ModLoaderMp.registerNetClientHandlerEntity(MissilePassengerWarheadEntity.class, 147);
			ModLoaderMp.registerNetClientHandlerEntity(MissileWarheadEntity.class, 148);
			ModLoaderMp.registerNetClientHandlerEntity(ParachuteEntity.class, 149);
			ModLoaderMp.registerNetClientHandlerEntity(RangefinderEntity.class, 150);*/
			
		  missileModelID = ModLoader.getUniqueBlockModelID(instance, true);
		  warheadModelID = ModLoader.getUniqueBlockModelID(instance, true);
		  machineModelID = ModLoader.getUniqueBlockModelID(instance, true);
	      optionsFile = new File(Minecraft.getMinecraftDir(), "/config/RocketScience.cfg");
	      func_22024_func_21238_readOptions();
	      
	      booster = new MissileBoosterBlock(boosterID, 0).setBlockName("Missile");
	      warhead = new MissileWarheadBlock(warheadID).setBlockName("If you have this block, it's a glitch.");
	      RSmachine = new BlockRSMachine(machineID).setBlockName("RS Machine");
	      RSgenerator = new BlockRSGenerator(generatorID).setBlockName("RS Generator");
	      superconductor = new BlockSuperconductor(superconductorID).setBlockName("Superconductor");
	      parachute=(ItemRS)new ItemRS(parachuteID,0).setItemName("Parachute");
	      passengerModule=(ItemRS)new ItemRS(passengerModuleID,1).setItemName("Passenger Module");
	      boosterModule=(ItemRS)new ItemRS(boosterModuleID,2).setItemName("Booster Module");
	      tntModule=(ItemRS)new ItemRS(tntModuleID,3).setItemName("TNT Warhead");
	      incendiaryModule=(ItemRS)new ItemRS(incendiaryModuleID,4).setItemName("Incendiary Warhead");
	      nuclearModule=(ItemRS)new ItemRS(nuclearModuleID,5).setItemName("Nuclear Warhead");
	      rangefinderItem=(ItemRS)new RangefinderItem(rangefinderItemID,6).setItemName("Laser Rangefinder");
	      parachuteArmor = (ItemArmorRS)new ItemArmorRS(parachuteArmorID, 7, EnumArmorMaterial.CLOTH, ModLoader.addArmor("parachute"), 1, 1000).setItemName("Parachute Pack");
	      superconductorUncompressed=(ItemRS)new ItemRS(superconductorUncompressedID,8).setItemName("SuperUncompressed");
	      cellDeuterium = (ItemRS)(new ItemRS(deuteriumID, 9)).setItemName("itemCellDeuterium");
	      copperCoil=(ItemRS)new ItemRS(copperCoilID,10).setItemName("CopperCoil");
	      copperCoils=(ItemRS)new ItemRS(copperCoilsID,11).setItemName("CopperCoils");
	      superCoil=(ItemRS)new ItemRS(superCoilID,12).setItemName("SuperCoil");
	      superCoils=(ItemRS)new ItemRS(superCoilsID,13).setItemName("SuperCoils");
	      ohmicHeater=(ItemRS)new ItemRS(ohmicHeaterID,14).setItemName("OhmicHeater");
	      neutralHeater=(ItemRS)new ItemRS(neutralHeaterID,15).setItemName("NeutralHeater");
	      rfHeater=(ItemRS)new ItemRS(rfHeaterID,16).setItemName("RFHeater");
	      vacuum=(ItemVacuum)new ItemVacuum(vacuumID,17).setItemName("Vacuum");
	      handPump=(ItemHandPump)new ItemHandPump(handPumpID,20).setItemName("HandPump");
	      ionDrive=(ItemRS)new ItemRS(ionDriveID,21).setItemName("IonDrive");
	      passengerDepleted=(ItemPassengerDepleted)new ItemPassengerDepleted(passengerDepletedID,22).setItemName("PassengerDepleted");
	      finder=(ItemAutominerFinder)new ItemAutominerFinder(finderID,23).setItemName("Finder");
	      lithium=(ItemRS)new ItemRS(lithiumID,24).setItemName("Lithium");
	      lithiumCell=(ItemRS)new ItemRS(lithiumCellID,25).setItemName("LithiumCell");
	      lithium6Cell=(ItemRS)new ItemRS(lithium6CellID,26).setItemName("Lithium6Cell");
	      tritiumCell=(ItemRS)new ItemRS(tritiumCellID,27).setItemName("TritiumCell");
	      thermoModule=(ItemRS)new ItemRS(thermoModuleID,5).setItemName("Thermonuclear Warhead");
	      
	      ModLoader.registerBlock(booster, MissileItem.class);
	      ModLoader.registerBlock(warhead, null);
	      ModLoader.registerBlock(superconductor);
	      ModLoader.registerBlock(RSmachine, ItemMachineRS.class);
	      ModLoader.registerBlock(RSgenerator);
	      ModLoader.registerTileEntity(MissileTileEntity.class, "Missile");
	      ModLoader.registerTileEntity(TileEntitySuperconductor.class, "Superconductor");
	      ModLoader.registerTileEntity(TileEntityIsotope.class, "Isotopic Separator");
	      ModLoader.registerTileEntity(TileEntityFusion.class, "Fusion Reactor");
	      ModLoader.registerTileEntity(TileEntityAutoMiner.class, "Autominer");
	      ModLoader.registerTileEntity(TileEntityDefense.class, "Missile Defense");
	      ModLoader.registerTileEntity(TileEntityOffense.class, "Missile Targeting");
	      ModLoader.registerTileEntity(TileEntityLaser.class,"Defense Laser");
	      ModLoader.registerTileEntity(TileEntityRadar.class,"Radar");
	      TileEntityIsotope.initRecipes();
	      
	      ModLoader.addName(boosterModule, "Booster Module");
	      ModLoader.addName(passengerModule, "Passenger Module");
	      ModLoader.addName(parachute, "Parachute");
	      ModLoader.addName(booster, "Missile");
	      ModLoader.addName(warhead, "If you have this block, it's a glitch.");
	      ModLoader.addName(tntModule, "TNT Warhead");
	      ModLoader.addName(incendiaryModule, "Incendiary Warhead");
	      ModLoader.addName(nuclearModule, "Nuclear Warhead");
	      ModLoader.addName(rangefinderItem, "Laser Rangefinder");
	      ModLoader.addName(parachuteArmor, "Parachute Pack");
	      ModLoader.addName(superconductor, "Superconductor");
	      ModLoader.addName(superconductorUncompressed, "Graphene-Gold Lattice");
	      ModLoader.addName(cellDeuterium, "Deuterium Cell");
	      ModLoader.addName(copperCoil, "Copper Loop");
	      ModLoader.addName(copperCoils, "Copper Coils");
	      ModLoader.addName(superCoil, "Superconductor Loop");
	      ModLoader.addName(superCoils, "Superconducting Coils");
	      ModLoader.addName(ohmicHeater, "Ohmic Heating System");
	      ModLoader.addName(neutralHeater, "Neutral-Beam Heating System");
	      ModLoader.addName(rfHeater, "RF Cyclotron Heating System");
	      ModLoader.addName(new ItemStack(booster, 1, 14), "Missile");
	      ModLoader.addName(new ItemStack(booster, 1, 15), "Reusable Passenger Rocket (half charge)");
	      ModLoader.addName(new ItemStack(booster, 1, 0), "Reusable Passenger Rocket");
	      ModLoader.addName(new ItemStack(booster, 1, 4), "Incendiary Missile");
	      ModLoader.addName(new ItemStack(booster, 1, 8), "Nuclear Missile");
	      ModLoader.addName(new ItemStack(booster, 1, 12), "Passenger Rocket");
	      ModLoader.addName(new ItemStack(booster, 1, 13), "Thermonuclear Missile");
	      ModLoader.addName(new ItemStack(RSmachine,1,0), "Isotopic Separator");
	      ModLoader.addName(new ItemStack(RSmachine,1,1), "Mobile Auto-Miner");
	      ModLoader.addName(new ItemStack(RSmachine,1,2), "Missile Defense System");
	      ModLoader.addName(new ItemStack(RSmachine,1,3), "Missile Targeting System");
	      ModLoader.addName(new ItemStack(RSmachine,1,4), "Missile Defense Laser");
	      ModLoader.addName(new ItemStack(RSmachine,1,5), "Radar");
	      ModLoader.addName(new ItemStack(RSgenerator,1,0), "Fusion Reactor");
	      ModLoader.addName(vacuum, "Wet/Dry Vac");
	      ModLoader.addName(handPump,"Hand Pump");
	      ModLoader.addName(ionDrive, "Ion Drive");
	      ModLoader.addName(passengerDepleted, "Discharged Passenger Rocket");
	      ModLoader.addName(finder, "Autominer Location Device");
	      ModLoader.addName(lithium,"Lithium");
	      ModLoader.addName(lithiumCell,"Lithium Cell");
	      ModLoader.addName(lithium6Cell,"Lithium-6 Cell");
	      ModLoader.addName(tritiumCell,"Tritium Cell");
	      ModLoader.addName(thermoModule, "Thermonuclear Warhead");
	      
	      ModLoader.addRecipe(new ItemStack(boosterModule, 2), new Object[] { "#X#", "#X#", "#X#", Character.valueOf('#'), Ic2Items.refinedIronIngot, Character.valueOf('X'), Ic2Items.coalfuelCell});
	      ModLoader.addRecipe(new ItemStack(boosterModule, 2), new Object[] { "#X#", "#X#", "#X#", Character.valueOf('#'), Ic2Items.refinedIronIngot, Character.valueOf('X'), Ic2Items.biofuelCell});
	      ModLoader.addRecipe(new ItemStack(parachute,1), new Object[] {"XXX", "O O", " O ", Character.valueOf('X'), Item.leather,Character.valueOf('O'), Item.silk});
	      ModLoader.addRecipe(new ItemStack(parachuteArmor,1), new Object[] {"L L", "LPL", "LLL", Character.valueOf('L'), Item.leather,Character.valueOf('P'), parachute});
	      ModLoader.addRecipe(new ItemStack(passengerModule, 1), new Object[] {"P", "M", Character.valueOf('P'), parachute, Character.valueOf('M'), Item.minecartEmpty});
	      ModLoader.addRecipe(new ItemStack(tntModule,1), new Object[] {" I ", "ITI", Character.valueOf('I'), Ic2Items.refinedIronIngot, Character.valueOf('T'),Block.tnt});
	      ModLoader.addRecipe(new ItemStack(incendiaryModule,1), new Object[] {" I ", "ITI", Character.valueOf('I'), Ic2Items.refinedIronIngot, Character.valueOf('T'),Ic2Items.lavaCell});
	      ModLoader.addRecipe(new ItemStack(nuclearModule,1), new Object[] {" I ", "ITI", Character.valueOf('I'), Ic2Items.refinedIronIngot, Character.valueOf('T'),Ic2Items.nuke});
	      ModLoader.addRecipe(new ItemStack(booster, 1, 12), new Object[] {"P", "M", Character.valueOf('P'), passengerModule, Character.valueOf('M'), boosterModule});
	      ModLoader.addRecipe(new ItemStack(booster, 1, 8), new Object[] {"P", "M", Character.valueOf('P'), nuclearModule, Character.valueOf('M'), boosterModule});
	      ModLoader.addRecipe(new ItemStack(booster, 1, 4), new Object[] {"P", "M", Character.valueOf('P'), incendiaryModule, Character.valueOf('M'), boosterModule});
	      ModLoader.addRecipe(new ItemStack(booster, 1, 14), new Object[] {"P", "M", Character.valueOf('P'), tntModule, Character.valueOf('M'), boosterModule});
	      ModLoader.addRecipe(new ItemStack(superconductorUncompressed), new Object[] {" C ", " D ", " G ", Character.valueOf('C'), Ic2Items.carbonPlate, Character.valueOf('D'), Item.redstone, Character.valueOf('G'), Item.ingotGold});
	      ModLoader.addRecipe(new ItemStack(RSmachine,1,0), new Object[] {"ICI", "RER", "IAI", Character.valueOf('E'), Ic2Items.extractor, Character.valueOf('I'), Ic2Items.refinedIronIngot, Character.valueOf('R'), Item.redstone, Character.valueOf('A'),Ic2Items.advancedMachine, Character.valueOf('C'),Ic2Items.advancedCircuit});
	      ModLoader.addRecipe(new ItemStack(RSgenerator, 1, 0), new Object[] {"CCC","CAC","CCC", Character.valueOf('C'), Ic2Items.reactorChamber, Character.valueOf('A'), Ic2Items.advancedMachine});
	      ModLoader.addRecipe(new ItemStack(copperCoil,1),new Object[] {"CCC", "C C", "CCC", Character.valueOf('C'), Ic2Items.copperIngot});
	      ModLoader.addRecipe(new ItemStack(copperCoils,1),new Object[] {"CCC", "C C", "CCC", Character.valueOf('C'), copperCoil});
	      ModLoader.addRecipe(new ItemStack(superCoil,1),new Object[] {"CCC", "C C", "CCC", Character.valueOf('C'), superconductor});
	      ModLoader.addRecipe(new ItemStack(superCoils,1),new Object[] {"CCC", "C C", "CCC", Character.valueOf('C'), superCoil});
	      ModLoader.addRecipe(new ItemStack(ohmicHeater,1),new Object[] {"WWW", "C W", "WWW", Character.valueOf('W'), Ic2Items.insulatedCopperCableItem, Character.valueOf('C'), Ic2Items.electronicCircuit});
	      ModLoader.addRecipe(new ItemStack(rfHeater,1),new Object[] {"F", "A", "M", Character.valueOf('M'), Ic2Items.machine, Character.valueOf('A'), Ic2Items.advancedCircuit, Character.valueOf('F'), Ic2Items.frequencyTransmitter});
	      ModLoader.addRecipe(new ItemStack(neutralHeater,1),new Object[] {"AMW"," I ", Character.valueOf('I'), Ic2Items.refinedIronIngot, Character.valueOf('M'), Ic2Items.machine, Character.valueOf('A'), Ic2Items.advancedCircuit,Character.valueOf('W'), Ic2Items.insulatedCopperCableItem});
	      ModLoader.addRecipe(new ItemStack(rangefinderItem,1),new Object[] {"III","ICD","III", Character.valueOf('I'), Ic2Items.refinedIronIngot, Character.valueOf('C'), Ic2Items.electronicCircuit, Character.valueOf('D'), Item.diamond});
	      ModLoader.addRecipe(new ItemStack(vacuum), new Object[] {"R","P","B", Character.valueOf('R'), Ic2Items.rubber, Character.valueOf('B'), Ic2Items.reBattery, Character.valueOf('P'), Ic2Items.pump});
	      ModLoader.addRecipe(new ItemStack(handPump), new Object[] {"RB"," C", Character.valueOf('C'), Ic2Items.cell, Character.valueOf('B'), Ic2Items.bronzeIngot, Character.valueOf('R'), Ic2Items.rubber});
	      ModLoader.addRecipe(new ItemStack(ionDrive), new Object[] {"ACA","ARA","AGA",Character.valueOf('A'),Ic2Items.advancedAlloy,Character.valueOf('C'),new ItemStack(Ic2Items.energyCrystal.getItem(),1,26),Character.valueOf('R'),Ic2Items.advancedCircuit,Character.valueOf('G'),Block.glowStone});
	      ModLoader.addRecipe(new ItemStack(ionDrive), new Object[] {"ACA","ARA","AGA",Character.valueOf('A'),Ic2Items.advancedAlloy,Character.valueOf('C'),new ItemStack(Ic2Items.energyCrystal.getItem(),1,27),Character.valueOf('R'),Ic2Items.advancedCircuit,Character.valueOf('G'),Block.glowStone});
	      ModLoader.addRecipe(new ItemStack(passengerDepleted,1,10001), new Object[] {"P","I",Character.valueOf('P'),passengerModule,Character.valueOf('I'),ionDrive});
	      ModLoader.addRecipe(new ItemStack(RSmachine,1,1), new Object[] {"CTC","AMA","I I",Character.valueOf('C'),Ic2Items.advancedCircuit,Character.valueOf('T'),Block.chest,Character.valueOf('A'),Ic2Items.advancedMachine,Character.valueOf('M'),Ic2Items.miner,Character.valueOf('I'),ionDrive});
	      ModLoader.addRecipe(new ItemStack(thermoModule), new Object[] {" T ","DND"," T ",Character.valueOf('T'), tritiumCell, Character.valueOf('D'), cellDeuterium, Character.valueOf('N'), nuclearModule});
	      ModLoader.addRecipe(new ItemStack(thermoModule), new Object[] {" D ","TNT"," D ",Character.valueOf('T'), tritiumCell, Character.valueOf('D'), cellDeuterium, Character.valueOf('N'), nuclearModule});
	      ModLoader.addRecipe(new ItemStack(booster,1,13), new Object[] {"P","M",Character.valueOf('P'),thermoModule,Character.valueOf('M'),boosterModule});
	      ModLoader.addRecipe(new ItemStack(RSmachine,1,2), new Object[] {"GAG","RMR","GCG", Character.valueOf('G'),Block.thinGlass, Character.valueOf('A'), Ic2Items.advancedCircuit, Character.valueOf('R'), Item.redstone, Character.valueOf('M'), Ic2Items.machine, Character.valueOf('C'), Ic2Items.electronicCircuit});
	      ModLoader.addRecipe(new ItemStack(RSmachine,1,3), new Object[] {"GCG","RMR","GAG", Character.valueOf('G'),Block.thinGlass, Character.valueOf('A'), Ic2Items.advancedCircuit, Character.valueOf('R'), Item.redstone, Character.valueOf('M'), Ic2Items.machine, Character.valueOf('C'), Ic2Items.electronicCircuit});
	      ModLoader.addRecipe(new ItemStack(RSmachine,1,4), new Object[] {" E ","GCG","AMA", Character.valueOf('E'),new ItemStack(Ic2Items.energyCrystal.getItem(),1,26),Character.valueOf('G'),Item.lightStoneDust, Character.valueOf('C'), Ic2Items.advancedCircuit, Character.valueOf('A'), Ic2Items.advancedAlloy, Character.valueOf('M'), Ic2Items.advancedMachine});
	      ModLoader.addRecipe(new ItemStack(RSmachine,1,5), new Object[] {"IFI"," I "," M ", Character.valueOf('I'),Ic2Items.refinedIronIngot,Character.valueOf('F'),Ic2Items.frequencyTransmitter,Character.valueOf('M'), Ic2Items.machine});
	      ModLoader.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.copperIngot, 64), new Object[] {copperCoils});
	      ModLoader.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.copperIngot, 8), new Object[] {copperCoil});
	      ModLoader.addShapelessRecipe(new ItemStack(superconductor,64), new Object[] {superCoils});
	      ModLoader.addShapelessRecipe(new ItemStack(superconductor,8), new Object[] {superCoil});
	      ModLoader.addShapelessRecipe(new ItemStack(finder,1), new Object[] {Ic2Items.frequencyTransmitter, new ItemStack(Item.dyePowder,1,1)});
	      ModLoader.addShapelessRecipe(new ItemStack(lithiumCell,1),new Object[] {Ic2Items.cell, lithium});
	      
	      ModLoader.registerEntityID(MissileBoosterEntity.class, "Missile", ModLoader.getUniqueEntityId());
	      ModLoader.registerEntityID(MissileWarheadEntity.class, "Warhead", ModLoader.getUniqueEntityId());
	      ModLoader.registerEntityID(MissilePassengerBoosterEntity.class, "Passenger Missile", ModLoader.getUniqueEntityId());
	      ModLoader.registerEntityID(MissilePassengerWarheadEntity.class, "Passenger Warhead", ModLoader.getUniqueEntityId());
	      ModLoader.registerEntityID(RangefinderEntity.class, "Rangefinder", ModLoader.getUniqueEntityId());
	      ModLoader.registerEntityID(MissileMinerBoosterEntity.class, "Miner", ModLoader.getUniqueEntityId());
	      ModLoader.registerEntityID(EntityDefenseLaser.class,"Defense Laser", ModLoader.getUniqueEntityId());
	      
	      Ic2Recipes.addCompressorRecipe(new ItemStack(superconductorUncompressed), new ItemStack(superconductor));
	      Ic2Recipes.addExtractorRecipe(new ItemStack(Item.clay), new ItemStack(lithium));
	      
	      ModLoader.setInGameHook(instance,true,false);
	    }
	    catch (Exception exception)
	    {
	      System.out.println("Rocket Science: Failed to initialize");
	      exception.printStackTrace();
	    }
	}
	
	public String Version()
	  {
	    return "1.1.0";
	  }
	
	public static void func_22022_g21240_saveOptions()
	  {
	    try
	    {
	      optionsFile.createNewFile();
	      PrintWriter printwriter = new PrintWriter(new FileWriter(optionsFile));
	      printwriter.println("saveFileVersion:1.1.0");
	      printwriter.println("boosterBlockID:" + boosterID);
	      printwriter.println("warheadBlockID:" + warheadID);
	      printwriter.println("machineID:" + machineID);
	      printwriter.println("generatorID:"+generatorID);
	      printwriter.println("boosterItemID:" + boosterModuleID);
	      printwriter.println("passengerItemID:" + passengerModuleID);
	      printwriter.println("tntWarheadItemID:" + tntModuleID);
	      printwriter.println("incendiaryWarheadItemID:" + incendiaryModuleID);
	      printwriter.println("nuclearWarheadItemID:" + nuclearModuleID);
	      printwriter.println("parachuteItemID:" + parachuteID);
	      printwriter.println("rangefinderItemID:"+rangefinderItemID);
	      printwriter.println("parachuteArmorID:"+parachuteArmorID);
	      printwriter.println("superconductorID:"+superconductorID);
	      printwriter.println("superconductorUncompressedID:"+superconductorUncompressedID);
	      printwriter.println("cellDeuteriumID:"+deuteriumID);
	      printwriter.println("copperCoilID:"+copperCoilID);
	      printwriter.println("copperCoilsID:"+copperCoilsID);
	      printwriter.println("superCoilID:"+superCoilID);
	      printwriter.println("superCoilsID:"+superCoilsID);
	      printwriter.println("ohmicHeaterID:"+ohmicHeaterID);
	      printwriter.println("rfHeaterID:"+rfHeaterID);
	      printwriter.println("neutralHeaterID:"+neutralHeaterID);
	      printwriter.println("vacuumID:"+vacuumID);
	      printwriter.println("handPumpID:"+handPumpID);
	      printwriter.println("ionDriveID:"+ionDriveID);
	      printwriter.println("passengerDepletedID:"+passengerDepletedID);
	      printwriter.println("finderID:"+finderID);
	      printwriter.println("lithiumID:"+lithiumID);
	      printwriter.println("lithiumCellID:"+lithiumCellID);
	      printwriter.println("lithium6CellID:"+lithium6CellID);
	      printwriter.println("tritiumCellID:"+tritiumCellID);
	      printwriter.println("thermoModuleID:"+thermoModuleID);
	      printwriter.close();
	    }
	    catch (Exception exception)
	    {
	      System.out.println("Failed to save options");
	      exception.printStackTrace();
	    }
	  }

	  public static void func_22024_func_21238_readOptions()
	  {
	    try
	    {
	      if (!optionsFile.exists())
	      {
	        func_22022_g21240_saveOptions();
	      }
	      BufferedReader bufferedreader = new BufferedReader(new FileReader(optionsFile));
	      //String s = "";
	      /*if (!func_22023_func_21239_checkVersion(bufferedreader.readLine()))
	      {
	        func_22022_g21240_saveOptions();
	      }*/
	      String s1;
	      int num;
	      while ((s1 = bufferedreader.readLine()) != null)
	      {
	        String[] as = s1.split(":");
	        if (as[0].equals("boosterBlockID"))
	        {
	        	num=Integer.parseInt(as[1]);
	        	if(Block.blocksList[num]!=null)
	        		ModLoader.getLogger().fine("Booster block ID ("+num+") conflicts! This may cause the mod to fail.");
	        	boosterID = num;
	        }
	        else if (as[0].equals("warheadBlockID"))
	        {
	        	num=Integer.parseInt(as[1]);
	        	if(Block.blocksList[num]!=null)
	        		ModLoader.getLogger().fine("Warhead block ID ("+num+") conflicts! This may cause the mod to fail.");
	          warheadID = Integer.parseInt(as[1]);
	        }
	        else if (as[0].equals("generatorID"))
	        {
	        	num=Integer.parseInt(as[1]);
	        	if(Block.blocksList[num]!=null)
	        		ModLoader.getLogger().fine("Fusion reactor block ID ("+num+") conflicts! This may cause the mod to fail.");
	          generatorID = Integer.parseInt(as[1]);
	        }
	        else if (as[0].equals("machineID"))
	        {
	        	num=Integer.parseInt(as[1]);
	        	if(Block.blocksList[num]!=null)
	        		ModLoader.getLogger().fine("Machine block ID ("+num+") conflicts! This may cause the mod to fail.");
	          machineID = Integer.parseInt(as[1]);
	        }
	        else if (as[0].equals("parachuteItemID"))
	        {
	        	num=Integer.parseInt(as[1]);
	        	if(Item.itemsList[num]!=null)
	        		ModLoader.getLogger().fine("Parachute item ID ("+num+") conflicts! This may cause the mod to fail.");
		      parachuteID = Integer.parseInt(as[1]);
	        }
	        else if (as[0].equals("boosterItemID"))
	        {
	        	num=Integer.parseInt(as[1]);
	        	if(Item.itemsList[num]!=null)
	        		ModLoader.getLogger().fine("Booster item ID ("+num+") conflicts! This may cause the mod to fail.");
		      boosterModuleID = Integer.parseInt(as[1]);
	        }
	        else if (as[0].equals("passengerItemID"))
	        {
	        	num=Integer.parseInt(as[1]);
	        	if(Item.itemsList[num]!=null)
	        		ModLoader.getLogger().fine("Passenger module item ID ("+num+") conflicts! This may cause the mod to fail.");
		      passengerModuleID = Integer.parseInt(as[1]);
	        }
	        else if (as[0].equals("tntWarheadItemID"))
	        {
	        	num=Integer.parseInt(as[1]);
	        	if(Item.itemsList[num]!=null)
	        		ModLoader.getLogger().fine("TNT warhead item ID ("+num+") conflicts! This may cause the mod to fail.");
		      tntModuleID = Integer.parseInt(as[1]);
	        }
	        else if (as[0].equals("incendiaryWarheadItemID"))
	        {
	        	num=Integer.parseInt(as[1]);
	        	if(Item.itemsList[num]!=null)
	        		ModLoader.getLogger().fine("Incendiary warhead item ID ("+num+") conflicts! This may cause the mod to fail.");
		      incendiaryModuleID = Integer.parseInt(as[1]);
	        }
	        else if (as[0].equals("nuclearWarheadItemID"))
	        {
	        	num=Integer.parseInt(as[1]);
	        	if(Item.itemsList[num]!=null)
	        		ModLoader.getLogger().fine("Nuclear item ID ("+num+") conflicts! This may cause the mod to fail.");
		      nuclearModuleID = Integer.parseInt(as[1]);
	        }
	        else if (as[0].equals("rangefinderItemID"))
	        {
	        	num=Integer.parseInt(as[1]);
	        	if(Item.itemsList[num]!=null)
	        		ModLoader.getLogger().fine("Rangefinder item ID ("+num+") conflicts! This may cause the mod to fail.");
		      rangefinderItemID = Integer.parseInt(as[1]);
	        }
	        else if (as[0].equals("parachuteArmorID"))
	        {
	        	num=Integer.parseInt(as[1]);
	        	if(Item.itemsList[num]!=null)
	        		ModLoader.getLogger().fine("Parachute armor item ID ("+num+") conflicts! This may cause the mod to fail.");
		      parachuteArmorID = Integer.parseInt(as[1]);
	        }
	        else if (as[0].equals("superconductorID"))
	        {
	        	num=Integer.parseInt(as[1]);
	        	if(Block.blocksList[num]!=null)
	        		ModLoader.getLogger().fine("Superconductor block ID ("+num+") conflicts! This may cause the mod to fail.");
		      superconductorID = Integer.parseInt(as[1]);
	        }
	        else if (as[0].equals("superconductorUncompressedID"))
	        {
	        	num=Integer.parseInt(as[1]);
	        	if(Item.itemsList[num]!=null)
	        		ModLoader.getLogger().fine("Superconductor item ID ("+num+") conflicts! This may cause the mod to fail.");
		      superconductorUncompressedID = Integer.parseInt(as[1]);
	        }
	        else if (as[0].equals("cellDeuteriumID"))
	        {
	        	num=Integer.parseInt(as[1]);
	        	if(Item.itemsList[num]!=null)
	        		ModLoader.getLogger().fine("Deuterium item ID ("+num+") conflicts! This may cause the mod to fail.");
		      deuteriumID = Integer.parseInt(as[1]);
	        }
	        else if (as[0].equals("copperCoilID"))
	        {
	        	num=Integer.parseInt(as[1]);
	        	if(Item.itemsList[num]!=null)
	        		ModLoader.getLogger().fine("Copper coil item ID ("+num+") conflicts! This may cause the mod to fail.");
		      copperCoilID = Integer.parseInt(as[1]);
	        }
	        else if (as[0].equals("copperCoilsID"))
	        {
	        	num=Integer.parseInt(as[1]);
	        	if(Item.itemsList[num]!=null)
	        		ModLoader.getLogger().fine("Copper coils item ID ("+num+") conflicts! This may cause the mod to fail.");
		      copperCoilsID = Integer.parseInt(as[1]);
	        }
	        else if (as[0].equals("superCoilID"))
	        {
	        	num=Integer.parseInt(as[1]);
	        	if(Item.itemsList[num]!=null)
	        		ModLoader.getLogger().fine("Superconducting coil item ID ("+num+") conflicts! This may cause the mod to fail.");
		      superCoilID = Integer.parseInt(as[1]);
	        }
	        else if (as[0].equals("superCoilsID"))
	        {
	        	num=Integer.parseInt(as[1]);
	        	if(Item.itemsList[num]!=null)
	        		ModLoader.getLogger().fine("Superconducting coils item ID ("+num+") conflicts! This may cause the mod to fail.");
		      superCoilsID = Integer.parseInt(as[1]);
	        }
	        else if (as[0].equals("ohmicHeaterID"))
	        {
	        	num=Integer.parseInt(as[1]);
	        	if(Item.itemsList[num]!=null)
	        		ModLoader.getLogger().fine("Ohmic heater item ID ("+num+") conflicts! This may cause the mod to fail.");
		      ohmicHeaterID = Integer.parseInt(as[1]);
	        }
	        else if (as[0].equals("rfHeaterID"))
	        {
	        	num=Integer.parseInt(as[1]);
	        	if(Item.itemsList[num]!=null)
	        		ModLoader.getLogger().fine("RF heater item ID ("+num+") conflicts! This may cause the mod to fail.");
		      rfHeaterID = Integer.parseInt(as[1]);
	        }
	        else if (as[0].equals("neutralHeaterID"))
	        {
	        	num=Integer.parseInt(as[1]);
	        	if(Item.itemsList[num]!=null)
	        		ModLoader.getLogger().fine("Neutral heater item ID ("+num+") conflicts! This may cause the mod to fail.");
		      neutralHeaterID = Integer.parseInt(as[1]);
	        }
	        else if (as[0].equals("vacuumID"))
	        {
	        	num=Integer.parseInt(as[1]);
	        	if(Item.itemsList[num]!=null)
	        		ModLoader.getLogger().fine("Vacuum item ID ("+num+") conflicts! This may cause the mod to fail.");
		      vacuumID = Integer.parseInt(as[1]);
	        }
	        else if (as[0].equals("handPumpID"))
	        {
	        	num=Integer.parseInt(as[1]);
	        	if(Item.itemsList[num]!=null)
	        		ModLoader.getLogger().fine("Hand pump item ID ("+num+") conflicts! This may cause the mod to fail.");
		      handPumpID = Integer.parseInt(as[1]);
	        }
	        else if (as[0].equals("ionDriveID"))
	        {
	        	num=Integer.parseInt(as[1]);
	        	if(Item.itemsList[num]!=null)
	        		ModLoader.getLogger().fine("Ion drive item ID ("+num+") conflicts! This may cause the mod to fail.");
		      ionDriveID = Integer.parseInt(as[1]);
	        }
	        else if (as[0].equals("passengerDepletedID"))
	        {
	        	num=Integer.parseInt(as[1]);
	        	if(Item.itemsList[num]!=null)
	        		ModLoader.getLogger().fine("Depleted passenger rocket item ID ("+num+") conflicts! This may cause the mod to fail.");
		      passengerDepletedID = Integer.parseInt(as[1]);
	        }
	        else if (as[0].equals("finderID"))
	        {
	        	num=Integer.parseInt(as[1]);
	        	if(Item.itemsList[num]!=null)
	        		ModLoader.getLogger().fine("Autominer finder item ID ("+num+") conflicts! This may cause the mod to fail.");
		      finderID = Integer.parseInt(as[1]);
	        }
	        else if (as[0].equals("lithiumID"))
	        {
	        	num=Integer.parseInt(as[1]);
	        	if(Item.itemsList[num]!=null)
	        		ModLoader.getLogger().fine("Lithium item ID ("+num+") conflicts! This may cause the mod to fail.");
		      lithiumID = Integer.parseInt(as[1]);
	        }
	        else if (as[0].equals("lithiumCellID"))
	        {
	        	num=Integer.parseInt(as[1]);
	        	if(Item.itemsList[num]!=null)
	        		ModLoader.getLogger().fine("Lithium cell item ID ("+num+") conflicts! This may cause the mod to fail.");
		      lithiumCellID = Integer.parseInt(as[1]);
	        }
	        else if (as[0].equals("lithium6CellID"))
	        {
	        	num=Integer.parseInt(as[1]);
	        	if(Item.itemsList[num]!=null)
	        		ModLoader.getLogger().fine("Lithium-6 cell item ID ("+num+") conflicts! This may cause the mod to fail.");
		      lithium6CellID = Integer.parseInt(as[1]);
	        }
	        else if (as[0].equals("tritiumCellID"))
	        {
	        	num=Integer.parseInt(as[1]);
	        	if(Item.itemsList[num]!=null)
	        		ModLoader.getLogger().fine("Tritium cell item ID ("+num+") conflicts! This may cause the mod to fail.");
		      tritiumCellID = Integer.parseInt(as[1]);
	        }
	        else if (as[0].equals("thermoModuleID"))
	        {
	        	num=Integer.parseInt(as[1]);
	        	if(Item.itemsList[num]!=null)
	        		ModLoader.getLogger().fine("Thermonuclear warhead item ID ("+num+") conflicts! This may cause the mod to fail.");
		      thermoModuleID = Integer.parseInt(as[1]);
	        }
	      }
	      bufferedreader.close();
	    }
	    catch (Exception exception)
	    {
	      System.out.println("Rocket Science: Failed to load options");
	      exception.printStackTrace();
	    }
	  }

	  public static boolean func_22023_func_21239_checkVersion(String s)
	  {
	    String[] as = s.split(":");
	    return (as[0].equals("saveFileVersion")) && (as[1].equals("1.2.5"));
	  }
	  
	  public void addRenderer(Map map)
	  {
			 map.put(MissileBoosterEntity.class, new MissileRender(1));
			 map.put(MissileWarheadEntity.class, new MissileWarheadRender(1));
			 map.put(MissilePassengerBoosterEntity.class, new MissilePassengerRender(1));
			 map.put(MissilePassengerWarheadEntity.class, new MissilePassengerWarheadRender(1));
			 map.put(ParachuteEntity.class, new ParachuteRender(1));
			 map.put(MissileMinerBoosterEntity.class, new MissileMinerBoosterRender(1));
			 map.put(EntityDefenseLaser.class, new RenderDefenseLaser());
	  }
	  
	  private boolean RenderMissileInWorld(RenderBlocks renderer, IBlockAccess world, int x, int y, int z, Block block) {
		   block.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 1.0F, .9375F);
		   renderer.renderStandardBlock(block, x, y, z);
		   block.setBlockBounds(0.4375F, 0.75F, 0.0625F, 0.5625F, 0F, 0F);
		   renderer.renderStandardBlock(block, x, y, z);
		   block.setBlockBounds(0.4375F, 0.75F, 1.0F, 0.5625F, 0F, 0.9375F);
		   renderer.renderStandardBlock(block, x, y, z);
		   block.setBlockBounds(1.0F, 0.75F, 0.4375F, 0.9375F, 0F, 0.5625F);
		   renderer.renderStandardBlock(block, x, y, z);
		   block.setBlockBounds(.0625F, 0.75F, 0.4375F, 0F, 0F, 0.5625F);
		   renderer.renderStandardBlock(block, x, y, z);
		   
		   block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		   return true;
		  }
	  
	  private boolean RenderWarheadInWorld(RenderBlocks renderer, IBlockAccess world, int x, int y, int z, Block block) {
		   if(world.getBlockMetadata(x,y,z)!=12&&world.getBlockMetadata(x,y,z)!=0&&world.getBlockMetadata(x,y,z)!=15)
		   {
			   block.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.7F, .9F);
			   renderer.renderStandardBlock(block, x, y, z);
			   block.setBlockBounds(0.2F, 0.7F, 0.2F, 0.8F, 0.9F, 0.8F);
			   renderer.renderStandardBlock(block, x, y, z);
			   block.setBlockBounds(0.3F, 0.9F, 0.3F, 0.7F, 1F, 0.7F);
			   renderer.renderStandardBlock(block, x, y, z);
		   }
		   else
		   {
			   block.setBlockBounds(0.0F, 0.0F, 0F, 1F, 0.1F, 1F);
			   renderer.renderStandardBlock(block, x, y, z);
			   block.setBlockBounds(0F, 0.1F, 0F, 1F, 1F, .1F);
			   renderer.renderStandardBlock(block, x, y, z);
			   block.setBlockBounds(0F, 0.1F, 0.9F, 1F, 1F, 1F);
			   renderer.renderStandardBlock(block, x, y, z);
			   block.setBlockBounds(0F, 0.1F, 0.1F, 0.1F, 1F, 0.9F);
			   renderer.renderStandardBlock(block, x, y, z);
			   block.setBlockBounds(0.9F, 0.1F, 0.1F, 1F, 1F, .9F);
			   renderer.renderStandardBlock(block, x, y, z);
		   }
		   block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		   return true;
		  }
	  
	  private boolean RenderMachineInWorld(RenderBlocks renderer, IBlockAccess world, int x, int y, int z, Block block) {
		  if(world.getBlockMetadata(x,y,z)<4)
		   {
			  block.setBlockBounds(0.0F, 0.0F, 0.0F, 1F, 1F, 1F);
			  renderer.renderStandardBlock(block,x,y,z);
		   }
		  else if(world.getBlockMetadata(x, y, z)==4)
		  {
			  block.setBlockBounds(0,0,0,1,0.5f,1);
			  renderer.renderStandardBlock(block, x, y, z);
			  block.setBlockBounds(0.25f,0.5f,0.25f,0.75f,0.625f,0.75f);
			  renderer.renderStandardBlock(block, x, y, z);
			  block.setBlockBounds(0.625f,0.625f,0.625f,0.375f,1,0.375f);
			  renderer.renderStandardBlock(block, x, y, z);
		  }
		  else if(world.getBlockMetadata(x, y, z)==5)
		  {
			  block.setBlockBounds(0,0,0,1,0.125f,1);
			  renderer.renderStandardBlock(block, x, y, z);
			  block.setBlockBounds(0.625f,.125f,0.625f,0.375f,0.375f,0.375f);
			  renderer.renderStandardBlock(block, x, y, z);
			  block.setBlockBounds(0,0.375f,0,1,0.5f,1);
			  renderer.renderStandardBlock(block, x, y, z);
			  block.setBlockBounds(0,0.5f,0.875f,1,0.875f,1);
			  renderer.renderStandardBlock(block, x, y, z);
			  block.setBlockBounds(0,0.5f,0,1,0.875f,0.125f);
			  renderer.renderStandardBlock(block, x, y, z);
			  block.setBlockBounds(0,0.5f,0.875f,1,0.875f,1);
			  renderer.renderStandardBlock(block, x, y, z);
			  block.setBlockBounds(0,0.5f,0.125f,0.125f,0.875f,0.875f);
			  renderer.renderStandardBlock(block, x, y, z);
			  block.setBlockBounds(0.875f,0.5f,0.125f,1,0.875f,0.875f);
			  renderer.renderStandardBlock(block, x, y, z);
			  block.setBlockBounds(0.375f,0.5f,0.375f,0.625f,1,0.625f);
			  renderer.renderStandardBlock(block, x, y, z);
		  }
		  return true;
	  }
	  
	  private void RenderMachineInInv(RenderBlocks renderer, Block block, int metadata)
  	  {
		  Tessellator tesselator = Tessellator.instance;
		  block.setBlockBounds(0,0,0,1,1,1);
		  GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
	      tesselator.startDrawingQuads();
	      tesselator.setNormal(0.0F, -1.0F, 0.0F);
	      renderer.renderBottomFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(0,metadata));
	      tesselator.draw();
	      tesselator.startDrawingQuads();
	      tesselator.setNormal(0.0F, 1.0F, 0.0F);
	      renderer.renderTopFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(1,metadata));
	      tesselator.draw();
	      tesselator.startDrawingQuads();
	      tesselator.setNormal(0.0F, 0.0F, -1.0F);
	      renderer.renderEastFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(2,metadata));
	      tesselator.draw();
	      tesselator.startDrawingQuads();
	      tesselator.setNormal(0.0F, 0.0F, 1.0F);
	      renderer.renderWestFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(3,metadata));
	      tesselator.draw();
	      tesselator.startDrawingQuads();
	      tesselator.setNormal(-1.0F, 0.0F, 0.0F);
	      renderer.renderNorthFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(4,metadata));
	      tesselator.draw();
	      tesselator.startDrawingQuads();
	      tesselator.setNormal(1.0F, 0.0F, 0.0F);
	      renderer.renderSouthFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(5,metadata));
	      tesselator.draw();
	      GL11.glTranslatef(0.5F, 0.5F, 0.5F);
  	  }

	  	  private void RenderMissileInInv(RenderBlocks renderer, Block block, int metadata)
	  	  {
	  		Tessellator tesselator = Tessellator.instance;
	  		int sideTex=0;
	  		int topTex=0;
		    for (int i = 0; i < 6; i++) {
		    	//Get bounds for each rectangle
			  if (i == 0) block.setBlockBounds(0.4F, 0.95F, 0.4F, 0.6F, 1.0F, 0.6F);
		      else if (i == 1) block.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.95F, 0.7F);
		      else if (i == 2) block.setBlockBounds(0.4F, 0F, 0.1F, 0.6F, 0.4F, 0.3F);
		      else if (i == 3) block.setBlockBounds(0.4F, 0F, 0.7F, 0.6F, 0.4F, 0.9F);
		      else if (i == 4) block.setBlockBounds(0.1F, 0F, 0.4F, 0.3F, 0.4F, 0.6F);
		      else if (i == 5) block.setBlockBounds(0.7F, 0F, 0.4F, 0.9F, 0.4F, 0.6F);
		      	//Get textures
		      if(i==0&&metadata!=0&&metadata!=15)
		      {
		    	  topTex=15;
		    	  sideTex=block.getBlockTextureFromSideAndMetadata(1,metadata);
		      }
		      else if(i==0)
		      {
		    	  topTex=18;
		    	  sideTex=block.getBlockTextureFromSideAndMetadata(1,metadata);
		      }
		      else if (i==1&&metadata!=0&&metadata!=15)
		      {
		    	  sideTex=block.getBlockTextureFromSideAndMetadata(1, 1);
		    	  topTex=block.getBlockTextureFromSideAndMetadata(1, 1);
		      }
		      else if (i==1)
		      {
		    	  sideTex=block.getBlockTextureFromSideAndMetadata(1, metadata);
		    	  topTex=block.getBlockTextureFromSideAndMetadata(1, metadata);
		      }
		      else if(metadata!=0&&metadata!=15)
		      {
		    	  sideTex=topTex=15;
		      }
		      else
		      {
		    	  sideTex=topTex=18;
		      }
		      GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		      tesselator.startDrawingQuads();
		      tesselator.setNormal(0.0F, -1.0F, 0.0F);
		      renderer.renderBottomFace(block, 0.0D, 0.0D, 0.0D, sideTex);
		      tesselator.draw();
		      tesselator.startDrawingQuads();
		      tesselator.setNormal(0.0F, 1.0F, 0.0F);
		      renderer.renderTopFace(block, 0.0D, 0.0D, 0.0D, topTex);
		      tesselator.draw();
		      tesselator.startDrawingQuads();
		      tesselator.setNormal(0.0F, 0.0F, -1.0F);
		      renderer.renderEastFace(block, 0.0D, 0.0D, 0.0D, sideTex);
		      tesselator.draw();
		      tesselator.startDrawingQuads();
		      tesselator.setNormal(0.0F, 0.0F, 1.0F);
		      renderer.renderWestFace(block, 0.0D, 0.0D, 0.0D, sideTex);
		      tesselator.draw();
		      tesselator.startDrawingQuads();
		      tesselator.setNormal(-1.0F, 0.0F, 0.0F);
		      renderer.renderNorthFace(block, 0.0D, 0.0D, 0.0D, sideTex);
		      tesselator.draw();
		      tesselator.startDrawingQuads();
		      tesselator.setNormal(1.0F, 0.0F, 0.0F);
		      renderer.renderSouthFace(block, 0.0D, 0.0D, 0.0D, sideTex);
		      tesselator.draw();
		      GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		    }
		    block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	  	  }
	  
		  public void renderInvBlock(RenderBlocks renderer, Block block, int metadata, int modelID)
		  {
		    if (modelID == missileModelID)
		      RenderMissileInInv(renderer, block, metadata);
		    else if(modelID==warheadModelID)
		    	RenderMissileInInv(renderer,block,metadata);
		    else if(modelID==machineModelID)
		    	RenderMachineInInv(renderer,block,metadata);
		  }

		  public boolean renderWorldBlock(RenderBlocks renderblocks, IBlockAccess iblockaccess, int i, int j, int k, Block block, int l)
		  {
		    if (l == missileModelID)
		      return RenderMissileInWorld(renderblocks, iblockaccess, i, j, k, block);
		    else if (l == warheadModelID)
			      return RenderWarheadInWorld(renderblocks, iblockaccess, i, j, k, block);
		    else if (l == machineModelID)
		    	return RenderMachineInWorld(renderblocks, iblockaccess, i, j, k, block);
		    return false;
		  }
		  
		  public boolean OnTickInGame(float tick, net.minecraft.client.Minecraft game)
		  {
			  ItemStack chute=game.thePlayer.inventory.armorItemInSlot(2);
			  if(chute==null)
				  return true;
			  boolean parachuteEquipped=(chute.itemID-256)==parachuteArmorID;
			  if(game.thePlayer.fallDistance>3&&parachuteEquipped)
			  {
				  game.thePlayer.fallDistance=1;
				  game.thePlayer.motionY=-0.3f;
				  if(!parachuteDeployed)
				  {
					  parachuteDeployed=true;
					  ParachuteEntity theChute=new ParachuteEntity(game.theWorld,(int)game.thePlayer.posX,(int)game.thePlayer.posY,(int)game.thePlayer.posZ,game.thePlayer);
					  game.theWorld.spawnEntityInWorld(theChute);
				  }
			  }
			  else if(parachuteDeployed&&game.thePlayer.fallDistance==0)
			  {
				  parachuteDeployed=false;
			  }
			  else if(parachuteDeployed)
			  {
				  game.thePlayer.fallDistance=0.1f;
				  game.thePlayer.motionY=-0.4f;
			  }
			  return true;
		  }
}
