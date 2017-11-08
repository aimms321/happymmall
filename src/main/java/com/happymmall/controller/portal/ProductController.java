package com.happymmall.controller.portal;

import com.happymmall.common.ServerResponse;
import com.happymmall.pojo.Product;
import com.happymmall.service.IProductService;
import com.happymmall.vo.ProductDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by user on 2017-11-07.
 */
@Controller
@RequestMapping("/product/")
public class ProductController {
    @Autowired
    private IProductService iProductService;

    @RequestMapping(value = "detail.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse detail(Integer productId) {
        return iProductService.productDetail(productId);
    }

    public ServerResponse list(@RequestParam(value = "keyword", required = false) String keyword, @RequestParam(value = "categoryId",required = false) Integer categoryId, @RequestParam(value="pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue ="10") int pageSize) {

    }
}
