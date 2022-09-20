package org.example.github.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClientResponseException;

public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    void throwIfContentTypeNotSupported(String contentType) {
        logger.info("Inside of exception handling.");
        if (StringUtils.isNotBlank(contentType) && !StringUtils.equals("*/*", contentType)) {
            if (MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(contentType)) return;

            throw new WebClientResponseException(406, "Unsupported content type. Only application/json is supported", null, null, null);
        }
    }

}
