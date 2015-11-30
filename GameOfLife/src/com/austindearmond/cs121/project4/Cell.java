package com.austindearmond.cs121.project4;

public class Cell {
	private boolean occupied;
	
	public Cell() {
		this(true);
	}
	
	public Cell(boolean alive) {
		this.occupied = alive;
	}
	
	public Cell(Cell cell) {
		this.occupied = cell.occupied;
	}
	
	public boolean isOccupied() {
		return occupied;
	}
	
	public void setOccupied(boolean alive) {
		this.occupied = alive;
	}

	@Override
	public int hashCode() {
		return occupied ? 3593 : 3607;
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
		if (occupied != other.occupied)
			return false;
		return true;
	}
	
}
