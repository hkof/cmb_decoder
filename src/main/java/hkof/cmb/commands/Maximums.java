package hkof.cmb.commands;

import java.nio.ByteBuffer;

public class Maximums extends Command {
	public static final byte CODE = 0x6; 
	float max_x;
	float max_y;
	float max_z;
	
	public Maximums(byte code, byte[] data) {
		ByteBuffer buf = ByteBuffer.wrap(data);
		buf.order(getByteOrder());
		max_x= buf.getFloat();
		max_y = buf.getFloat();
		max_z = buf.getFloat();
		
		this.control_code = code;
	}
	
	@Override
	public byte[] getBytes() {
		return ByteBuffer.allocate(13).put(control_code).order(getByteOrder()).putFloat(max_x).putFloat(max_y).putFloat(max_z).array();
	}

}
