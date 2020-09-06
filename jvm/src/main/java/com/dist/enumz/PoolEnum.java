package com.dist.enumz;

public enum  PoolEnum {
    A,
    B;
   private Object object =null;

    public void setObject(Object object) {
        if(this.object==null){
            this.object = object;
        }
    }

    @Override
    public String toString() {
     return this.name().toLowerCase();
    }

    public Object getObject() {
        return object;
    }
}
