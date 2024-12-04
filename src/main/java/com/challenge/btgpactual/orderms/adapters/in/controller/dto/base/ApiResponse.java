package com.challenge.btgpactual.orderms.adapters.in.controller.dto.base;

import java.util.List;
import java.util.Map;

public record ApiResponse<T>(
        Map<String, Object> summary,
        List<T> data,
        PaginationResponse pagination) {
}
