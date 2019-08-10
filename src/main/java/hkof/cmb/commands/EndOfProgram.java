package hkof.cmb.commands;

public class EndOfProgram extends Command {
	public static final byte CODE = 0x3; 
	
	public EndOfProgram() {
		this.control_code = CODE;
	}
	
	@Override
	public byte[] getBytes() {
		return  new byte[] {control_code};
	}
	
}
