package net.minecraft.server;

import java.util.Random;

import org.bukkit.craftbukkit.event.CraftEventFactory; // CraftBukkit

public class BlockStem extends BlockAgriculture implements IBlockFragilePlantElement {

    private final Block blockFruit;

    protected BlockStem(Block block) {
        this.blockFruit = block;
        this.a(true);
        float f = 0.125F;

        this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
        this.a((CreativeModeTab) null);
    }

    protected boolean a(Block block) {
        return block == Blocks.SOIL;
    }

    public void a(World world, int i, int j, int k, Random random) {
        super.a(world, i, j, k, random);
        if (world.getLightLevel(i, j + 1, k) >= 9) {
            float f = this.n(world, i, j, k);

            if (random.nextInt((int) (25.0F / f) + 1) == 0) {
                int l = world.getData(i, j, k);

                if (l < 7) {
                    ++l;
                    CraftEventFactory.handleBlockGrowEvent(world, i, j, k, this, l); // CraftBukkit
                } else {
                    if (world.getType(i - 1, j, k) == this.blockFruit) {
                        return;
                    }

                    if (world.getType(i + 1, j, k) == this.blockFruit) {
                        return;
                    }

                    if (world.getType(i, j, k - 1) == this.blockFruit) {
                        return;
                    }

                    if (world.getType(i, j, k + 1) == this.blockFruit) {
                        return;
                    }

                    int i1 = random.nextInt(4);
                    int j1 = i;
                    int k1 = k;

                    if (i1 == 0) {
                        j1 = i - 1;
                    }

                    if (i1 == 1) {
                        ++j1;
                    }

                    if (i1 == 2) {
                        k1 = k - 1;
                    }

                    if (i1 == 3) {
                        ++k1;
                    }

                    Block block = world.getType(j1, j - 1, k1);

                    if (world.getType(j1, j, k1).material == Material.AIR && (block == Blocks.SOIL || block == Blocks.DIRT || block == Blocks.GRASS)) {
                        CraftEventFactory.handleBlockGrowEvent(world, j1, j, k1, this.blockFruit, 0); // CraftBukkit
                    }
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


    public void g() {
        float f = 0.125F;

        this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
    }

    public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
        this.maxY = (double) ((float) (iblockaccess.getData(i, j, k) * 2 + 2) / 16.0F);
        float f = 0.125F;

        this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, (float) this.maxY, 0.5F + f);
    }

    public int b() {
        return 19;
    }

    public void dropNaturally(World world, int i, int j, int k, int l, float f, int i1) {
        super.dropNaturally(world, i, j, k, l, f, i1);
        if (!world.isStatic) {
            Item item = null;

            if (this.blockFruit == Blocks.PUMPKIN) {
                item = Items.PUMPKIN_SEEDS;
            }

            if (this.blockFruit == Blocks.MELON) {
                item = Items.MELON_SEEDS;
            }

            for (int j1 = 0; j1 < 3; ++j1) {
                if (world.random.nextInt(15) <= l) {
                    this.a(world, i, j, k, new ItemStack(item));
                }
            }
        }
    }

    public Item getDropType(int i, Random random, int j) {
        return null;
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
