package com.petterp.festec.example.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mob.MobSDK;
import com.petterp.latte_core.delegates.web.event.Event;
import com.petterp.latte_core.util.log.LatteLogger;

import cn.sharesdk.onekeyshare.OnekeyShare;


/**
 * Created by 傅令杰
 */

public class ShareEvent extends Event {

    @Override
    public String execute(String params) {

        LatteLogger.json("ShareEvent", params);

        final JSONObject object = JSON.parseObject(params).getJSONObject("params");
        final String title = object.getString("title");
        final String url = object.getString("url");
        final String imageUrl = object.getString("imageUrl");
        final String text = object.getString("text");
        MobSDK.init(getContext());
        final OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();
        oks.setTitle(title);
        oks.setText(text);
        oks.setImageUrl(imageUrl);
        oks.setUrl(url);
        oks.show(getContext());

        return null;
    }
}
