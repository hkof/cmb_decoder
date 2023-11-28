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
