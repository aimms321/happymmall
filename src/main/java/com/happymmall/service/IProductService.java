package com.happymmall.service;

import com.happymmall.common.ServerResponse;
import com.happymmall.pojo.Product;

/**
 * Created by user on 2017-10-18.
 */
public interface IProductService {
    ServerResponse saveOrUpdateProduct(Product product);
    ServerResponse<String> setSaleStatus(Integer productId, Integer status);
}
