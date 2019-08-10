package hkof.cmb.commands;

public class EndOfLayer extends Command {
	public static final byte CODE = 0x7;
	
	public EndOfLayer() {
		this.control_code = CODE;
	}

	@Override
	public byte[] getBytes() {
		return new byte [] {control_code};

	}
}
