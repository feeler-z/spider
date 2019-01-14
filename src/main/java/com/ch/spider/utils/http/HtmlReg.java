package com.ch.spider.utils.http;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlReg {


    public static String FindStrByReg(String strText,String strReg)
    {
        String strContent="";
        try {
            String regEx = strReg;
            String s = strText;
            Pattern pat = Pattern.compile(regEx);
            Matcher mat = pat.matcher(s);
            boolean rs = mat.find();
            if(rs==true)
            {
                strContent=mat.group(0);
            }


        } catch (Exception e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            strContent="";
        }
        return strContent;

    }

    public static List<String> FindStrByRegList(String strText,String strReg)
    {
        List<String> listResult=new ArrayList<String>();
        String strContent="";
        try {
            String regEx = strReg;
            String s = strText;
            Pattern pat = Pattern.compile(regEx);
            Matcher mat = pat.matcher(s);
            while(mat.find())
            {
                strContent=mat.group();
                listResult.add(strContent);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            strContent="";
        }
        return listResult;

    }

}//end class HtmlReg
