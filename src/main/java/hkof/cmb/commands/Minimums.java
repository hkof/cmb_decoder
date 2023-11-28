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
