package edu.uw.aws.cliloginpassthrough.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Base64;
import java.util.Objects;

import edu.uw.aws.cliloginpassthrough.properties.PassthroughProperties;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Maxime Deravet Date: 2019-05-28
 */
@Controller
@Slf4j
public class PassthroughController {


    private PassthroughProperties passthroughProperties;

    @Autowired
    public PassthroughController(PassthroughProperties passthroughProperties) {
        this.passthroughProperties = passthroughProperties;
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/passthrough")
    public String passthroughApp(
            @RequestHeader(name = "Referer", required = false) String referer,
            @RequestParam(name = "SAMLResponse") String samlResponse,
            @RequestParam(name = "localPort", required = false) Integer forcedLocalPort,
            Model model) {


        prepareModel(model, samlResponse, referer, forcedLocalPort);

        return "passthrough";
    }


    private void prepareModel(Model model, String samlResponse, String referer, Integer forcedLocalPort) {
        model.addAttribute("SAMLResponse", samlResponse);

        if (log.isDebugEnabled()) {
            model.addAttribute("SAMLResponseDecoded", new String(Base64.getDecoder().decode(samlResponse.getBytes())));
        }

        model.addAttribute("localPort", getLocalPort(referer, forcedLocalPort));

    }

    private int getLocalPort(String referer, Integer forcedLocalPort) {
        int localPort = passthroughProperties.getDefaultPort();

        if (forcedLocalPort != null) {
            localPort = forcedLocalPort;
        } else if (!StringUtils.isEmpty(referer)) {
            localPort = Integer.valueOf(Objects.requireNonNull(getLocalPortFromReferer(referer)));
        }

        return localPort;
    }

    private String getLocalPortFromReferer(String referer) {
        return UriComponentsBuilder.fromHttpUrl(referer).build().getQueryParams().getFirst(passthroughProperties.getLocalPortParamName());
    }
}
