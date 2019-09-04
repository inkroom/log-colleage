/**
 * Copyright © 2019 inkbox (enpassPixiv@protonmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.inkroom.log.web.controller;

import cn.inkroom.log.web.bean.MessageDto;
import cn.inkroom.log.web.config.Asserts;
import cn.inkroom.log.web.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 墨盒
 * @date 19-4-30
 */
@RequestMapping("/auth/")
@Controller
public class AuthController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private AuthService service;

    @RequestMapping("index")
    public String index() throws RuntimeException {


        return "login";
    }

    @RequestMapping("login")
    @ResponseBody
    public MessageDto<String> login(String username, String password) throws RuntimeException {


        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return new MessageDto<>(2);
        } else if (request.getHeader("User-Agent") == null) {
            return new MessageDto<>(2);
        }

        if (service.login(username, password, request.getRemoteAddr(), request.getHeader("User-Agent"))) {
            request.getSession().setAttribute(Asserts.AUTH_SESSION_KEY, username);

            return new MessageDto<>(0);
        }

        return new MessageDto<>(1);
    }
}
