package net.zoocraftia.core;

import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class BehaviorDartDispense extends BehaviorProjectileDispense
{
    /** Reference to the MinecraftServer object. */
    final MinecraftServer mcServer;

    public BehaviorDartDispense(MinecraftServer par1)
    {
        this.mcServer = par1;
    }

    /**
     * Return the projectile entity spawned by this dispense behavior.
     */
    protected IProjectile getProjectileEntity(World par1World, IPosition par2IPosition)
    {
        DartEntity var3 = new DartEntity(par1World, par2IPosition.getX(), par2IPosition.getY(), par2IPosition.getZ());
        var3.canBePickedUp = 1;
        return var3;
    }
}
