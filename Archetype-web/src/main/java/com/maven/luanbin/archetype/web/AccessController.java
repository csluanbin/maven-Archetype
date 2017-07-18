package com.maven.luanbin.archetype.web;

import com.maven.luanbin.archetype.common.TErrorResult;
import com.maven.luanbin.archetype.common.TResponse;
import com.maven.luanbin.archetype.common.TSuccessResult;
import com.maven.luanbin.archetype.dataobject.UserDo;
import com.maven.luanbin.archetype.mapper.UserMappper;
import com.maven.luanbin.archetype.vo.LoginRequestVo;
import com.maven.luanbin.archetype.vo.LoginResponseVo;
import com.maven.luanbin.archetype.vo.RegisterRequestVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by luanbin on 17/6/17.
 */
@RestController
@RequestMapping("/access")
public class AccessController {

    @Resource
    private UserMappper userMappper;

    @Resource
    private HttpServletResponse response;


    @Resource
    private HttpServletRequest request;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public TResponse register(@RequestBody RegisterRequestVo registerRequestVo) {
        if(registerRequestVo == null){
            return new TErrorResult("注册失败，参数映射错误");
        }
        if(registerRequestVo.getLoginName() == null || registerRequestVo.getPassword() == null){
            return new TErrorResult("注册失败，参数传递错误");
        }

        String loginName = registerRequestVo.getLoginName();
        UserDo userDo = userMappper.getUserDoByLoginName(loginName);

        if(userDo != null){
            return new TErrorResult("注册失败，用户名已存在");
        }

        userDo = new UserDo();
        userDo.setCreateTime(new Date());
        userDo.setLoginName(registerRequestVo.getLoginName());
        userDo.setPassword(registerRequestVo.getPassword());
        userDo.setCorpName(registerRequestVo.getCorpName());

        userMappper.insert(userDo);

        return new TSuccessResult<>("注册成功");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public TResponse login(@RequestBody LoginRequestVo loginRequestVo) {
        if(loginRequestVo == null){
            return new TErrorResult("登录失败，参数映射错误");
        }
        if(loginRequestVo.getLoginName() == null || loginRequestVo.getPassword() == null){
            return new TErrorResult("登录失败，参数传递错误");
        }

        String loginName = loginRequestVo.getLoginName();
        String password = loginRequestVo.getPassword();
        UserDo userDo = userMappper.getUserDoByLoginName(loginName);

        if(userDo == null){
            return new TErrorResult("登录失败，用户名不存在");
        }

        String dbPassword  = userDo.getPassword();

        if(dbPassword.equals(password)){
            Cookie cookie = new Cookie("login", "true");
            cookie.setMaxAge(60 * 60 * 24);// 设置为30min
            cookie.setPath("/");
            response.addCookie(cookie);

            LoginResponseVo loginResponseVo = new LoginResponseVo();
            loginResponseVo.setCorpName(userDo.getCorpName());
            loginResponseVo.setUserId(userDo.getId());
            loginResponseVo.setLoginName(userDo.getLoginName());

            return new TSuccessResult<>(loginResponseVo, "登录成功");
        }else {
            return new TSuccessResult<>("登录失败，密码错误");
        }
    }

    public static boolean loginByCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for(Cookie cookie : cookies){

                String name = cookie.getName();

                if(name != null && name.equals("login")){
                    if(cookie.getValue() != null && cookie.getValue().equals("true")){
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
