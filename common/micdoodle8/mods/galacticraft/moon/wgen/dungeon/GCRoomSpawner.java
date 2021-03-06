package micdoodle8.mods.galacticraft.moon.wgen.dungeon;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class GCRoomSpawner extends GCDungeonRoom
{
    int sizeX;
    int sizeY;
    int sizeZ;
    Random rand;

    private final ArrayList<ChunkCoordinates> spawners = new ArrayList<ChunkCoordinates>();

    public GCRoomSpawner(World worldObj, int posX, int posY, int posZ, int entranceDir)
    {
        super(worldObj, posX, posY, posZ, entranceDir);
        if (worldObj != null)
        {
            this.rand = new Random(worldObj.getSeed() * posX * posY * 57 * posZ);
            this.sizeX = this.rand.nextInt(5) + 6;
            this.sizeY = this.rand.nextInt(2) + 4;
            this.sizeZ = this.rand.nextInt(5) + 6;
        }
    }

    @Override
    public void generate(short[] chunk, byte[] meta, int cx, int cz)
    {
        for (int i = this.posX - 1; i <= this.posX + this.sizeX; i++)
        {
            for (int j = this.posY - 1; j <= this.posY + this.sizeY; j++)
            {
                for (int k = this.posZ - 1; k <= this.posZ + this.sizeZ; k++)
                {
                    if (i == this.posX - 1 || i == this.posX + this.sizeX || j == this.posY - 1 || j == this.posY + this.sizeY || k == this.posZ - 1 || k == this.posZ + this.sizeZ)
                    {
                        this.placeBlock(chunk, meta, i, j, k, cx, cz, GCMapGenDungeon.DUNGEON_WALL_ID, GCMapGenDungeon.DUNGEON_WALL_META);
                    }
                    else
                    {
                        this.placeBlock(chunk, meta, i, j, k, cx, cz, 0, 0);
                        if (this.rand.nextFloat() < 0.05F)
                        {
                            this.placeBlock(chunk, meta, i, j, k, cx, cz, Block.web.blockID, 0);
                        }
                    }
                }
            }
        }
        if (this.placeBlock(chunk, meta, this.posX + 1, this.posY - 2, this.posZ + 1, cx, cz, Block.mobSpawner.blockID, 0))
        {
            this.spawners.add(new ChunkCoordinates(this.posX + 1, this.posY - 2, this.posZ + 1));
        }
        if (this.placeBlock(chunk, meta, this.posX + this.sizeX - 1, this.posY - 2, this.posZ + this.sizeZ - 1, cx, cz, Block.mobSpawner.blockID, 0))
        {
            this.spawners.add(new ChunkCoordinates(this.posX + this.sizeX - 1, this.posY - 2, this.posZ + this.sizeZ - 1));
        }
    }

    @Override
    public GCDungeonBoundingBox getBoundingBox()
    {
        return new GCDungeonBoundingBox(this.posX, this.posZ, this.posX + this.sizeX, this.posZ + this.sizeZ);
    }

    @Override
    protected GCDungeonRoom makeRoom(World worldObj, int x, int y, int z, int dir)
    {
        return new GCRoomSpawner(worldObj, x, y, z, dir);
    }

    @Override
    protected void handleTileEntities(Random rand)
    {
        for (final ChunkCoordinates spawnerCoords : this.spawners)
        {
            final TileEntityMobSpawner spawner = (TileEntityMobSpawner) this.worldObj.getBlockTileEntity(spawnerCoords.posX, spawnerCoords.posY, spawnerCoords.posZ);
            if (spawner != null)
            {
                spawner.func_98049_a().setMobID(GCRoomSpawner.getMob(rand));
            }
        }
    }

    private static String getMob(Random rand)
    {
        switch (rand.nextInt(2))
        {
        case 0:
            return "Evolved Spider";
        case 1:
            return "Evolved Skeleton";
        default:
            return "Evolved Zombie";
        }
    }
}
