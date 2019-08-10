package hkof.cmb.commands;

import java.nio.ByteBuffer;

public class Minimums extends Command {
	public static final byte CODE = 0x5; 
	float min_x;
	float min_y;
	float min_z;
	
	public Minimums(byte code, byte[] data) {
		ByteBuffer buf = ByteBuffer.wrap(data);
		buf.order(getByteOrder());
		min_x= buf.getFloat();
		min_y = buf.getFloat();
		min_z = buf.getFloat();
		this.control_code = code;
	}
	
	@Override
	public byte[] getBytes() {
		return ByteBuffer.allocate(13).put(control_code).order(getByteOrder()).putFloat(min_x).putFloat(min_y).putFloat(min_z).array();
	}
	

}
