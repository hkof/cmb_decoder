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

public class WayPointType1 extends ToolPathWayPoint {
	public static final byte SUB_CODE = 0x1; 
	
	public WayPointType1(byte code, byte sub_code,  byte[] data) {
		ByteBuffer buf = ByteBuffer.wrap(data);
		buf.order(Command.getByteOrder());
		x_coordinate = buf.getFloat();
		y_coordinate = buf.getFloat();
		this.control_code = code;
		this.control_sub_code = sub_code;
	}
	
	@Override
	public String toString() {
		return String.format("%s:, %f, %f", super.toString(), x_coordinate, y_coordinate);
	}
	
	@Override
	public byte[] getBytes() {
		return ByteBuffer.allocate(10).put(control_code).put(control_sub_code).order(Command.getByteOrder()).putFloat(x_coordinate).putFloat(y_coordinate).array();
	}
}
