package vn.com.blowjob.validator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import vn.com.blowjob.bean.Product;
import vn.com.blowjob.common.BuscuConst;

/**
 * WriteFile
 *
 * @version 1.0
 * @author Namnh30
 */
public class WriteFile {

    /**
     * writeFile
     *
     * @param Error
     */
    public static boolean writeFile(List<Product> Error) {
        String errorCsvFile = BuscuConst.ERROR_PATH;
        FileWriter fileWriter = null;
        if (Error.isEmpty() != true) {
            try {
                // delete the content of text file
                File f = new File(errorCsvFile);
                if (f.exists()) {
                    // delete if exists
                    f.delete();
                }
                // process write file
                fileWriter = new FileWriter(errorCsvFile);
                for (Product product : Error) {
                    fileWriter.append(product.getProductCode());
                    fileWriter.append(BuscuConst.COMA);
                    fileWriter.append(product.getBranchName());
                    fileWriter.append(BuscuConst.COMA);
                    fileWriter.append(product.getTitle());
                    fileWriter.append(BuscuConst.COMA);
                    fileWriter.append(String.valueOf(product.getPrice()));
                    fileWriter.append(BuscuConst.COMA);
                    fileWriter.append(product.getKey1());
                    fileWriter.append(BuscuConst.COMA);
                    fileWriter.append(product.getKey2());
                    fileWriter.append(BuscuConst.COMA);
                    fileWriter.append(String.valueOf(product.getDescription()));
                    fileWriter.append(BuscuConst.NEW_LINE_SEPARATOR);
                }
            } catch (Exception e) {
                System.out.println(BuscuConst.ERROR_WRITER);
                e.printStackTrace();
                return false;
            } finally {
                try {
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println(BuscuConst.ERROR_FLUSH_CLOSE);
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

}
