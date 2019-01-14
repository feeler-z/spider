package com.ch.spider.utils.http;



import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.util.ArrayList;
import java.util.List;

public class HtmlXpath {

    //无效，测试用
    public static List<String> GetTest(String html,String xpath)
    {

        List<String> listBlock=new ArrayList<String>();
        try
        {
            HtmlCleaner hc = new HtmlCleaner();
            TagNode tn = hc.clean(html);
            Document dom = new DomSerializer(new CleanerProperties()).createDOM(tn);
            XPath xPath = XPathFactory.newInstance().newXPath();
            Object result;
            result = xPath.evaluate(xpath, dom, XPathConstants.NODESET);
            if (result instanceof NodeList) {
                NodeList nodeList = (NodeList) result;
                System.out.println(nodeList.getLength());
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    /**
                     * Node.getTextContent() 此属性返回此节点及其后代的文本内容。
                     * Node.getFirstChild()  此节点的第一个子节点。
                     * Node.getAttributes() 包含此节点的属性的 NamedNodeMap（如果它是 Element）；否则为 null
                     * 如果想获取相应对象的相关属性，可以调用  getAttributes().getNamedItem("属性名") 方法
                     */

                    String innerhtml=hc.getInnerHtml((TagNode) node);
                    System.out.println(innerhtml);
                }

            }

        }catch (Exception ex)
        {
            String strex=ex.toString();
        }
        return listBlock;
    }

    //根据属性，获取固定标签的属性值
    public static String GetAttribute(String html,String xpath,String attribute)
    {

        String strResult="";
        try
        {
            HtmlCleaner hc = new HtmlCleaner();
            TagNode tn = hc.clean(html);
            Document dom = new DomSerializer(new CleanerProperties()).createDOM(tn);
            XPath xPath = XPathFactory.newInstance().newXPath();
            Object result;
            result = xPath.evaluate(xpath, dom, XPathConstants.NODESET);
            if (result instanceof NodeList) {
                NodeList nodeList = (NodeList) result;
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    /**
                     * Node.getTextContent() 此属性返回此节点及其后代的文本内容。
                     * Node.getFirstChild()  此节点的第一个子节点。
                     * Node.getAttributes() 包含此节点的属性的 NamedNodeMap（如果它是 Element）；否则为 null
                     * 如果想获取相应对象的相关属性，可以调用  getAttributes().getNamedItem("属性名") 方法
                     */
                   // System.out.println(node.getNodeValue() == null ? node.getFirstChild().getAttributes().getNamedItem("href") : node.getNodeValue());
                    strResult=node.getAttributes().getNamedItem(attribute).getTextContent();
                    break;
                   // System.out.println(strResult);
                }

            }

        }catch (Exception ex)
        {
            String strex=ex.toString();
        }
        return strResult;
    }

    /*
    * 根据xpath抽取相应的文本内容，不带html格式
    * **/
    public static String GetContent(String html,String xpath)
    {

        String strResult="";
        try
        {
            HtmlCleaner hc = new HtmlCleaner();
            TagNode tn = hc.clean(html);
            Document dom = new DomSerializer(new CleanerProperties()).createDOM(tn);
            XPath xPath = XPathFactory.newInstance().newXPath();
            Object result;
            result = xPath.evaluate(xpath, dom, XPathConstants.NODESET);
            if (result instanceof NodeList) {
                NodeList nodeList = (NodeList) result;
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    /**
                     * Node.getTextContent() 此属性返回此节点及其后代的文本内容。
                     * Node.getFirstChild()  此节点的第一个子节点。
                     * Node.getAttributes() 包含此节点的属性的 NamedNodeMap（如果它是 Element）；否则为 null
                     * 如果想获取相应对象的相关属性，可以调用  getAttributes().getNamedItem("属性名") 方法
                     */
                    // System.out.println(node.getNodeValue() == null ? node.getFirstChild().getAttributes().getNamedItem("href") : node.getNodeValue());
                    strResult=node.getTextContent();
                    break;
                    // System.out.println(strResult);
                }

            }

        }catch (Exception ex)
        {
            String strex=ex.toString();
        }
        return strResult;
    }
    /*
     * 从html中根据Xpath抽取，相应的html代码
     * 只获取第一个内容
     * ***/
    public static String GetInnerHtml(String strHtml,String strSubXpath)
    {
        String strResult="";
        try {
            HtmlCleaner cleaner2 = new HtmlCleaner();
            org.htmlcleaner.TagNode node2 = cleaner2.clean(strHtml);
            Object[] ns2 = node2.evaluateXPath(strSubXpath);

            for(Object on2 : ns2) {
                if(strSubXpath.toLowerCase().contains("@href")||strSubXpath.toLowerCase().contains("text()"))
                {
                    //strResult=on2.toString();
                    strResult="";
                }
                else
                {
                    TagNode n2 = (TagNode) on2;
                    strResult=cleaner2.getInnerHtml(n2);
                    //System.out.println(strResult);
                    break;
                }
            }

        }
        catch (Exception exception) {
            //exception.printStackTrace();
            System.out.println("GetInnerHtml error");
        }
        return strResult;
    }


    /*
     * 从html中根据Xpath抽取，相应的html代码
     * 只获取列表
     * ***/
    public static List<String>  GetInnerHtmlList(String strHtml,String strSubXpath)
    {
        List<String> listBlock=new ArrayList<String>();
        String strResult="";
        try {
            HtmlCleaner cleaner2 = new HtmlCleaner();
            org.htmlcleaner.TagNode node2 = cleaner2.clean(strHtml);
            Object[] ns2 = node2.evaluateXPath(strSubXpath);

            for(Object on2 : ns2) {
                if(!(strSubXpath.toLowerCase().contains("@href")||strSubXpath.toLowerCase().contains("text()")))
                {
                    TagNode n2 = (TagNode) on2;
                    strResult=cleaner2.getInnerHtml(n2);
                    //System.out.println(strResult);
                    listBlock.add(strResult);
                }
            }
        }
        catch (Exception exception) {
            //exception.printStackTrace();
            System.out.println("GetInnerHtml error");
        }
        return listBlock;
    }
}//end HtmlXpath
