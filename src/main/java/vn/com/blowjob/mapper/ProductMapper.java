package vn.com.blowjob.mapper;

import java.util.List;
import vn.com.blowjob.bean.Product;

/**
 * ProductMapper Interface
 *
 * @version 1.0
 * @author ToanNDD
 */
public interface ProductMapper {

    public void truncate();

    public void insert(Product product);

    public void update(Product product);

    public int selectProductByCode(String productCode);

    public List<Product> selectProduct();

    public List<Product> selectProductByPage(int pageNumber);

    public int countTotalProduct();
}
