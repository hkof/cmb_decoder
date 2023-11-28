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

public class PrintingMode extends Command {
	
	public enum Mode {
		Part_Interior,
		Part_Surface,
		Sparse_Raster,
		Solid_Raster,
		Part_First_Layer,
		Part_First_Layer_Perimeter,
		Support,
		Interface,
		Sparse_Bridge
	};
	
	public static final byte CODE = 0xd; 
	byte mode;
	byte extra_byte;
	public PrintingMode(byte code, byte[] data) {
		mode = data[0];
		extra_byte = data[1];
		this.control_code = code;
	}
	
	
	public void changeMode(Mode new_mode) {
		switch(new_mode) {
			case Part_Interior: mode = 0x65;break;
			case Part_Surface: mode = 0x66;break;
			case Sparse_Raster: mode = 0x67;break;
			case Solid_Raster: mode = 0x68;break;
			case Part_First_Layer: mode = 0x6B;break;
			case Part_First_Layer_Perimeter: mode = 0x6E;break;
			case Support: mode = (byte) 0xC8;break;
			case Interface: mode = (byte) 0xCA;break;
			case Sparse_Bridge: mode = (byte) 0xCB;break;
		}
	}
	
	public Mode getPrintingMode() {
		switch(mode) {
		case 0x65: return Mode.Part_Interior;
		case 0x66: return Mode.Part_Surface;
		case 0x67: return Mode.Sparse_Raster;
		case 0x68: return Mode.Solid_Raster;
		case 0x6B: return Mode.Part_First_Layer;
		case 0x6E: return Mode.Part_First_Layer_Perimeter;
		case (byte)0xC8: return Mode.Support;
		case (byte)0xCA: return Mode.Interface;
		case (byte)0xCB: return Mode.Sparse_Bridge;
		}
		return null;
	}
	
	@Override
	public byte[] getBytes() {
		// TODO Auto-generated method stub
		return new byte[] {control_code,mode,extra_byte};

	}
	
	
}
