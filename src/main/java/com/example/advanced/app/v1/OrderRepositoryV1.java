package com.example.advanced.app.v1;

import com.example.advanced.trace.HelloTrace.HelloTraceV1;
import com.example.advanced.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor // component Scan 에 대상이 된다.
public class OrderRepositoryV1 {
    private final HelloTraceV1 trace;

    public void save(String itemId) {

        TraceStatus status = null;
        try {
            status = trace.begin("OrderRepository.save()");

            // 저장 로직
            if(itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }
            sleep(1000);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e); // 예외를 먹어버리면 정상흐름으로 된다.
            throw e; // 예외를 꼭 다시 던져주어야 한다.
        }
    }
    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
