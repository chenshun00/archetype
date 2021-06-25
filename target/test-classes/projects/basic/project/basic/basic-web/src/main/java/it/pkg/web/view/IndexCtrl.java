package it.pkg.web.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import it.pkg.api.Response;

/**
 * @author luobo.cs@raycloud.com
 * @since 2020/6/17 10:33 上午
 */
@RequestMapping("api")
@RestController
public class IndexCtrl {

    final Logger log = LoggerFactory.getLogger(this.getClass());

    @PostMapping("postRequest")
    public Response postRequest(String name) {
        try {
            return Response.success("SUCCESS");
        } catch (Exception e) {
            log.error("[postRequest][error:{}]", e.getMessage(), e);
            return Response.fail(e.getMessage());
        }
    }

}
