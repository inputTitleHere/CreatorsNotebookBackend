package com.creators.notebook.backend.utils;

import lombok.*;


/**
 *  간단한 MSG를 클라이언트에게 보내기 위한 객체
 *  매번 Map&lt;String, String&gt; ... 를 만들어 보내는 것을 간소화하기 위해 만듦.
 *  Builder를 통해 생성 및 msg만 설정해서 보내자.
 */
@Builder
@Data
public class SimpleMsgResponse {
    private String msg;
    private Object object;
}
