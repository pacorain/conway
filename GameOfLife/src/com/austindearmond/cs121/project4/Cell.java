package com.austindearmond.cs121.project4;

public class Cell {
	private boolean alive;
	
	public Cell() {
		this(true);
	}
	
	public Cell(boolean alive) {
		this.alive = alive;
	}
	
	public Cell(Cell cell) {
		this.alive = cell.alive;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	@Override
	public int hashCode() {
		return alive ? 3593 : 3607;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (alive != other.alive)
			return false;
		return true;
	}
	
}
