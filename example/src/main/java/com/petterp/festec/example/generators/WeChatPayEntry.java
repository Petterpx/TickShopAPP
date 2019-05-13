package com.petterp.festec.example.generators;


import com.petterp.latte.annotations.PayEntryGenerator;
import com.petterp.latte_core.wechat.templates.WXPayEntryTemplate;

/**
 * Created by 傅令杰 on 2017/4/22
 */
@SuppressWarnings("unused")
@PayEntryGenerator(
        packageName = "com.petterp.festec.example",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
