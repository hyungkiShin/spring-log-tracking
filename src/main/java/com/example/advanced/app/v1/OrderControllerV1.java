package com.example.advanced.app.v1;

import com.example.advanced.trace.HelloTrace.HelloTraceV1;
import com.example.advanced.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

    private final OrderServiceV1 orderService;
    private final HelloTraceV1 trace;

    @GetMapping("/v1/request")
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            orderService.orderItem(itemId);
            trace.end(status);
            return "ok";
        } catch (Exception e) {
            trace.exception(status, e); // 예외를 먹어버리면 정상흐름으로 된다.
            throw e; // 예외를 꼭 다시 던져주어야 한다.
        }
    }
}
