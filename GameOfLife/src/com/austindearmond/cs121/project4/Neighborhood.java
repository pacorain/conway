package com.austindearmond.cs121.project4;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class Neighborhood {
	private final HashMap<Point, Cell> neighborhood;
	private int width;
	private int height;

	public Neighborhood(int width, int height) {
		neighborhood = new HashMap<>();
		this.width = width;
		this.height = height;
		initializeCells();
	}

	private void initializeCells() {
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				neighborhood.put(Point.atX(x).atY(y), new Cell());
	}
	
	public Neighborhood(Neighborhood source) {
		neighborhood = new HashMap<>(source.neighborhood);
	}

	public void randomize() {
		Random random = new Random();
		for (Point point : getPoints()) {
			boolean alive = random.nextBoolean();
			cellAt(point).setAlive(alive);
		}
	}

	public Set<Point> getPoints() {
		return neighborhood.keySet();
	}

	public Cell cellAt(Point point) {
		if (point.getX() >= width || point.getX() < 0 
				|| point.getY() >= height || point.getY() < 0)
			throw new PointIndexOutOfBoundsException();
		return neighborhood.get(point);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean hasCitizen() {
		for (Point point : getPoints())
			if (cellAt(point).isAlive())
				return true;
		return false;
	}

	public int count() {
		int count = 0;
		for (Point point : getPoints())
			if (cellAt(point).isAlive())
				count++;
		return count;
	}

	public String toString(char alive, char dead) {
		String value = "Neighborhood [width: " + width + ", height: " + height + "]";
		Point point;
		for (int y = 0; y < height; y++) {
			value = value.concat("\n");
			for (int x = 0; x < width; x++) {
				point = Point.atX(x).atY(y);
				if (cellAt(point).isAlive())
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

	@Override
	public int hashCode() {
		final int prime = 239;
		int result = 1;
		result = prime * result + (neighborhood.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Neighborhood other = (Neighborhood) obj;
		if (neighborhood == null) {
			if (other.neighborhood != null)
				return false;
		} else if (!neighborhood.equals(other.neighborhood))
			return false;
		return true;
	}
	
	public Neighborhood nextGeneration() {
		Neighborhood newNeighborhood = new Neighborhood(width, height);
		for (Point point : getPoints()) {
			boolean alive = isAliveNextGeneration(point);
			newNeighborhood.cellAt(point).setAlive(alive);
		}
		return newNeighborhood;
	}
	
	private boolean isAliveNextGeneration(Point point) {
		return hasThreeNeighbors(point) || isAliveWithTwoNeighbors(point);
	}

	private boolean hasThreeNeighbors(Point point) {
		return numberOfNeighbors(point) == 3;
	}

	private boolean isAliveWithTwoNeighbors(Point point) {
		return numberOfNeighbors(point) == 2 && cellAt(point).isAlive();
	}

	private int numberOfNeighbors(Point point) {
		int neighborCount = cellAt(point).isAlive() ? -1 : 0;
		for (int y = point.getY() - 1; y <= point.getY() + 1; y++)
			for (int x = point.getX() - 1; x <= point.getX() + 1; x++)
				if (isNeighbor(Point.atX(x).atY(y)))
					neighborCount++;
		return neighborCount;
	}

	private boolean isNeighbor(Point point) {
		try {
			return cellAt(point).isAlive();
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}
}
