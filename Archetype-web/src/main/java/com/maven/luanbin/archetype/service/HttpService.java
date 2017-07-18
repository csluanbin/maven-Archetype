package com.maven.luanbin.archetype.service;

import java.util.ArrayList;
import java.util.List;

import com.maven.luanbin.archetype.model.Node;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * Created by luanbin on 16/12/8.
 */
public class HttpService {

    public String getEntityFromUrl (String str_url, String encode) throws Exception {
        HttpClient httpclient = new DefaultHttpClient();
        //httpclient.getParams().setParameter(CoreProtocolPNames.USER_AGENT,  "Mozilla/5.0 (Windows NT 6.1; rv:8.0.1) Gecko/20100101 Firefox/8.0.1");
        HttpGet httpget = new HttpGet(str_url);
        HttpResponse res = httpclient.execute(httpget);
        HttpEntity entity = res.getEntity();
        String loginEntityContent = EntityUtils.toString(entity, encode);
        return loginEntityContent;
    }

    /*public static final String sendHttpsRequestByPost(String url, Map<String, String> params) {
        String responseContent = null;
        HttpClient httpClient = new DefaultHttpClient();
        //创建TrustManager
        X509TrustManager xtm = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        //这个好像是HOST验证
        X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier() {
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
            public void verify(String arg0, SSLSocket arg1) throws IOException {}
            public void verify(String arg0, String[] arg1, String[] arg2) throws SSLException {}
            public void verify(String arg0, X509Certificate arg1) throws SSLException {}
        };
        try {
            //TLS1.0与SSL3.0基本上没有太大的差别，可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext
            SSLContext ctx = SSLContext.getInstance("TLS");
            //使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
            ctx.init(null, new TrustManager[] { xtm }, null);
            //创建SSLSocketFactory
            SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
            socketFactory.setHostnameVerifier(hostnameVerifier);
            //通过SchemeRegistry将SSLSocketFactory注册到我们的HttpClient上
            httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", socketFactory, 443));
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> formParams = new ArrayList<NameValuePair>(); // 构建POST请求的表单参数
            for (Map.Entry<String, String> entry : params.entrySet()) {
                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(formParams, "UTF-8"));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity(); // 获取响应实体
            if (entity != null) {
                responseContent = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            httpClient.getConnectionManager().shutdown();
        }
        return responseContent;
    }

    public List<Node> getNode(String html,String id, String[] strs, String type)
    {
        Document doc=  Jsoup.parse(html);
        List<Node> list=new ArrayList<Node>();
        switch(type)
        {
            case "class":
                Elements elements=doc.getElementsByClass(id);
                for(Element e: elements)
                {
                    List<Element> list_element=GetElementList(e, strs);
                    for(Element ele: list_element)
                    {
                        Node node=new Node();
                        node.setLink(ele.attr("href"));
                        node.setText(ele.html());
                        list.add(node);
                    }
                }
                break;
            case "id":
                Element element=doc.getElementById(id);
                List<Element> list_element=GetElementList(element, strs);
                for(Element ele: list_element)
                {
                    Node node=new Node();
                    node.setLink(ele.attr("href"));
                    node.setText(ele.html());
                    list.add(node);
                }
        }
        return list;
    }

    public List<Node> getNodeContent(String html,String id, String[] strs, String type)
    {
        Document doc=  Jsoup.parse(html);
        List<Node> list=new ArrayList<Node>();
        switch(type)
        {
            case "class":
                Elements elements=doc.getElementsByClass(id);
                for(Element e: elements)
                {
                    List<Element> list_element=GetElementList(e, strs);
                    for(Element ele: list_element)
                    {
                        Node node=new Node();
                        node.setText(ele.html());
                        list.add(node);
                    }
                }
                break;
            case "id":
                Element element=doc.getElementById(id);
                List<Element> list_element=GetElementList(element, strs);
                for(Element ele: list_element)
                {
                    Node node=new Node();
                    node.setText(ele.html());
                    list.add(node);
                }
        }
        return list;
    }

    private List<Element> GetElementList(Element e, String[] tags)
    {
        List<Element> list=new ArrayList<Element>();
        if(tags.length==0)
        {
            list.add(e);
        }
        else
        {
            Elements temp=e.getElementsByTag(tags[0]);
            if(temp==null)
            {
                list.add(e);
                return list;
            }
            for(int i=1; i<tags.length; i++)
            {
                String str=tags[i];
                Elements temp1=new Elements();
                for(Element e1:temp)
                {
                    temp1.addAll(e1.getElementsByTag(str));
                }
                if(temp1.size()==0)
                {
                    list.add(e);
                    return list;
                }
                temp=temp1;
            }
            for(Element e1:temp)
            {
                list.add(e1);
            }
        }
        return list;
    }*/

}
