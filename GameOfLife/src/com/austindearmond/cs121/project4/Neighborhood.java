package com.austindearmond.cs121.project4;

import java.util.Random;

public class Neighborhood {
	Cell[][] neighborhood;

	public Neighborhood(Cell[][] cellArray) {
		neighborhood = cellArray;
	}

	public Neighborhood(int width, int height) {
		neighborhood = new Cell[height][width];
	}

	public void randomize() {
		for (int y = 0; y < getHeight(); y++)
			for (int x = 0; x < getWidth(); x++)
				neighborhood[x][y] = getRandomCell();
	}

	private Cell getRandomCell() {
		Random random = new Random();
		boolean alive = random.nextBoolean();
		return new Cell(alive);
	}

	public Cell[][] toCells() {
		return neighborhood;
	}

	public int getWidth() {
		return neighborhood[0].length;
	}

	public int getHeight() {
		return neighborhood.length;
	}

	public boolean isAlive(int x, int y) throws ArrayIndexOutOfBoundsException {
		return neighborhood[x][y].isAlive();
	}

	public void setAlive(int x, int y, boolean life) {
		neighborhood[x][y].setAlive(life);
	}

	public boolean hasCitizen() {
		// Check each cell for a citizen.
		for (int i = 0; i < neighborhood.length; i++)
			for (int j = 0; j < neighborhood[i].length; j++)
				if (neighborhood[i][j].isAlive())
					return true;
		return false;
	}

	public int count() {
		int count = 0;
		for (int i = 0; i < getHeight(); i++)
			for (int j = 0; j < getWidth(); j++)
				if (neighborhood[j][i].isAlive())
					count++;
		return count;
	}

	public String toString(char alive, char dead) {
		String value = "Neighborhood [width: " + neighborhood[0].length
				+ ", height: " + neighborhood.length + "]";
		for (int i = 0; i < getHeight(); i++) {
			value = value.concat("\n");
			for (int j = 0; j < getWidth(); j++) {
				if (neighborhood[i][j].isAlive())
					value = value.concat(Character.toString(alive));
				else
					value = value.concat(Character.toString(dead));
			}
		}
		return value;
	}

	@Override
	public String toString() {
		return toString('X', '.');
	}

	public Cell[][] nextGeneration() {
		// Go through and add up the elements' neighbors
		int[][] neighborCount = new int[getHeight()][getWidth()];
		for (int i = 0; i < getHeight(); i++)
			for (int j = 0; j < getWidth(); j++)
				if (neighborhood[i][j].isAlive()) {
					// Add 1 to all surrounding cells in the neighbor count,
					// being mindful of walls.
					if (j != 0) {
						if (i != 0)
							neighborCount[i - 1][j - 1] += 1;
						neighborCount[i][j - 1] += 1;
						if (i != getHeight() - 1)
							neighborCount[i + 1][j - 1] += 1;
					}
					if (i != 0)
						neighborCount[i - 1][j] += 1;
					if (i != getHeight() - 1)
						neighborCount[i + 1][j] += 1;
					if (j != getWidth() - 1) {
						if (i != 0)
							neighborCount[i - 1][j + 1] += 1;
						neighborCount[i][j + 1] += 1;
						if (i != getHeight() - 1)
							neighborCount[i + 1][j + 1] += 1;
					}
				}
		// Store the result of each cell based on how many neighbors it has
		for (int i = 0; i < getHeight(); i++)
			for (int j = 0; j < getWidth(); j++) {
				if (neighborCount[i][j] == 3)
					neighborhood[i][j].setAlive(true);
				else if (neighborCount[i][j] == 2 && neighborhood[i][j].isAlive())
					neighborhood[i][j].setAlive(true);
				else
					neighborhood[i][j].setAlive(true);
			}
		return neighborhood;
	}
}
