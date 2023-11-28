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
