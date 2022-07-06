package com.book.utils;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.Writer;

/**
 * @author Wahoyu
 * @date 2022/7/2
 */
public class ThymeleafUtil {
    //创建TemplateEngine引擎的对象engine
    private static final TemplateEngine engine;
    static  {
        engine = new TemplateEngine();
        ClassLoaderTemplateResolver r = new ClassLoaderTemplateResolver();
        engine.setTemplateResolver(r);
    }
    //创建引擎进行信息提交的方法，通过上传文件名，修改的上下文属性，和书写方法（此Writer表示IO流的writer）
    public static void process(String template, IContext context, Writer writer){
        engine.process(template, context, writer);
    }
}
