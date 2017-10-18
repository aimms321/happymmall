package com.happymmall.service.impl;

import com.happymmall.common.ServerResponse;
import com.happymmall.pojo.Product;
import com.happymmall.service.IProductService;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2017-10-18.
 */
@Service("iProductService")
public class ProductServiceImpl implements IProductService {
    public ServerResponse saveOrUpdataProduct(Product product) {
        if (product != null) {

        }
        return ServerResponse.createByErrorMessage("新增或修改产品参数不正确");
    }
}
