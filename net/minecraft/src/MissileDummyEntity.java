package net.minecraft.src;

public class MissileDummyEntity extends EntityLiving{

	public MissileDummyEntity(World world) {
		super(world);
	}

	public static void updateFall(Entity titty, float fall)
	{
		titty.fallDistance=fall;
	}
	
	public static boolean isFallDistance(Entity titty, float fall)
	{
		return titty.fallDistance==fall;
	}

	@Override
	public int getMaxHealth() {
		return 0;
	}
}
