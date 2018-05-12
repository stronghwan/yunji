package com.yunding.news.web.control;
/*
 * @Name:朋友圈•图片
 * @Author:Farmerzhang
 * @Date: 2018/4/23
 * @Time: 16:28
 */import com.yunding.news.model.pojo.Pictures;
import com.yunding.news.model.service.ServiceFactory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class pictureServlet extends HttpServlet {
    public void doPost(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        String realname;
        String realpath;
        String url = null;
        //1.创建文件上传工厂类
        DiskFileItemFactory fac = new DiskFileItemFactory();
        String path = getServletContext().getRealPath( "/WEB-INF/images" );
        //设置上传的临时目录
        fac.setRepository( new File( path ) );
        if (!new File( path ).exists()) {
            new File( path ).mkdir();
            System.out.println( "创建临时文件" );
        } else {
            System.out.println( "临时文件存在,不需要创建!" );
        }
        //2.创建文件上传核心类对象
        ServletFileUpload upload = new ServletFileUpload( fac );
        //【一、设置单个文件最大30M】
        upload.setFileSizeMax( 10 * 1024 * 1024 );//10M
        //【二、设置总文件大小：50M】
        upload.setSizeMax( 50 * 1024 * 1024 ); //50M
        // 也可以通过这样的方式来判断是否是entype类型的
//        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
//        if(!isMultipart)
//        {
//            System.out.println("上传方式错误！");
//            return;
//        }
        //判断用户的表单的enctype是否为multipart/from-data类型的，如果不是那么就不处理。
        if (upload.isMultipartContent( request )) {

            try {
                //3.把请求数据转换为FileItem对象的集合
                List<FileItem> fileItemListlist = upload.parseRequest( request );
                //遍历，得到每一个上传项
                Iterator<FileItem> fileItems = fileItemListlist.iterator();
                while (fileItems.hasNext()) {
                    FileItem fileItem = fileItems.next();
                    //判断：是普通表单项，还是文件上传表单项
                    if (fileItem.isFormField()) {
                        //普通表单x（isFromField返回为true）
                        // getFieldName()获取表单元素中name元素的值
                        String fieldName = fileItem.getFieldName();//获取元素名称
                        // 将保存在FileItem中的数据流内容以一个字符串的内容返回
                        String value = fileItem.getString("utf-8"); //获取元素值
                        System.out.println( "输出" );
                        System.out.println( fieldName + " : " + value );
                    } else {
                        //文件上传表单
                        String name = fileItem.getName(); //上传的文件名称
                        System.out.println( name );
                        String suffix = name.substring( name.lastIndexOf( "." ) );//获取文件的扩展名
                        System.out.println( suffix );
                        //通过上传时间+随机数命名
                        DateFormat dateFormat = new SimpleDateFormat( "yyyyMMddHHmmss" );
                        Date data = new Date();
                        Random random = new Random();
                        int i = random.nextInt( 10 );
                        //与文件名拼接
                        realname = dateFormat.format( data ) + i + suffix;
                        System.out.println( realname );
                        //request.getSession().setAttribute("name",realName);
                        //上传到指定目录：获取上传目录路径
                        realpath = "D:\\picture";
                        //创建文件对象
                        File file = new File( realpath, realname );
                        System.out.println( "成功上传喽！！！" );
                        url = "/image/"+realname;
                        fileItem.write( file );
                        fileItem.delete();


                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println( "不处理！" );
        }
        //将图片的url 发图片用户的姓名和id
        Pictures pictures=new Pictures();
        pictures.setUrl( url );


        //请求中文编码设置
        request.setCharacterEncoding("UTF-8");

        // 响应中文乱码  字节流处理
        response.setHeader("ContentType", "text/html;application/json;charset=UTF-8");

        //响应中文乱码  字符流处理；设置response缓冲区编码
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        String JSON=request.getParameter("pictureList"); //json
        JSONObject json=JSONObject.fromObject(JSON);

            String puser_name=json.getString( "puser_name" ) ;
            int puser_id = ServiceFactory.getService("user").findUserId(puser_name);
            //将传入的user_name转为user_id,存入数据库
            pictures.setPuser_name( puser_name );
            pictures.setUserId( puser_id );
            pictures.setFid(json.getInt("fid"));
            ServiceFactory.getService("picture").save(pictures);
        }
    }


