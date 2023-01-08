package com.scm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductsRequest {

    private Long id;

    @Size(max = 30, message = "Size is exceeded")
    @NotBlank(message = "Please provide product name")
    private String productName;

    @NotNull(message = "Please provide quantity of product")
    private Integer quantity;

    @NotNull(message = "Please provide products price")
    private Double price;

    private Set<String> image;
}
