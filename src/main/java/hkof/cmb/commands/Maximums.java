/*
Copyright 2023 Hamza Alkofahi

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

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
