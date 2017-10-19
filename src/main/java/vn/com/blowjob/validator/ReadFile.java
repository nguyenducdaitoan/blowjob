package vn.com.blowjob.validator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import vn.com.blowjob.bean.Product;
import vn.com.blowjob.common.BuscuConst;
import vn.com.blowjob.service.ProductService;

/**
 * ReadFile Class
 *
 * @version 1.0
 * @author Namnh30
 */
public class ReadFile {

    /**
     * readFile Method
     *
     * @param File f
     * @param ProductService productService
     */
    public static void readFile(File f, ProductService productService, List<Product> productErrorList) {
        File file = f;
        String csvFile = f.getPath().toString();
        List<Product> productList = new ArrayList<Product>();
        //List<Product> productErrorList = new ArrayList<Product>();
        String cvsSplitBy = BuscuConst.COMA;
        BufferedReader br = null;
        String extension = BuscuConst.BLANK;

        // check file extension
        int ext = file.getAbsolutePath().lastIndexOf('.');

        if (ext >= BuscuConst.VAL_0) {
            extension = file.getAbsolutePath().substring(ext + BuscuConst.VAL_1);
            if (extension.equals(BuscuConst.CSV)) {

                // check File size
                long length = file.length();
                long lengthMB = length / BuscuConst.VAL_1M_TO_BYTE;
                if (lengthMB <= BuscuConst.VAL_100) {
                    System.out.println(lengthMB + BuscuConst.MEGABYTE);
                    try {
                        br = new BufferedReader(new FileReader(new File(csvFile)));
                        String line;
                        Product product = null;
                        String[] sv;

                        // Read File
                        while ((line = br.readLine()) != null) {
                            product = new Product();

                            // Split line
                            sv = line.split(cvsSplitBy);

                            // check format File
                            if (sv.length == BuscuConst.VAL_7) {

                                // add to Object product
                                product.setProductCode(sv[BuscuConst.VAL_0]);
                                product.setBranchName(sv[BuscuConst.VAL_1]);
                                product.setTitle(sv[BuscuConst.VAL_2]);
                                product.setPrice(Double.parseDouble((sv[BuscuConst.VAL_3])));
                                product.setKey1(sv[BuscuConst.VAL_4]);
                                product.setKey2(sv[BuscuConst.VAL_5]);
                                product.setDescription(sv[BuscuConst.VAL_6]);
                            } else {
                                System.out.println(BuscuConst.WRONG_FORMAT);
                            }

                            // Validate file
                            boolean checkFile = ValidateFile.validate(product);

                            if (checkFile == true) {

                                // Add to ProductList
                                System.out.println(product.getProductCode() + BuscuConst.HYPHEN
                                        + product.getBranchName() + BuscuConst.HYPHEN
                                        + product.getTitle() + BuscuConst.HYPHEN
                                        + product.getPrice() + BuscuConst.HYPHEN
                                        + product.getKey1() + BuscuConst.HYPHEN
                                        + product.getKey2() + BuscuConst.HYPHEN
                                        + product.getDescription());
                                productList.add(product);
                            } else {

                                // Add error line to ErrorList
                                System.out.println(BuscuConst.ERROR_LINE
                                        + product.getProductCode() + BuscuConst.HYPHEN
                                        + product.getBranchName() + BuscuConst.HYPHEN
                                        + product.getTitle() + BuscuConst.HYPHEN
                                        + product.getPrice() + BuscuConst.HYPHEN
                                        + product.getKey2() + BuscuConst.HYPHEN
                                        + product.getKey2() + BuscuConst.HYPHEN
                                        + product.getDescription());

                                productErrorList.add(product);

                                System.out.println(BuscuConst.WRONG_INFO);
                            }
                            if (productList.size() == BuscuConst.VAL_6999) {
                                // add listProduct to Database
                                productService.insert(productList);

                                // clear listProduct
                                productList.clear();
                            }
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    finally {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    System.out.println(BuscuConst.FILE_LARGE + lengthMB + BuscuConst.MEGABYTE);
                }
            } else {
                System.out.println(BuscuConst.WRONG_EXTENSION);
            }
                // add listProduct to Database if ListProduct not null
                // write file Product error
                WriteFile.writeFile(productErrorList);

            try {
                productService.insert(productList);
            } catch (Exception e) {
                e.printStackTrace();
            }
            productList.clear();
        } else {
            System.out.println(BuscuConst.WRONG_EXTENSION);
        }
    }
}
