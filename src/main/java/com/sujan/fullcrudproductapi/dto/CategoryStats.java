package com.sujan.fullcrudproductapi.dto;

import java.math.BigDecimal;

public record CategoryStats(
        String category,
        BigDecimal totalStockValue,
        BigDecimal averagePrice
) {}
