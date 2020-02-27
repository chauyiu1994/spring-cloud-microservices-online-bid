package com.chauyiu1994.onlineBidProductsService.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SQLErrorResponseModel {

    private String message;
}
