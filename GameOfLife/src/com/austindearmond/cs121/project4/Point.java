package com.austindearmond.cs121.project4;

import java.util.HashSet;
import java.util.Set;

public class Point {
	private static final Point[] SURROUNDING_POINT_OFFSETS = {
			Point.atX(-1).atY(-1),
			Point.atX(0).atY(-1),
			Point.atX(1).atY(-1),
			Point.atX(-1).atY(0),
			Point.atX(1).atY(0),
			Point.atX(-1).atY(1),
			Point.atX(0).atY(1),
			Point.atX(1).atY(1)
		};
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
	
	public Set<Point> getSurroundingPoints() {
		Set<Point> surroundingPoints = new HashSet<Point>();
		Point newPoint;
		for (Point offset : SURROUNDING_POINT_OFFSETS) {
			newPoint = Point.atX(x + offset.x).atY(y + offset.y);
			surroundingPoints.add(newPoint);
		}
		return surroundingPoints;
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
