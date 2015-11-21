package com.austindearmond.cs121.project4;

public class Point {
	private final int x;
	private final int y;
	
	public static final Builder atX(int x) {
		return new Builder(x);
	}
	
	public static final class Builder {
		private int x;
		private int y;
		
		private Builder(int x) {
			this.x = x;
		}
		public Point atY(int y) {
			this.y = y;
			return new Point(this);
		}
	}
	
	private Point(Builder builder) {
		x = builder.x;
		y = builder.y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
