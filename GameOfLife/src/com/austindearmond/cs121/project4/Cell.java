package com.austindearmond.cs121.project4;

public class Cell implements Cloneable {
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
	
}
