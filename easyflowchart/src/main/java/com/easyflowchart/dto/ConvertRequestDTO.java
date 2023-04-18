package com.easyflowchart.dto;

import com.easyflowchart.enums.SyntaxType;
import lombok.Data;

@Data
public class ConvertRequestDTO {
    private final String originalCode;
    private final SyntaxType type;
}
