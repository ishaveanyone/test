package com.dist.Subselect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-15 11:30
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */

@Service
public class CombineService {
    @Autowired
    private CombineRepository combineRepository;

    @Autowired
    private ViewRepository viewRepository;

    public List<CombineEntity> getAllCombine(){
        return StreamSupport.stream(combineRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    public List<ViewEntity> getAllView(){
        return StreamSupport.stream(viewRepository.findAll().spliterator(),false).collect(Collectors.toList());

    }

}
