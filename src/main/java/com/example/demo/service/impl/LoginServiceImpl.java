package com.example.demo.service.impl;

import com.example.demo.annotation.LogAnnotation;
import com.example.demo.config.RedisService;
import com.example.demo.mode.myenum.EventType;
import com.example.demo.service.AopService;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RedisService redisService;

    @LogAnnotation(businessName = "yw",userName = "me",eventType = EventType.SYSTEM)
    @Override
    public String graphcode(String param) {
        String s = this.generateVerificationCode();
        redisService.setValue("VerCode_"+s,s,60*1000);
        return s;
    }

    @Override
    public Map login(String username,String code) {
        HashMap<Object, Object> resultHashMap = new HashMap<>();
        resultHashMap.put("code","500");
        resultHashMap.put("message","失败");
        if(redisService.getValue("VerCode_" + code)!=null){
            String token = this.generateVerificationCode();
            redisService.setValue("token_"+token,username,1800*1000);
            resultHashMap.put("username",username);
            resultHashMap.put("menu","menu");
            resultHashMap.put("token","token_"+token);
            resultHashMap.put("code","10000");
            resultHashMap.put("message","成功");
            return resultHashMap;
        }
        return resultHashMap;
    }

    // 定义一个字符串，其中包含了所有可能用于验证码的字符：数字、大写字母和小写字母
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CODE_LENGTH = 4;
    private static final SecureRandom random = new SecureRandom();

    /**
     * 生成一个包含数字和大小写字母的随机验证码。
     *
     * @return 生成的验证码字符串
     */
    public static String generateVerificationCode() {
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            // 从CHARACTERS中随机选择一个字符
            int index = random.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(index));
        }
        return code.toString();
    }

    public static void main(String[] args) {
        // 测试生成验证码的功能
        System.out.println("Generated verification code: " + generateVerificationCode());
    }
}
