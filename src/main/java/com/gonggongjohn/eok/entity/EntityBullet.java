package com.gonggongjohn.eok.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityBullet extends EntityThrowable {
	public EntityBullet(World worldIn) {
		super(worldIn);
	}

	public EntityBullet(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
	}

	public EntityBullet(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	@Override
	protected void onImpact(MovingObjectPosition movingObjectPosition) {
		if (worldObj.isRemote) {
			return;
		}
		if (movingObjectPosition.entityHit instanceof EntityLiving) {
			EntityLiving living = (EntityLiving) movingObjectPosition.entityHit;
			living.attackEntityFrom(DamageSource.generic, 10F);
		}
		this.setDead();
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (ticksExisted > 200) {
			this.setDead();
		}
	}

	@Override
	protected float func_70182_d() {
		return 100F;
	}
}