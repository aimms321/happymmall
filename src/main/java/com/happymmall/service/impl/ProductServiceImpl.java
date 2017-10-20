package com.happymmall.service.impl;

import com.happymmall.common.ResponseCode;
import com.happymmall.common.ServerResponse;
import com.happymmall.dao.ProductMapper;
import com.happymmall.pojo.Product;
import com.happymmall.service.IProductService;
import com.happymmall.util.PropertiesUtil;
import com.happymmall.vo.ProductDetailVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2017-10-18.
 */

@Service("iProductService")
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;
    public ServerResponse saveOrUpdateProduct(Product product) {
        if (product != null) {
            if (StringUtils.isNotBlank(product.getSubImages())) {
                String[] subImageArray = product.getSubImages().split(",");
                if (subImageArray.length > 0) {
                    product.setMainImage(subImageArray[0]);
                }
            }
            if (product.getId() != null) {
                int resultCount = productMapper.updateByPrimaryKey(product);
                if (resultCount > 0) {
                    return ServerResponse.createBySuccessMessage("更新产品成功");
                }
                return ServerResponse.createByErrorMessage("更新产品失败");
            } else {
                int resultCount = productMapper.insert(product);
                if (resultCount > 0) {
                    return ServerResponse.createBySuccessMessage("添加产品成功");
                }
                return ServerResponse.createByErrorMessage("添加产品失败");
            }
        }
        return ServerResponse.createByErrorMessage("新增或修改产品参数不正确");
    }

    public ServerResponse<String> setSaleStatus(Integer productId, Integer status) {
        if (productId == null || status == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);
        int rowCount = productMapper.updateByPrimaryKeySelective(product);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("更改产品销售状态成功");
        }
        return ServerResponse.createByErrorMessage("更改产品销售状态失败");
    }

    public ServerResponse<Object> manageProductDetail(Integer productId) {
        if (productId == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product == null) {
            return ServerResponse.createByErrorMessage("产品已下架或删除");
        }
        ProductDetailVo productDetailVo = new ProductDetailVo();


    }

    private ProductDetailVo assembleProductVo(Product product) {
        ProductDetailVo productDetailVo = new ProductDetailVo();
        productDetailVo.setId(product.getId());
        productDetailVo.setName(product.getName());
        productDetailVo.setSubtitle(product.getSubtitle());
        productDetailVo.setMainImage(product.getMainImage());
        productDetailVo.setSubImages(product.getSubImages());
        productDetailVo.setCategoryId(product.getCategoryId());
        productDetailVo.setDetail(product.getDetail());
        productDetailVo.setPrice(product.getPrice());
        productDetailVo.setStock(product.getStock());
        productDetailVo.setStatus(product.getStatus());
        productDetailVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix"));

        //parentCategoryId
        //createTime
        //updateTime

    }
}

