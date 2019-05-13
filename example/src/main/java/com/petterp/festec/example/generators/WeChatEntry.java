package com.petterp.festec.example.generators;


import com.petterp.latte.annotations.EntryGenerator;
import com.petterp.latte_core.wechat.templates.WXEntryTemplate;

/**
 * Created by 傅令杰 on 2017/4/22
 */

@SuppressWarnings("unused")
@EntryGenerator(
        packageName = "com.petterp.festec.example",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}
