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
import io.kaitai.struct.ByteBufferKaitaiStream;
import io.kaitai.struct.KaitaiStruct;
import io.kaitai.struct.KaitaiStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class CmbHeader extends KaitaiStruct {
    public static CmbHeader fromFile(String fileName) throws IOException {
        return new CmbHeader(new ByteBufferKaitaiStream(fileName));
    }

    public static CmbHeader fromByteBuffer(ByteBuffer buffer) throws IOException {
        return new CmbHeader(new ByteBufferKaitaiStream(buffer));
    }

    public CmbHeader(KaitaiStream _io) {
        this(_io, null, null);
    }

    public CmbHeader(KaitaiStream _io, KaitaiStruct _parent) {
        this(_io, _parent, null);
    }

    public CmbHeader(KaitaiStream _io, KaitaiStruct _parent, CmbHeader _root) {
        super(_io);
        this._parent = _parent;
        this._root = _root == null ? this : _root;
        _read();
    }
    private void _read() {
        this.magicNumber = this._io.readBytes(4);
        this.machineType = this._io.readBytes(4);
        this.sliceHeight = this._io.readF4le();
        this.partVolume = this._io.readF4le();
        this.suppVolume = this._io.readF4le();
        this.partMaterialNameLength = this._io.readS4le();
        this.partMaterial = new String(this._io.readBytes(partMaterialNameLength()), Charset.forName("UTF-8"));
        this.suppMaterialNameLength = this._io.readS4le();
        this.suppMaterial = new String(this._io.readBytes(suppMaterialNameLength()), Charset.forName("UTF-8"));
        this.boundingBoxMinX = this._io.readF4le();
        this.boundingBoxMinY = this._io.readF4le();
        this.boundingBoxMinZ = this._io.readF4le();
        this.boundingBoxMaxX = this._io.readF4le();
        this.boundingBoxMaxY = this._io.readF4le();
        this.boundingBoxMaxZ = this._io.readF4le();
        this.partAreaL = this._io.readF4le();
        this.partAreaR = this._io.readF4le();
        this.suppAreaL = this._io.readF4le();
        this.suppAreaR = this._io.readF4le();
        this.partWidthL = this._io.readF4le();
        this.partWidthR = this._io.readF4le();
        this.suppWidthL = this._io.readF4le();
        this.suppWidthR = this._io.readF4le();
        this.partAspectRatioL = this._io.readF4le();
        this.partAspectRatioR = this._io.readF4le();
        this.suppAspectRatioL = this._io.readF4le();
        this.suppAspectRatioR = this._io.readF4le();
        this.numberOfSlices = this._io.readS4le();
        this.addressOfLayersTable = this._io.readS4le();
        this.commentLength = this._io.readS4le();
        this.comment = new String(this._io.readBytes(commentLength()), Charset.forName("UTF-8"));
        this.numberOfPacksList = this._io.readS4le();
        this.packListNameLength = this._io.readS4le();
        this.packListName = new String(this._io.readBytes(packListNameLength()), Charset.forName("UTF-8"));
        this.versionLength = this._io.readS4le();
        this.version = new String(this._io.readBytes(versionLength()), Charset.forName("UTF-8"));
        this.encodeLength = this._io.readS4le();
        this.encode = new String(this._io.readBytes(encodeLength()), Charset.forName("UTF-8"));
        this.partTipSizeLength = this._io.readS4le();
        this.partTipSize = new String(this._io.readBytes(partTipSizeLength()), Charset.forName("UTF-8"));
        this.suppTipSizeLength = this._io.readS4le();
        this.suppTipSize = new String(this._io.readBytes(suppTipSizeLength()), Charset.forName("UTF-8"));
        this.buildTime = this._io.readS4le();
        this.fixPart1 = this._io.readBytes(1);
        this.fixLength = this._io.readS4le();
        this.fixPart2 = this._io.readBytes((((fixLength() - 2) * 8) + 22));
        this.countVerticesOutline = this._io.readS4le();
        layerOutlinePoints = new ArrayList<OutlinePoint>((int) (countVerticesOutline()));
        for (int i = 0; i < countVerticesOutline(); i++) {
            this.layerOutlinePoints.add(new OutlinePoint(this._io, this, _root));
        }
    }

    public byte[] getBytes(){
        List<ByteBuffer> bytes = new ArrayList<>();
        //bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).put());

        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).put(magicNumber));
        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).put(machineType));
        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putFloat(sliceHeight));
        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putFloat(partVolume));
        ;
        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putFloat(suppVolume));
        ;
        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putInt(partMaterialNameLength));
        ;
        bytes.add(ByteBuffer.wrap(partMaterial.getBytes(Charset.forName("UTF-8"))).order(Command.getByteOrder()));
        ;
        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putInt(suppMaterialNameLength));
        ;
        bytes.add(ByteBuffer.wrap(suppMaterial.getBytes(Charset.forName("UTF-8"))).order(Command.getByteOrder()));
        ;
        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putFloat(boundingBoxMinX));

        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putFloat(boundingBoxMinY));
        ;
        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putFloat(boundingBoxMinZ));
        ;
        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putFloat(boundingBoxMaxX));
        ;
        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putFloat(boundingBoxMaxY));
        ;
        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putFloat(boundingBoxMaxZ));
        ;
        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putFloat(partAreaL));
        ;
        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putFloat(partAreaR));
        ;
        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putFloat(suppAreaL));
        ;
        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putFloat(suppAreaR));
        ;
        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putFloat(partWidthL));
        ;
        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putFloat(partWidthR));
        ;
        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putFloat(suppWidthL));
        ;
        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putFloat(suppWidthR));
        ;
        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putFloat(partAspectRatioL));
        ;
        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putFloat(partAspectRatioR));
        ;
        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putFloat(suppAspectRatioL));
        ;
        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putFloat(suppAspectRatioR));
        ;

        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putInt(numberOfSlices));
        ;
        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putInt(addressOfLayersTable));
        ;
        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putInt(commentLength));
        ;
        bytes.add(ByteBuffer.wrap(comment.getBytes(Charset.forName("UTF-8"))).order(Command.getByteOrder()));

        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putInt(numberOfPacksList));
        ;
        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putInt(packListNameLength));
        ;
        bytes.add(ByteBuffer.wrap(packListName.getBytes(Charset.forName("UTF-8"))).order(Command.getByteOrder()));

        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putInt(versionLength));
        ;
        bytes.add(ByteBuffer.wrap(version.getBytes(Charset.forName("UTF-8"))).order(Command.getByteOrder()));

        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putInt(encodeLength));
        ;
        bytes.add(ByteBuffer.wrap(encode.getBytes(Charset.forName("UTF-8"))).order(Command.getByteOrder()));

        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putInt(partTipSizeLength));
        ;
        bytes.add(ByteBuffer.wrap(partTipSize.getBytes(Charset.forName("UTF-8"))).order(Command.getByteOrder()));

        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putInt(suppTipSizeLength));
        ;
        bytes.add(ByteBuffer.wrap(suppTipSize.getBytes(Charset.forName("UTF-8"))).order(Command.getByteOrder()));

        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putInt(buildTime));
        ;
        bytes.add(ByteBuffer.allocate(fixPart1.length).order(Command.getByteOrder()).put(fixPart1));
        ;
        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putInt(fixLength));
        ;
        bytes.add(ByteBuffer.allocate(fixPart2.length).order(Command.getByteOrder()).put(fixPart2));
        ;
        bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putInt(countVerticesOutline));
        ;
        
        //bytes.add(ByteBuffer.allocate(4).order(Command.getByteOrder()).putInt());
        for (OutlinePoint point: layerOutlinePoints){
            byte[] byteArr = point.getBytes();
            //no need to select the byte order here, since getBytes will do that for us
            bytes.add(ByteBuffer.allocate(byteArr.length).put(byteArr));
        }

        //measure total size
        int totalBufSize = 0;
        for(ByteBuffer buff: bytes){
            totalBufSize += buff.limit();
        }

        //create a bigger buffer to holder all the bytes
        ByteBuffer  totalBuf= ByteBuffer.allocate(totalBufSize);
        for(ByteBuffer buf: bytes){
            totalBuf.put(buf.array());
        }
        return totalBuf.array();
    }
    public static class OutlinePoint extends KaitaiStruct {
        public static OutlinePoint fromFile(String fileName) throws IOException {
            return new OutlinePoint(new ByteBufferKaitaiStream(fileName));
        }

        public OutlinePoint(KaitaiStream _io) {
            this(_io, null, null);
        }

        public OutlinePoint(KaitaiStream _io, CmbHeader _parent) {
            this(_io, _parent, null);
        }

        public OutlinePoint(KaitaiStream _io, CmbHeader _parent, CmbHeader _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.command = this._io.readBytes(1);
            this.x = this._io.readF4le();
            this.y = this._io.readF4le();
        }

        public byte[] getBytes(){
            ByteBuffer b1 = ByteBuffer.allocate(command.length).order(Command.getByteOrder()).put(command);
            ByteBuffer b2 = ByteBuffer.allocate(4).order(Command.getByteOrder()).putFloat(x);
            ByteBuffer b3 = ByteBuffer.allocate(4).order(Command.getByteOrder()).putFloat(y);
            return ByteBuffer.allocate(b1.limit() + b2.limit() + b3.limit()).put(b1.array()).put(b2.array()).put(b3.array()).array();
        }
        private byte[] command;
        private float x;
        private float y;
        private CmbHeader _root;
        private CmbHeader _parent;
        public byte[] command() { return command; }
        public float x() { return x; }
        public float y() { return y; }
        public CmbHeader _root() { return _root; }
        public CmbHeader _parent() { return _parent; }
    }
    private byte[] magicNumber;
    private byte[] machineType;
    private float sliceHeight;
    private float partVolume;
    private float suppVolume;
    private int partMaterialNameLength;
    private String partMaterial;
    private int suppMaterialNameLength;
    private String suppMaterial;
    private float boundingBoxMinX;
    private float boundingBoxMinY;
    private float boundingBoxMinZ;
    private float boundingBoxMaxX;
    private float boundingBoxMaxY;
    private float boundingBoxMaxZ;
    private float partAreaL;
    private float partAreaR;
    private float suppAreaL;
    private float suppAreaR;
    private float partWidthL;
    private float partWidthR;
    private float suppWidthL;
    private float suppWidthR;
    private float partAspectRatioL;
    private float partAspectRatioR;
    private float suppAspectRatioL;
    private float suppAspectRatioR;
    private int numberOfSlices;
    private int addressOfLayersTable;
    private int commentLength;
    private String comment;
    private int numberOfPacksList;
    private int packListNameLength;
    private String packListName;
    private int versionLength;
    private String version;
    private int encodeLength;
    private String encode;
    private int partTipSizeLength;
    private String partTipSize;
    private int suppTipSizeLength;
    private String suppTipSize;
    private int buildTime;
    private byte[] fixPart1;
    private int fixLength;
    private byte[] fixPart2;
    private int countVerticesOutline;
    private ArrayList<OutlinePoint> layerOutlinePoints;
    private CmbHeader _root;
    private KaitaiStruct _parent;
    public byte[] magicNumber() { return magicNumber; }
    public byte[] machineType() { return machineType; }
    public float sliceHeight() { return sliceHeight; }
    public float partVolume() { return partVolume; }
    public float suppVolume() { return suppVolume; }
    public int partMaterialNameLength() { return partMaterialNameLength; }
    public String partMaterial() { return partMaterial; }
    public int suppMaterialNameLength() { return suppMaterialNameLength; }
    public String suppMaterial() { return suppMaterial; }
    public float boundingBoxMinX() { return boundingBoxMinX; }
    public float boundingBoxMinY() { return boundingBoxMinY; }
    public float boundingBoxMinZ() { return boundingBoxMinZ; }
    public float boundingBoxMaxX() { return boundingBoxMaxX; }
    public float boundingBoxMaxY() { return boundingBoxMaxY; }
    public float boundingBoxMaxZ() { return boundingBoxMaxZ; }
    public float partAreaL() { return partAreaL; }
    public float partAreaR() { return partAreaR; }
    public float suppAreaL() { return suppAreaL; }
    public float suppAreaR() { return suppAreaR; }
    public float partWidthL() { return partWidthL; }
    public float partWidthR() { return partWidthR; }
    public float suppWidthL() { return suppWidthL; }
    public float suppWidthR() { return suppWidthR; }
    public float partAspectRatioL() { return partAspectRatioL; }
    public float partAspectRatioR() { return partAspectRatioR; }
    public float suppAspectRatioL() { return suppAspectRatioL; }
    public float suppAspectRatioR() { return suppAspectRatioR; }
    public int numberOfSlices() { return numberOfSlices; }
    public int addressOfLayersTable() { return addressOfLayersTable; }
    public int commentLength() { return commentLength; }
    public String comment() { return comment; }
    public int numberOfPacksList() { return numberOfPacksList; }
    public int packListNameLength() { return packListNameLength; }
    public String packListName() { return packListName; }
    public int versionLength() { return versionLength; }
    public String version() { return version; }
    public int encodeLength() { return encodeLength; }
    public String encode() { return encode; }
    public int partTipSizeLength() { return partTipSizeLength; }
    public String partTipSize() { return partTipSize; }
    public int suppTipSizeLength() { return suppTipSizeLength; }
    public String suppTipSize() { return suppTipSize; }
    public int buildTime() { return buildTime; }
    public byte[] fixPart1() { return fixPart1; }
    public int fixLength() { return fixLength; }
    public byte[] fixPart2() { return fixPart2; }
    public int countVerticesOutline() { return countVerticesOutline; }
    public ArrayList<OutlinePoint> layerOutlinePoints() { return layerOutlinePoints; }
    public CmbHeader _root() { return _root; }
    public KaitaiStruct _parent() { return _parent; }
} 
