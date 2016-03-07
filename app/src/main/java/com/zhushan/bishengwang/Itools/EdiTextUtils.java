package com.zhushan.bishengwang.Itools;

import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/12/11.
 */
public class EdiTextUtils {

    private static  EdiTextUtils ediTextUtils;

    private EdiTextUtils(){}

    public static EdiTextUtils getInstance()
    {
        if (ediTextUtils==null)
        {
            ediTextUtils = new EdiTextUtils();

        }
        return ediTextUtils;
    }

    public boolean CheckPasswoeEqual(EditText password,EditText passwordtwo)
    {
        if (password==null||passwordtwo==null)
        {
            return false;
        }

        if (password.getText().toString().trim().equals(passwordtwo.getText().toString().trim()))
        {
            return true;

        }else{
            Snackbar.make(passwordtwo,"两次输入的密码不正确,请重新输入",Snackbar.LENGTH_LONG).show();
            return false;
        }


    }

    public void resetText(EditText editText)
    {
        if (editText==null)
        {
            return;

        }

        editText.setText("");

    }
    public String getText(EditText editText)
    {
        return editText.getText().toString().trim();
    }
    public boolean CheckedEmpty(EditText editText,String error)
    {
        if (editText==null)
        {
            return false;
        }

        if (TextUtils.isEmpty(editText.getText().toString().trim()))
        {
            editText.setError(error);
            return false;

        }
     return  true;
    }

    public boolean CheckPhone(EditText editText,String error)
    {

        if (editText==null)
        {
            return false;
        }
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(editText.getText().toString().trim());
       if(!m.matches())
       {
           editText.setError(error);
           return false;

       }

        return true;
    }

    public boolean CheckedPassword(EditText editText,String error)
    {
        if (editText==null)
        {
            return false;
        }
        if (editText.getText().toString().trim().length()<6)
        {
            editText.setError(error);
            return false;

        }
        return true;
    }




}
