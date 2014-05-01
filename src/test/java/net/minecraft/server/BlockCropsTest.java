package net.minecraft.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class BlockCropsTest {
	public BlockCrops blockStem = new BlockCrops();

	@Test
	public void testTrueTrueTrueTrue() {
		testWorld tester = new testWorld(true, true, true, true);
		assertEquals(blockStem.n(tester, 1, 1, 1), 4.0F, 0.5);
	}
	
	@Test
	public void testTrueTrueTrueFalse() {
		testWorld tester = new testWorld(true, true, true, false);
		assertEquals(blockStem.n(tester, 1, 1, 1), 4.0F, 0.5);
	}
	
	@Test
	public void testTrueTrueFalseTrue() {
		testWorld tester = new testWorld(true, true, false, true);
		assertEquals(blockStem.n(tester, 1, 1, 1), 4.0F, 0.5);
	}
	
	@Test
	public void testTrueFalseTrueTrue() {
		testWorld tester = new testWorld(true, false, true, true);
		assertEquals(blockStem.n(tester, 1, 1, 1), 10.0F, 0.5);
	}
	
	@Test
	public void testFalseTrueTrueTrue() {
		testWorld tester = new testWorld(false, true, true, true);
		assertEquals(blockStem.n(tester, 1, 1, 1), 4.0F, 0.5);
	}
	
	@Test
	public void testTrueTrueFalseFalse() {
		testWorld tester = new testWorld(true, true, false, false);
		assertEquals(blockStem.n(tester, 1, 1, 1), 4.0F, 0.5);
	}
	
	@Test
	public void testTrueFalseTrueFalse() {
		testWorld tester = new testWorld(true, false, true, false);
		assertEquals(blockStem.n(tester, 1, 1, 1), 10.0F, 0.5);
	}
	
	@Test
	public void testFalseTrueTrueFalse() {
		testWorld tester = new testWorld(false, true, true, false);
		assertEquals(blockStem.n(tester, 1, 1, 1), 4.0F, 0.5);
	}
	
	@Test
	public void testTrueFalseFalseTrue() {
		testWorld tester = new testWorld(true, false, false, true);
		assertEquals(blockStem.n(tester, 1, 1, 1) , 10.0F, 0.5);
	}
	
	@Test
	public void testFalseTrueFalseTrue() {
		testWorld tester = new testWorld(false, true, false, true);
		assertEquals(blockStem.n(tester, 1, 1, 1), 4.0F, 0.5);
	}
	
	@Test
	public void testFalseFalseTrueTrue() {
		testWorld tester = new testWorld(false, false, true, true);
		assertEquals(blockStem.n(tester, 1, 1, 1), 10.0F, 0.5);
	}
	
	@Test
	public void testTrueFalseFalseFalse() {
		testWorld tester = new testWorld(true, false, false, false);
		assertEquals(blockStem.n(tester, 1, 1, 1), 10.0F, 0.5);
	}
	
	@Test
	public void testFalseTrueFalseFalse() {
		testWorld tester = new testWorld(false, true, false, false);
		assertEquals(blockStem.n(tester, 1, 1, 1), 4.0F, 0.5);
	}
	
	@Test
	public void testFalseFalseTrueFalse() {
		testWorld tester = new testWorld(false, false, true, false);
		assertEquals(blockStem.n(tester, 1, 1, 1), 10.0F, 0.5);
	}
	
	@Test
	public void testFalseFalseFalseTrue() {
		testWorld tester = new testWorld(false, false, false, true);
		assertEquals(blockStem.n(tester, 1, 1, 1), 10.0F, 0.5);
	}
	
	@Test
	public void testFalseFalseFalseFalse() {
		testWorld tester = new testWorld(false, false, false, false);
		assertEquals(blockStem.n(tester, 1, 1, 1), 10.0F, 0.5);
	}
	
	private class testWorld extends World {
		private HashMap<Integer, ArrayList<Block>> matrix = new HashMap<Integer, ArrayList<Block>>();
		private Block soilBlock = Blocks.SOIL;
		private Block notSoilBlock = Blocks.LAVA;
		private boolean testSoil;
		private boolean emptyData;
		
		private testWorld(boolean testSoil, boolean emptyData, boolean flagAndFlag1, boolean flag2) {
			this.testSoil = testSoil;
			this.emptyData = emptyData;
			
			ArrayList<Block> blocks = new ArrayList<Block>();
			blocks.add(flag2?
					new BlockStem(Blocks.LAVA):
					new BlockCrops());
			blocks.add(flagAndFlag1?
					new BlockStem(Blocks.LAVA):
					new BlockCrops());
			blocks.add(Blocks.LAVA);
			matrix.put(0,blocks);
			
			ArrayList<Block> blocks2 = new ArrayList<Block>();
			blocks2.add(flagAndFlag1?
					new BlockStem(Blocks.LAVA):
					new BlockCrops());
			blocks2.add(Blocks.LAVA);
			blocks2.add(Blocks.LAVA);
			matrix.put(1, blocks2);
			
			ArrayList<Block> blocks3 = new ArrayList<Block>();
			blocks3.add(Blocks.LAVA);
			blocks3.add(Blocks.LAVA);
			blocks3.add(Blocks.LAVA);
			matrix.put(2, blocks3);
		}
		
		//GetType assumes that the first and third parameter x and y are going
		//to be 0-2
		//The z variable has no real assumptions other than it is treated as 
		//normal if the z==1. If it's not, we give it the auxiliary block

		@Override
		protected IChunkProvider j() {
			// Left blank as unnecessary for tests
			return null;
		}
		
		@Override
		public Block getType(int x, int z, int y) {
			Block toReturn;
			if(z!=1){
				toReturn = testSoil?
								soilBlock:
								notSoilBlock;
			} else {
				toReturn = matrix.get(x).get(y);
			}
			return toReturn;
			
		}
		
		@Override
		public int getData(int x, int z, int y) {
			int toReturn;
			if (emptyData == true) {
				toReturn = 0;
			} else {
				toReturn = 1;
			}
			return toReturn;
		}

		@Override
		protected int p() {
			// Left blank as unnecessary for tests
			return 0;
		}

		@Override
		public Entity getEntity(int i) {
			// TODO Left blank as unnecessary for tests
			return null;
		}
	}
}