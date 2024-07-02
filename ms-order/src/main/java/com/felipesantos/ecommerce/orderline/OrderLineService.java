package com.felipesantos.ecommerce.orderline;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    @Transactional
    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        return orderLineRepository.save(orderLineMapper.toOrderLine(orderLineRequest)).getId() ;
    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return orderLineRepository.findAllByOrderId(orderId).stream()
                .map(orderLineMapper::fromOrderLine)
                .collect(Collectors.toList());
    }
}
