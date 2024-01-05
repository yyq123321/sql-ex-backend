package com.yyq.sql.service;

import com.yyq.sql.common.PageRequest;
import com.yyq.sql.model.domain.Address;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yyq.sql.model.dto.address.AddressAddRequest;
import com.yyq.sql.model.dto.address.ListAddressPageRequest;
import com.yyq.sql.model.vo.address.ListAddressVO;

/**
* @author Acer
* @description 针对表【address】的数据库操作Service
* @createDate 2023-11-24 15:30:03
*/
public interface AddressService extends IService<Address> {

    Long add(AddressAddRequest addressAddRequest);

    ListAddressVO list(ListAddressPageRequest addressQueryRequest);

    Integer updateAddress(Address address);

    Integer deleteById(long id);
}
