package com.yyq.sql.controller;


import com.yyq.sql.common.BaseResponse;
import com.yyq.sql.common.PageRequest;
import com.yyq.sql.common.ResultUtils;
import com.yyq.sql.model.domain.Address;
import com.yyq.sql.model.dto.address.AddressAddRequest;
import com.yyq.sql.model.dto.address.AddressUpdateRequest;
import com.yyq.sql.model.dto.address.ListAddressPageRequest;
import com.yyq.sql.model.vo.address.AddressVO;
import com.yyq.sql.model.vo.address.ListAddressVO;
import com.yyq.sql.model.vo.food.FoodsVO;
import com.yyq.sql.service.AddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/11/24
 */
@RestController
@RequestMapping("/address")
@CrossOrigin
public class AddressController {

    @Resource
    private AddressService addressService;

    @PostMapping("/add")
    public BaseResponse<Long> addAddress(@RequestBody AddressAddRequest addressAddRequest) {
        Long result = addressService.add(addressAddRequest);
        return ResultUtils.success(result);
    }

    @PostMapping("/update")
    public BaseResponse<Integer> updateAddress(@RequestBody AddressUpdateRequest addressUpdateRequest) {
        Address address = new Address();
        BeanUtils.copyProperties(addressUpdateRequest, address);
        Integer result = addressService.updateAddress(address);
        return ResultUtils.success(result);
    }

    @DeleteMapping("/update")
    public BaseResponse<Integer> deleteAddressById(long id) {
        Integer result = addressService.deleteById(id);
        return ResultUtils.success(result);
    }

    @GetMapping("/get")
    public BaseResponse<Address> getAddressById(long id) {
        Address result = addressService.getById(id);
        return ResultUtils.success(result);
    }

    @GetMapping("/list")
    public BaseResponse<ListAddressVO> listAddress(ListAddressPageRequest addressQueryRequest) {
        ListAddressVO list = addressService.list(addressQueryRequest);
        return ResultUtils.success(list);
    }


}

