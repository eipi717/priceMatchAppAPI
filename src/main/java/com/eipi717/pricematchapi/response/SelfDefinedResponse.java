package com.eipi717.pricematchapi.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelfDefinedResponse<T> {
    private String message = "";

    private long count = 0;

    private T data;

}
