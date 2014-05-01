package net.minecraft.server;

import java.util.Random;

public class BlockCrops extends BlockAgriculture implements IBlockFragilePlantElement {

    protected BlockCrops() {
        this.a(true);
        float f = 0.5F;

        this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
        this.a((CreativeModeTab) null);
        this.c(0.0F);
        this.a(h);
        this.H();
    }

    protected boolean a(Block block) {
        return block == Blocks.SOIL;
    }

    public void a(World world, int i, int j, int k, Random random) {
        super.a(world, i, j, k, random);
        if (world.getLightLevel(i, j + 1, k) >= 9) {
            int l = world.getData(i, j, k);

            if (l < 7) {
                float f = this.n(world, i, j, k);

                if (random.nextInt((int) (25.0F / f) + 1) == 0) {
                    ++l;
                    org.bukkit.craftbukkit.event.CraftEventFactory.handleBlockGrowEvent(world, i, j, k, this, l); // CraftBukkit
                }
            }
        }
    }

    public void m(World world, int i, int j, int k) {
        int l = world.getData(i, j, k) + MathHelper.nextInt(world.random, 2, 5);

        if (l > 7) {
            l = 7;
        }

        world.setData(i, j, k, l, 2);
    }

    

    public int b() {
        return 6;
    }

    protected Item i() {
        return Items.SEEDS;
    }

    protected Item P() {
        return Items.WHEAT;
    }

    public void dropNaturally(World world, int i, int j, int k, int l, float f, int i1) {
        super.dropNaturally(world, i, j, k, l, f, 0);
        if (!world.isStatic) {
            if (l >= 7) {
                int j1 = 3 + i1;

                for (int k1 = 0; k1 < j1; ++k1) {
                    if (world.random.nextInt(15) <= l) {
                        this.a(world, i, j, k, new ItemStack(this.i(), 1, 0));
                    }
                }
            }
        }
    }

    public Item getDropType(int i, Random random, int j) {
        return i == 7 ? this.P() : this.i();
    }

    public int a(Random random) {
        return 1;
    }

    public boolean a(World world, int i, int j, int k, boolean flag) {
        return world.getData(i, j, k) != 7;
    }

    public boolean a(World world, Random random, int i, int j, int k) {
        return true;
    }

    public void b(World world, Random random, int i, int j, int k) {
        this.m(world, i, j, k);
    }
}
