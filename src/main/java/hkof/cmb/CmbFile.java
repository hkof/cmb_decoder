package hkof.cmb;


import hkof.cmb.commands.Command;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class CmbFile {

    CmbHeader header;
    List<Layer> layers;
    int[] layersAddressTable;
    short checkSumPoly;
    byte[] checkSum;
    byte[] endOfFile = {0,0,0,0};

    public CmbFile (CmbHeader header,
                    List<Layer> layers,
                    int[] layersAddressTable,
                    short checkSumPoly,
                    byte[] checkSum) {
        this.header = header;
        this.layers = layers;
        this.layersAddressTable = layersAddressTable;
        this.checkSumPoly = checkSumPoly;
        this.checkSum = checkSum;
    }

    public CmbHeader getHeader() {
        return header;
    }

    public void setHeader(CmbHeader header) {
        this.header = header;
    }

    public List<Layer> getLayers() {
        return layers;
    }

    public void setLayers(List<Layer> layers) {
        this.layers = layers;
    }

    public int[] getLayersAddressTable() {
        return layersAddressTable;
    }

    public void setLayersAddressTable(int[] layersAddressTable) {
        this.layersAddressTable = layersAddressTable;
    }

    public short getCheckSumPoly() {
        return checkSumPoly;
    }

    public void setCheckSumPoly(short checkSumPoly) {
        this.checkSumPoly = checkSumPoly;
    }

    public byte[] getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(byte[] checkSum) {
        this.checkSum = checkSum;
    }


    public byte[] getBytes(){
        //pack header
        byte[] headerBytes = getHeader().getBytes();


        //pack the cmb layers
        byte[] layersBytes;
        byte[] temp;
        List<ByteBuffer> layersBuffList = new ArrayList<ByteBuffer>();

        for (Layer layer : layers) {
            for (int i = 0; i < layer.getSize(); i++) {
                temp = layer.getCommand(i).getBytes();
                //no need to select the byte order here, since getBytes will do that for us
                layersBuffList.add(ByteBuffer.allocate(temp.length).put(temp));
            }
        }
        int totalLayersBytes = 0;
        for(ByteBuffer buff: layersBuffList){
            totalLayersBytes += buff.limit();
        }
        ByteBuffer layersBytesBuff = ByteBuffer.allocate(totalLayersBytes);
        for(ByteBuffer buff: layersBuffList){
            layersBytesBuff.put(buff.array());
        }
        layersBytes = layersBytesBuff.array();

        //pack layers address table
        byte[] layersAddressTableBytes;
        ByteBuffer layersAddressTableBtyesBuff = ByteBuffer.allocate(layersAddressTable.length * 4).order(Command.getByteOrder());
        for(int address: layersAddressTable){
            layersAddressTableBtyesBuff.putInt(address);
        }
        layersAddressTableBytes = layersAddressTableBtyesBuff.array();

        //pack checksum + end of file
        byte[] checksumEOFBytes = ByteBuffer.allocate(2 + 6 + 4).order(Command.getByteOrder()).putShort(checkSumPoly).put(checkSum).put(endOfFile).array();


        return ByteBuffer.allocate(headerBytes.length + layersBytes.length + layersAddressTableBytes.length + checksumEOFBytes.length)
                .put(headerBytes)
                .put(layersBytes)
                .put(layersAddressTableBytes)
                .put(checksumEOFBytes)
                .array();

    }

}
