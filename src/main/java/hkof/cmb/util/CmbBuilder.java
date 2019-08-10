package hkof.cmb.util;

import java.io.FileOutputStream;
import java.io.IOException;
import hkof.cmb.CmbFile;

public class CmbBuilder {

    public static void writeCmbToFile(CmbFile cmb, String path) throws IOException {
        FileOutputStream stream = new FileOutputStream(path);
        try {
            stream.write(cmb.getBytes());
            stream.flush();
        } finally {
            stream.close();
        }

    }


}
