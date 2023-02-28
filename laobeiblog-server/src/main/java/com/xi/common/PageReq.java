package com.xi.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PageReq {
    @Schema(description = "页码，默认第一页")
    private long page=1;
    @Schema(description = "每页大小，默认5")
    private long size=10;
}
