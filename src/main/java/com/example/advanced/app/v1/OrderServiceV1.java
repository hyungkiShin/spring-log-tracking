package com.example.advanced.app.v1;

import com.example.advanced.trace.HelloTrace.HelloTraceV1;
import com.example.advanced.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

    private final OrderRepositoryV1 orderRepository;
    private final HelloTraceV1 trace;

    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderService.orderItem()");
            orderRepository.save(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e); // 예외를 먹어버리면 정상흐름으로 된다.
            throw e; // 예외를 꼭 다시 던져주어야 한다.
        }
    }
}
