package com.happymmall.service;

import com.github.pagehelper.PageInfo;
import com.happymmall.common.ServerResponse;
import com.happymmall.pojo.Product;
import com.happymmall.vo.ProductDetailVo;

/**
 * Created by user on 2017-10-18.
 */
public interface IProductService {
    ServerResponse saveOrUpdateProduct(Product product);
    ServerResponse<String> setSaleStatus(Integer productId, Integer status);
    ServerResponse<ProductDetailVo> manageProductDetail(Integer productId);
    ServerResponse<PageInfo> getProductList(int pageNum, int pageSize);
    ServerResponse<PageInfo> searchProduct(String productName, Integer productId, Integer pageNum, Integer pageSize);
    ServerResponse<ProductDetailVo> productDetail(Integer productId);

}
