package net.minecraft.server;

public class BlockAgriculture extends BlockPlant {
	
	float n(World world, int i, int j, int k) {
        float f = 1.0F;
        Block block = world.getType(i, j, k - 1);
        Block block1 = world.getType(i, j, k + 1);
        Block block2 = world.getType(i - 1, j, k);
        Block block3 = world.getType(i + 1, j, k);
        Block block4 = world.getType(i - 1, j, k - 1);
        Block block5 = world.getType(i + 1, j, k - 1);
        Block block6 = world.getType(i + 1, j, k + 1);
        Block block7 = world.getType(i - 1, j, k + 1);
        boolean flag = block2 == this || block3 == this;
        boolean flag1 = block == this || block1 == this;
        boolean flag2 = block4 == this || block5 == this || block6 == this || block7 == this;

        for (int l = i - 1; l <= i + 1; ++l) {
            for (int i1 = k - 1; i1 <= k + 1; ++i1) {
                Block block8 = world.getType(l, j - 1, i1);
                float f1 = 0.0F;

                if (block8 == Blocks.SOIL) {
                    f1 = 1.0F;
                    if (world.getData(l, j - 1, i1) > 0) {
                        f1 = 3.0F;
                    }
                }

                if (l != i || i1 != k) {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        if (flag2 || flag && flag1) {
            f /= 2.0F;
        }

        return f;
    }
}
