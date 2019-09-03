package com.petterp.festec.example.generators;


import com.petterp.latte.annotations.AppRegisterGenerator;
import com.petterp.latte_core.wechat.templates.AppRegisterTemplate;

/**
 * Created by 傅令杰 on 2017/4/22
 */
@SuppressWarnings("unused")
@AppRegisterGenerator(
        packageName = "com.petterp.festec.example",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
