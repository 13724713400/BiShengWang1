package com.zhushan.bishengwang.Itools;

public class Event
{
    /** 列表加载事件 */  
    public static class ItemListEvent  
    {
        private String position;
        public String getPosition() {
            return position;
        }
        public void setPosition(String position) {
            this.position = position;
        }
    }
  
}  