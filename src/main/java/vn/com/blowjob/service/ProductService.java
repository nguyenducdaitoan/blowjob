package vn.com.blowjob.service;

import java.util.List;
import vn.com.blowjob.bean.Product;
import vn.com.blowjob.common.BuscuConst;
import vn.com.blowjob.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * ProductService Class
 *
 * @version 1.0
 * @author ToanNDD
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProductService {

    @Autowired
    private ProductMapper productMapper;


    /**
     * @throws Exception
     */
    public void truncate() throws Exception {
        productMapper.truncate();
    }

    /**
     * @param productList
     * @throws Exception
     */
    public void insert(List<Product> productList) throws Exception {

        for (Product product : productList) {
            if (productMapper.selectProductByCode(product.getProductCode()) > 0 ) {
                productMapper.update(product);
                //Display message ID WR0004
                //Add record vao {Duplicate Product List}
            }
            else {
                productMapper.insert(product);
            }
        }
    }

    /**
     * @throws Exception
     */
    public List<Product> select() throws Exception {
        return productMapper.selectProduct();
    }

    /**
     * @throws Exception
     */
    public List<Product> selectProductByPage(int pageNumber) throws Exception {
        return productMapper.selectProductByPage((pageNumber - BuscuConst.VAL_1) * BuscuConst.VAL_10);
    }

    /**
     * @throws Exception
     */
    public int countTotalProduct() throws Exception {
        return productMapper.countTotalProduct();
    }
}
