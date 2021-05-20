package com.ex;

import java.util.HashMap;

public abstract class AbstractApplication {
    protected HashMap<String, Object> context;
    public abstract void run();
    public HashMap<String, Object> getContext(){
        return this.context;
    }
}
