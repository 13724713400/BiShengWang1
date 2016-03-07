package com.zhushan.bishengwang.Ifactory;

import android.content.Context;

import com.zhushan.bishengwang.Iaplication.TBaplication;
import com.zhushan.bishengwang.Itools.VolleyTools;

import org.simple.eventbus.EventBus;

import java.util.Map;

/**
 * Created by Administrator on 2015/11/28.
 */
public class IHompageFactory {
    private static VolleyTools<IHompageFactory> volleyTools;
    public static void getInstance(Class cls,int what,String tag,String url,Map map)
    {
      /*  if(volleyTools ==null)
        {
            volleyTools = new VolleyTools<IHompageFactory> (cls,what,tag);
            volleyTools.getJsonFromHttpPost(TBaplication.getInstance(),url,map);
        }
*/
    }

}
