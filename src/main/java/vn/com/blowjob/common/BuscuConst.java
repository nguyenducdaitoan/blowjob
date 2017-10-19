package vn.com.blowjob.common;

/**
 * BuscuConst
 *
 * @version 1.0
 * @author MinhNV14
 *
 */
public class BuscuConst {

    // Other
    public static final String LIST_PRODUCT = "listProduct";
    public static final String PAGING_PRODUCT_LIST = "pagingProductList";
    public static final String NO_OF_PAGE = "noOfPages";
    public static final String CURRENT_PAGE = "currentPage";
    public static final String ERROR_EXCEPTION_LIST = "errorExceptionList";
    public static final String ERROR_PRODUCT_LIST = "productErrorList";
    public static final String FILE = "file";
    public static final String VALID_DOT_FILE = "valid.file";
    public static final String FILE_EXEPTION = "fileException";
    public static final String UPLOADED_FILE = "uploadedfile";
    public static final String COMA = ",";
    public static final String BLANK = "";
    public static final String CSV = "csv";
    public static final String SLASH = "/";
    public static final String HYPHEN = " - ";
    public static final String NEW_LINE_SEPARATOR = "\n";
    public static final String ERROR_PATH = "D:\\ErrorList.csv";

    // Request Mapping
    public static final String REQ_UPLOAD = "/upload";
    public static final String REQ_PROCESS = "/process";
    public static final String REQ_INDEX = "/index";
    public static final String REQ_LIST_PRODUCT = "/listProduct";

    // Code
    public static final String ERROR_CODE_ERR0001 = "ERR0001";
    public static final String ERROR_CODE_WRN0002 = "WRN0002";
    public static final String ERROR_CODE_INF0007 = "INFO007";

    // Value
    public static final int VAL_0 = 0;
    public static final int VAL_1 = 1;
    public static final int VAL_2 = 2;
    public static final int VAL_3 = 3;
    public static final int VAL_4 = 4;
    public static final int VAL_5 = 5;
    public static final int VAL_6 = 6;
    public static final int VAL_7 = 7;
    public static final int VAL_MINUS_1 = -1;
    public static final int VAL_10 = 10;
    public static final int VAL_100 = 100;
    public static final int VAL_6999 = 6999;
    public static final int VAL_1M_TO_BYTE = 1048576;
    public static final double VAL_1_0 = 1.0;

    // Message
    public static final String MEGABYTE = " MB";
    public static final String WRONG_FORMAT = "Wrong Format!!!";
    public static final String WRONG_EXTENSION = "Wrong File extension!!!";
    public static final String WRONG_INFO = "Wrong Information!!!";
    public static final String FILE_LARGE = "File Too Large !!!";
    public static final String ERROR_LINE = "Error Line : ";
    public static final String ERROR_WRITER = "Error in CsvFileWriter !!!";
    public static final String ERROR_FLUSH_CLOSE = "Error while flushing/closing fileWriter !!!";

    // Pattern check
    public static final String PAT_VALID_PRODUCT_CODE = "[^A-Za-z 0-9]";
    public static final String PAT_VALID_PRODUCT_NAME = "[^A-Z a-z]";
    public static final String PAT_VALID_PRODUCT_DATE = "yyyy-MM-dd";
    public static final String PAT_VALID_PRODUCT_PHONE = "[^0-9]";
}
