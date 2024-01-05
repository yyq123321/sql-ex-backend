package com.yyq.sql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyq.sql.common.PageRequest;
import com.yyq.sql.model.domain.Address;
import com.yyq.sql.model.dto.address.AddressAddRequest;
import com.yyq.sql.model.dto.address.ListAddressPageRequest;
import com.yyq.sql.model.vo.address.ListAddressVO;
import com.yyq.sql.service.AddressService;
import com.yyq.sql.mapper.AddressMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author Acer
* @description 针对表【address】的数据库操作Service实现
* @createDate 2023-11-24 15:30:03
*/
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address>
implements AddressService{

    @Resource
    private AddressMapper addressMapper;

    @Override
    public Long add(AddressAddRequest addressAddRequest) {
        Address address = new Address();
        BeanUtils.copyProperties(addressAddRequest, address);
        return addressMapper.insertAddress(address);
    }

    @Override
    public ListAddressVO list(ListAddressPageRequest addressQueryRequest) {
        long current = addressQueryRequest.getCurrent();
        long pageSize = addressQueryRequest.getPageSize();
        Long customerId = addressQueryRequest.getCustomerId();
        List<Address> addresses = addressMapper.listByPage(customerId, (current - 1) * pageSize,
                pageSize);
        Long count = addressMapper.count(customerId);
        return new ListAddressVO(addresses, count);
    }

    @Override
    public Integer updateAddress(Address address) {
        return addressMapper.updateAddress(address);
    }

    @Override
    public Integer deleteById(long id) {
        return addressMapper.deleteAddressById(id);
    }
}
