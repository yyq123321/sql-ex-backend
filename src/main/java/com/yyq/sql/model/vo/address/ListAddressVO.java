package com.yyq.sql.model.vo.address;

import com.yyq.sql.model.domain.Address;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/12/8 16:01
 */
@Data
public class ListAddressVO {

    private List<Address> list;

    private Long total;

    public ListAddressVO(List<Address> list, Long total) {
        this.list = list;
        this.total = total;
    }
}
