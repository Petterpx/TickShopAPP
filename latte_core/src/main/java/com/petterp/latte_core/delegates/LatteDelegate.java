package com.petterp.latte_core.delegates;

/**
 * auther: Petterp on 2019/4/17
 * Summary:
 */
public abstract class LatteDelegate extends PermissionCheckerDelegate{

    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate(){
        return (T)getParentFragment();
    }
}
