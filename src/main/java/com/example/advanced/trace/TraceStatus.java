package com.example.advanced.trace;

public class TraceStatus { // log 를 시작할때 가지고 있는 상태정보

    private TraceId traceId; // 내부에 트랙잭션 ID 와 level 을 가지고 있다
    private Long startTimeMs; // 로그 시작시간
    private String message; // 시작시 사용한 메시지

    public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
        this.traceId = traceId;
        this.startTimeMs = startTimeMs;
        this.message = message;
    }

    public TraceId getTraceId() {
        return traceId;
    }

    public Long getStartTimeMs() {
        return startTimeMs;
    }

    public String getMessage() {
        return message;
    }
}
