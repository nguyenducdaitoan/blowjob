package vn.com.blowjob.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
*
* @author Namnh30
*/
import vn.com.blowjob.bean.Product;
import vn.com.blowjob.common.BuscuConst;

/**
 * ValidateFile Class
 *
 * @version 1.0
 * @author NamNH30
 *
 */
public class ValidateFile {

    /**
     * validate method
     *
     * @param check
     * @return boolean
     */
    public static boolean validate(Product check) {

        boolean checkFlg = false;
        Product product = check;

        for (int i = 0; i < BuscuConst.VAL_7; i++) {

            boolean checkCode = validateStudenCode(product.getProductCode());
            boolean checkFName = validateName(product.getBranchName());
            boolean checkLName = validateName(product.getTitle());
            boolean checkprice = validateprice(product.getPrice());
            boolean checkKey1 = validateName(product.getKey1());
            boolean checkKey2 = validateName(product.getKey2());
            boolean checkDescription = validateName(product.getDescription());

            if (checkCode == false) {
                checkFlg = false;
            } else if (checkFName == false){
                checkFlg = false;
            } else if (checkLName == false){
                checkFlg = false;
            } else if (checkprice == false){
                checkFlg = false;
            } else if (checkKey1 == false){
                checkFlg = false;
            } else if (checkKey2 == false){
                checkFlg = false;
            }
            else if (checkDescription == false){
                checkFlg = false;
            } else {
                checkFlg = true;
            }
        }

        return checkFlg;
    }

    /**
     * validateStudenCode
     *
     * @param productCode
     * @return boolean
     */
    public static boolean validateStudenCode(String productCode) {
        Pattern p = Pattern.compile(BuscuConst.PAT_VALID_PRODUCT_CODE);
        Matcher m = p.matcher(productCode);
        boolean b = m.find();
        if (b == true) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * validateName
     *
     * @param name
     * @return boolean
     */
    public static boolean validateName(String name) {
        Pattern p = Pattern.compile(BuscuConst.PAT_VALID_PRODUCT_NAME);
        Matcher m = p.matcher(name);
        boolean b = m.find();
        if (b == true || name.length() >= BuscuConst.VAL_100) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * validateprice
     *
     * @param price
     * @return boolean
     */
    public static boolean validateprice(Double price) {
        if (price == BuscuConst.VAL_0 || price == BuscuConst.VAL_1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * validateDate
     *
     * @param saleRank
     * @return boolean
     */
    public static boolean validateDate(Double saleRank) {
        String dateFormat = BuscuConst.PAT_VALID_PRODUCT_DATE;

        if (saleRank == null) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);

        if (saleRank == BuscuConst.VAL_0 || saleRank == BuscuConst.VAL_1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * validatePhone
     *
     * @param image1
     * @return boolean
     */
    public static boolean validatePhone(String image1) {
        Pattern p = Pattern.compile(BuscuConst.PAT_VALID_PRODUCT_PHONE);
        Matcher m = p.matcher(image1);
        boolean b = m.find();
        if (b == true) {
            return false;
        } else {
            return true;
        }
    }
}
