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

package hkof.cmb;

import hkof.cmb.commands.Command;
import java.util.ArrayList;
import java.util.List;

public class Layer {

	int layer_number;
	ArrayList<Command> commands;
	
	public Layer(int layer_number) {
		this.layer_number = layer_number;
		commands = new ArrayList<Command>();
	}
	
	public void addCommand(Command cmd) {
		commands.add(cmd);
	}
	
	public int getSize() {
		return commands.size();
	}
	
	public Command getCommand(int index) {
		return commands.get(index);
	}

	public List<Command> getCommands(){
		return commands;
	}
}
