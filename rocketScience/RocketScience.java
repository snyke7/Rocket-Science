package rocketScience;

import net.minecraft.src.forge.*;
import ic2.common.*;
import ic2.platform.GuiIronFurnace;
import ic2.platform.NetworkManager;

import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.src.*;

public class RocketScience extends NetworkMod {

	public RocketScience()
    {
		/*ModLoaderMp.registerGUI(this, mod_ZZZMissile.guiIsotopeID);
		ModLoaderMp.registerGUI(this, mod_ZZZMissile.guiFusionID);
		ModLoaderMp.registerGUI(this, mod_ZZZMissile.guiAutoMinerID);*/
		MinecraftForge.registerEntity(MissileBoosterEntity.class,this,143,160,40,true);
		MinecraftForge.registerEntity(MissileWarheadEntity.class,this,144,160,40,true);
		MinecraftForge.registerEntity(MissilePassengerBoosterEntity.class,this,145,160,40,true);
		MinecraftForge.registerEntity(MissilePassengerWarheadEntity.class,this,146,160,40,true);
		MinecraftForge.registerEntity(ParachuteEntity.class,this,147,160,40,true);
		MinecraftForge.registerEntity(RangefinderEntity.class,this,148,160,40,true);
		MinecraftForge.registerEntity(MissileMinerBoosterEntity.class,this,149,160,40,true);
    }
	
	/*public void HandlePacket(Packet230ModLoader packet230modloader)
    {
        NetworkManager.handlePacket(packet230modloader);
    }*/
	
	public GuiScreen HandleGUI(int i)
    {
        net.minecraft.src.EntityPlayerSP entityplayersp = ModLoader.getMinecraftInstance().thePlayer;
        if(entityplayersp == null)
        {
            return null;
        } else
        {
            return getGuiForId(entityplayersp, i, null);
        }
    }

    public static GuiScreen getGuiForId(EntityPlayer entityplayer, int i, TileEntity tileentity)
    {
    	if(i==mod_ZZZMissile.guiIsotopeID)
    		return new GUISeparator(entityplayer.inventory, tileentity == null ? new TileEntityIsotope() : (TileEntityIsotope)tileentity);
    	else if(i==mod_ZZZMissile.guiFusionID)
    		return new GUIFusion(entityplayer.inventory, tileentity == null ? new TileEntityFusion() : (TileEntityFusion)tileentity);
    	else if(i==mod_ZZZMissile.guiAutoMinerID)
    		return new GUIAutoMiner(entityplayer.inventory, tileentity == null ? new TileEntityAutoMiner() : (TileEntityAutoMiner)tileentity);
    	else if(i==mod_ZZZMissile.guiDefenseID)
    		return new GUIDefense(entityplayer.inventory, tileentity== null ? new TileEntityDefense() : (TileEntityDefense)tileentity);
    	else if(i==mod_ZZZMissile.guiOffenseID)
    		return new GUIOffense(entityplayer.inventory, tileentity==null ? new TileEntityOffense() : (TileEntityOffense)tileentity);
    	return null;
    }

	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return "0.89";
	}
	
	static 
    {
        MinecraftForgeClient.preloadTexture("/rocketScience/blocks.png");
        MinecraftForgeClient.preloadTexture("/rocketScience/items.png");
        MinecraftForgeClient.preloadTexture("/rocketScience/MissileModel.png");
    }

	@Override
	public void load() {
		// TODO Auto-generated method stub
		mod_ZZZMissile.init();
	}
	
}
