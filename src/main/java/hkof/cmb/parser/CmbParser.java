package hkof.cmb.parser;

import hkof.cmb.CmbFile;
import hkof.cmb.CmbHeader;
import hkof.cmb.Layer;
import hkof.cmb.commands.Command;
import hkof.cmb.commands.EndOfLayer;
import hkof.cmb.commands.EndOfProgram;
import hkof.cmb.commands.ToolPathWayPoint;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CmbParser {


    private static final Logger LOGGER = Logger.getLogger(CmbParser.class.getName());

    public static CmbFile fromFile (File file) throws IOException, IllegalArgumentException {
        CmbHeader header;
        List<Layer> layers;
        int[] layersAddressTable;
        short checkSumPoly;
        byte[] checkSum;

        //read the cmb header
        byte[] fileContent = Files.readAllBytes(file.toPath());
        ByteBuffer cmb_buf =  ByteBuffer.wrap(fileContent);
        header = CmbHeader.fromByteBuffer(cmb_buf);

        int number_of_slices = header.numberOfSlices();
        LOGGER.log(Level.INFO, String.format("number of slices(layers)= %d", number_of_slices));

        int abs_address_of_Layer_table = header.addressOfLayersTable();
        LOGGER.log(Level.INFO, String.format("absolute address of layers address table= %x", abs_address_of_Layer_table));

        //set up the byte order
        cmb_buf.order(Command.getByteOrder());

        //reading layer addresses table
        layersAddressTable = new int[number_of_slices];
        cmb_buf.position(abs_address_of_Layer_table);
        for(int i = 0; i < number_of_slices; i++) {
            layersAddressTable[i]= cmb_buf.getInt();
            LOGGER.log(Level.FINER, String.format("slice #%d address(h)= %x%n",i + 1, layersAddressTable[i]));
        }

        checkSumPoly = cmb_buf.getShort();
        checkSum = new byte [6];
        cmb_buf.get(checkSum);

        //read all the layers using layers address table
        layers = new ArrayList<Layer>();
        for(int i = 0; i < number_of_slices; i++) {
            Layer new_layer = new Layer(i+1);
            cmb_buf.position(layersAddressTable[i]);
            byte command_code;
            while((command_code= cmb_buf.get()) != EndOfLayer.CODE) {
                if(command_code != ToolPathWayPoint.CODE) {
                    byte[] data = new byte[Command.getCommandDataSizeInBytes(command_code, (byte)0)];
                    cmb_buf.get(data);
                    new_layer.addCommand(Command.parseData(command_code, data));
                }else {
                    byte sub_code = cmb_buf.get();
                    byte[] data = new byte[Command.getCommandDataSizeInBytes(command_code, sub_code)];
                    cmb_buf.get(data);
                    new_layer.addCommand(Command.parseData(command_code, sub_code, data));
                }
            }
            new_layer.addCommand(new EndOfLayer());
            if(i == number_of_slices -1)// last layer detected
                new_layer.addCommand(new EndOfProgram()); //add the end of program command
            layers.add(new_layer);
            LOGGER.log(Level.FINE, String.format("layer %d contains %d commands",i + 1,new_layer.getSize()));
        }

        return new CmbFile(header,layers, layersAddressTable,checkSumPoly,checkSum);
    }
}
