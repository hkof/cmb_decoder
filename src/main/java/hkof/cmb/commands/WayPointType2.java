package hkof.cmb.commands;

import java.nio.ByteBuffer;

public class WayPointType2 extends ToolPathWayPoint {
	public static final byte SUB_CODE = 0x2; 
	float z_coordinate;
	public WayPointType2(byte code, byte sub_code,  byte[] data) {
		ByteBuffer buf = ByteBuffer.wrap(data);
		buf.order(Command.getByteOrder());
		x_coordinate = buf.getFloat();
		y_coordinate = buf.getFloat();
		z_coordinate = buf.getFloat();
		this.control_code = code;
		this.control_sub_code = sub_code;
	}
	
	@Override
	public byte[] getBytes() {
		return ByteBuffer.allocate(14).put(control_code).put(control_sub_code).order(Command.getByteOrder()).putFloat(x_coordinate).putFloat(y_coordinate).putFloat(z_coordinate).array();

	}

	@Override
	public String toString() {
		return String.format("%s:, %f, %f, %f", super.toString(), x_coordinate, y_coordinate, z_coordinate);
	}
}
