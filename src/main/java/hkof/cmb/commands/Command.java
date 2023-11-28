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

import java.nio.ByteOrder;

//Ref: https://azttm.wordpress.com/2012/09/22/inside-the-stratasys-dimension-catalyst-cmb-file-format/
public abstract class Command {
	 byte control_code;
	
	public static Command parseData(byte code,byte[] data) throws IllegalArgumentException {
		switch(code) {
			case EndOfProgram.CODE: return new EndOfProgram();
			case Minimums.CODE: return new Minimums(code, data);
			case Maximums.CODE: return new Maximums(code, data);
			case EndOfLayer.CODE: return new EndOfLayer();
			case PrintingMode.CODE: return new PrintingMode(code, data);
			default: throw new IllegalArgumentException("Unknown Command= "+ code);
		}
	}
	
	public static Command parseData(byte code,  byte sub_code, byte[] data) throws IllegalArgumentException {
		switch(code) {
		case ToolPathWayPoint.CODE: 
			switch(sub_code) {
			case WayPointType0.SUB_CODE: return new WayPointType0(code,  sub_code, data);
			case WayPointType1.SUB_CODE: return new WayPointType1(code, sub_code, data);
			case WayPointType2.SUB_CODE: return new WayPointType2(code, sub_code, data);
			case WayPointType3.SUB_CODE: return new WayPointType3(code,  sub_code, data);
			}
		default: throw new IllegalArgumentException("Unknown Command= "+ code);
		}
		
	}
	
	public static ByteOrder getByteOrder() {
		return ByteOrder.LITTLE_ENDIAN;
	}
	
	public static byte getCommandDataSizeInBytes(byte code, byte sub_code) throws IllegalArgumentException {
		//byte one 
		switch(code) {
		case 0x3: return 0;
		case 0x5: return 12;
		case 0x6: return 12;
		case 0x7: return 0;
		case 0xb: 
			switch(sub_code) {
			case 0x0: return 16;
			case 0x1: return 8;
			case 0x2: return 12;
			case 0x3: return 12;
			}
		case 0xd: return 2;
		default: throw new IllegalArgumentException("Unknown Command= "+ code);
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		//return super.toString();
		return String.format("Command type: 0x0%X", control_code);
	}
	
	public abstract byte[] getBytes();
	
}
