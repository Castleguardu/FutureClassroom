package com.FutureClassroom.order.service.impl;


import com.FutureClassroom.order.mapper.OrderDetailMapper;
import com.FutureClassroom.order.service.OrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.futureClassroom.ftcr.model.order.OrderDetail;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单明细 订单明细 服务实现类
 * </p>
 *
 * @author zhw
 * @since 2023-04-17
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}
