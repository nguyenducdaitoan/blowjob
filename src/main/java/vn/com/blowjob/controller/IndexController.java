package vn.com.blowjob.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.com.blowjob.bean.FileForm;
import vn.com.blowjob.bean.Pager;
import vn.com.blowjob.bean.Product;
import vn.com.blowjob.common.BuscuConst;
import vn.com.blowjob.bean.ErrorException;
import vn.com.blowjob.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import vn.com.blowjob.validator.FileValidator;
import vn.com.blowjob.validator.ReadFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * IndexController Class
 *
 * @version 1.0
 * @author ToanNDD
 */
@Controller
public class IndexController {

    @Autowired
    private ProductService productService;

    @Autowired
    FileValidator validator;

    @Autowired
    ReadFile readFile;

    private final MessageSource messageSource;

    protected  List<ErrorException> errorExceptionList;

    List<Product> productErrorList = new ArrayList<Product>();

    List<Product> productList = new ArrayList<Product>();

    private final Logger logger = Logger.getLogger(this.getClass());

    /**
     * Constructor
     *
     * @param messageSource
     */
    @Autowired
    public IndexController(MessageSource messageSource) {
        this.messageSource = messageSource;
        this.errorExceptionList = new ArrayList<>();
    }

    /**
     * index method
     *
     * @param pager
     * @param model
     * @param request
     * @param response
     * @return String
     */
    @RequestMapping(value = BuscuConst.REQ_INDEX, method = RequestMethod.GET)
    public String index(Pager pager, Model model, HttpServletRequest request, HttpServletResponse response){
        return BuscuConst.LIST_PRODUCT;
    }

    /**
     * listProduct method
     *
     * @param pager
     * @param model
     * @param request
     * @param response
     * @return String
     */
    @RequestMapping(value = BuscuConst.REQ_LIST_PRODUCT, method = RequestMethod.GET)
    public String listProduct(Pager pager, Model model, HttpServletRequest request, HttpServletResponse response){
        List<Product> pagingProductList = new ArrayList<Product>();

        int pageNumber = BuscuConst.VAL_1;
        int recordsPerPage = BuscuConst.VAL_10;
        int total = BuscuConst.VAL_0;
        int noOfPages;

        if(pager.getPage() == BuscuConst.VAL_0) {
            pageNumber = BuscuConst.VAL_1;
        } else {
            pageNumber = pager.getPage();
        }
        try {
            total = productService.countTotalProduct();

            pagingProductList = productService.selectProductByPage(pageNumber);

        } catch (Exception e) {
            logger.error(e);
        }

        noOfPages = (int) Math.ceil(total * BuscuConst.VAL_1_0 / recordsPerPage);

        model.addAttribute(BuscuConst.PAGING_PRODUCT_LIST, pagingProductList);
        model.addAttribute(BuscuConst.ERROR_PRODUCT_LIST, productErrorList);
        model.addAttribute(BuscuConst.NO_OF_PAGE, noOfPages);
        model.addAttribute(BuscuConst.CURRENT_PAGE, pageNumber);

        model.addAttribute(BuscuConst.ERROR_EXCEPTION_LIST, errorExceptionList);

        return BuscuConst.LIST_PRODUCT;
    }

    /**
     * getForm Method
     *
     * @param model
     * @return String
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getForm(Model model) {
        FileForm fileModel = new FileForm();
        model.addAttribute(BuscuConst.FILE, fileModel);
        return BuscuConst.FILE;
    }

    /**
     * fileUploaded Method
     *
     * @param csvFile
     * @param model
     * @param file
     * @param result
     * @param request
     * @param response
     * @return String
     */
    @RequestMapping(value = BuscuConst.REQ_UPLOAD, method = RequestMethod.POST)
    public String fileUploaded(@ModelAttribute("csvFile") FileForm csvFile, Model model, @Validated FileForm file,
                               BindingResult result, HttpServletRequest request, HttpServletResponse response) {
        errorExceptionList.clear();

        if (result.hasErrors()) {
            return BuscuConst.FILE;
        } else {

            MultipartFile multipartFile = file.getFile();
            String rootPath = request.getSession().getServletContext().getRealPath(BuscuConst.SLASH);
            File dir = new File(rootPath + File.separator + BuscuConst.UPLOADED_FILE);

            System.out.println(rootPath + File.separator + BuscuConst.UPLOADED_FILE);

            if (!dir.exists()) {
                dir.mkdirs();
            }
            File serverFile = new File(dir.getAbsolutePath() + File.separator + multipartFile.getOriginalFilename());

            try (InputStream is = multipartFile.getInputStream();
                 BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {

                int i;
                while ((i = is.read()) != BuscuConst.VAL_MINUS_1) {
                    stream.write(i);
                }
                stream.flush();
            } catch (IOException e) {
                errorExceptionList.add(new ErrorException(
                        BuscuConst.ERROR_CODE_ERR0001, messageSource.getMessage(BuscuConst.ERROR_CODE_ERR0001, null, null)));
                return BuscuConst.FILE_EXEPTION;
            }
            ReadFile.readFile(serverFile, productService, productErrorList);
        }

        try {
            response.sendRedirect(BuscuConst.LIST_PRODUCT);
        } catch (IOException e) {
            e.printStackTrace();
            errorExceptionList.add(new ErrorException(
                    BuscuConst.ERROR_CODE_WRN0002,messageSource.getMessage(BuscuConst.ERROR_CODE_WRN0002, null, null)));
        }

        errorExceptionList.add(new ErrorException(
                BuscuConst.ERROR_CODE_INF0007, messageSource.getMessage(BuscuConst.ERROR_CODE_INF0007, null, null)));

        return BuscuConst.LIST_PRODUCT;
    }

    /**
     * process Method
     *
     * @param request
     * @param response
     * @return void
     */
    @RequestMapping(value = BuscuConst.REQ_PROCESS, method = RequestMethod.GET)
    public void process(HttpServletRequest request, HttpServletResponse response) {

        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

        //WebDriver driver = new ChromeDriver();

        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File("C:\\extension_2_6_1.crx"));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        //start driver in incognito mode
        capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
        capabilities.setCapability("chrome.switches", Arrays.asList("--incognito"));
        ChromeDriver driver = new ChromeDriver(capabilities);
        driver.manage().window().maximize();

        driver.get("https://www.amazon.com");
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement element1 = driver.findElement(By.xpath("//*[@id=\"nav-link-shopall\"]"));
        //WebElement element2 = driver.findElement(By.xpath("//*[@id=\"nav-signin-tooltip\"]/a/span"));
        Actions actions1 = new Actions(driver);
        actions1.moveToElement(element1);
        actions1.click();

        String asin = "";
        String branch = "";
        String title = "";
        Double price = 0d;
        String key1 = "";
        String key2 = "";
        String description = "";

        String csvFile = "C:\\asin.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] codeList = line.split(cvsSplitBy);

                System.out.println("Current shirt [asin= " + codeList[0] + " , name=" + codeList[0] + "]");

                driver.get("https://www.amazon.com/dp/" + codeList[0]);

                asin = codeList[0];

                //if (driver.findElements(By.xpath("//*[@id=\"brand\"]")).size() > 0) {
                if (!driver.findElements(By.xpath("//*[@id=\"brand\"]")).isEmpty()) {
                    branch = driver.findElement(By.xpath("//*[@id=\"brand\"]")).getText();
                }
                //if (driver.findElements(By.xpath("//*[@id=\"productTitle\"]")).size() > 0) {
                if (!driver.findElements(By.xpath("//*[@id=\"productTitle\"]")).isEmpty()) {
                    title = driver.findElement(By.xpath("//*[@id=\"productTitle\"]")).getText();
                }
                //if (driver.findElements(By.xpath("//*[@id=\"priceblock_ourprice\"]")).size() > 0) {
                if (!driver.findElements(By.xpath("//*[@id=\"priceblock_ourprice\"]")).isEmpty()) {
                    price = Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"priceblock_ourprice\"]")).getText().substring(1,6));
                }
                //if (driver.findElements(By.xpath("//*[@id=\"feature-bullets\"]/ul/li[4]/span")).size() > 0) {
                if (!driver.findElements(By.xpath("//*[@id=\"feature-bullets\"]/ul/li[4]/span")).isEmpty()) {
                    key1 = driver.findElement(By.xpath("//*[@id=\"feature-bullets\"]/ul/li[4]/span")).getText();
                }
                //if (driver.findElements(By.xpath("//*[@id=\"feature-bullets\"]/ul/li[5]/span")).size() > 0) {
                if (!driver.findElements(By.xpath("//*[@id=\"feature-bullets\"]/ul/li[5]/span")).isEmpty()) {
                    key2 = driver.findElement(By.xpath("//*[@id=\"feature-bullets\"]/ul/li[5]/span")).getText();
                }
                //if (driver.findElements(By.xpath("//*[@id=\"productDescription\"]/p")).size() > 0) {
                if (!driver.findElements(By.xpath("//*[@id=\"productDescription\"]/p")).isEmpty()){
                    description = driver.findElement(By.xpath("//*[@id=\"productDescription\"]/p")).getText();
                }
                Product product = new Product(asin, branch, title, price, key1, key2, description);
                productList.add(product);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // add listProduct to Database
            try {
                productService.insert(productList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
