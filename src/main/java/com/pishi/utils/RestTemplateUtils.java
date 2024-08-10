package com.pishi.utils;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import com.pishi.doc20240606.modle.dto.Resp;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Optional;

public class RestTemplateUtils {


    private static final String URL = "http://xxxx/module/analysis/";


    /**
     * post 请求
     *
     * @param restTemplate 对象
     * @param URL          url
     * @param parameters   参数
     * @param resp         返回值类型
     * @param <T>
     * @return
     */
    public static <T> T post(RestTemplate restTemplate,
                             String url,
                             Object parameters,
                             TypeReference<T> typeReference
    ) {

        final HttpHeaders httpHeaders = getHttpHeaders();

        final HttpEntity<Object> httpEntity = new HttpEntity<>(parameters, httpHeaders);


        final ResponseEntity<Resp> tResponseEntity = restTemplate.postForEntity(URL + url, httpEntity, Resp.class);


        return getT(typeReference, tResponseEntity);

    }

    private static <T> T getT(TypeReference<T> typeReference, ResponseEntity<Resp> tResponseEntity) {
        return Optional.ofNullable(tResponseEntity)
                .map(ResponseEntity::getBody)
                .map(Resp::getData)
                .map(i -> {
                    final String jsonStr = JSONUtil.toJsonStr(i);
                    return JSONUtil.toBean(jsonStr, typeReference, true);
                })
                .orElseThrow(RuntimeException::new);
    }

    private static HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("api-code", "bf1");
        return httpHeaders;
    }


    public static <T> T post(RestTemplate restTemplate,
                             String URL,
                             TypeReference<T> typeReference

    ) {
        return post(restTemplate, URL, new HashMap<>(), typeReference);
    }

    public static <T> T get(RestTemplate restTemplate, String url, TypeReference<T> typeReference) {


        HttpEntity<?> entity = new HttpEntity<>(getHttpHeaders());

        ResponseEntity<Resp> response = restTemplate.exchange(
                URL + url,
                HttpMethod.GET,
                entity,
                Resp.class);


        return getT(typeReference, response);

    }
}
