package com.easyflowchart.dto;

import com.easyflowchart.enums.SyntaxType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConvertResponseDTO {
    private String converted;
    private SyntaxType type;
}
