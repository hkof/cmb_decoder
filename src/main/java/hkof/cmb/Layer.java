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
