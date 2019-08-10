package hkof.cmb.commands;

public abstract class ToolPathWayPoint extends Command {
	public static final byte CODE = 0xb;
	byte control_sub_code;
	float x_coordinate;
	float y_coordinate;
	
	public float getX_coordinate() {
		return x_coordinate;
	}

	public void setX_coordinate(float x_coordinate) {
		this.x_coordinate = x_coordinate;
	}

	
	
	public float getY_coordinate() {
		return y_coordinate;
	}

	public void setY_coordinate(float y_coordinate) {
		this.y_coordinate = y_coordinate;
	}

	@Override
	public String toString() {
		return String.format("%s 0x0%x",super.toString(), control_sub_code);
	}

	
}
