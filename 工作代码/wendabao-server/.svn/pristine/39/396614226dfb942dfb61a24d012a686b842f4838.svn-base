/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.handany.base.generator;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.log4j.Logger;
import freemarker.template.Version;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Map;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Administrator
 */
public class FreemarkerUtil {
    private static final Logger log = Logger.getLogger(FreemarkerUtil.class);
    public static final String CHARSET = "UTF-8";
    
    public static Template getTemplate(String templateName) {
        try {
            Version version = new Version(2, 3, 23);
            Configuration cfg = new Configuration(version);
            File file = new File("src/main/java/com/handany/base/generator/template");
            System.out.println(file.getAbsolutePath());
            FileTemplateLoader fileTemplateLoader = new FileTemplateLoader(file);
            cfg.setTemplateLoader(fileTemplateLoader);
            cfg.setObjectWrapper(new DefaultObjectWrapper(version));
            cfg.setDefaultEncoding(CHARSET);
            return cfg.getTemplate(templateName);
        } catch (IOException ex) {
           log.error(ex.getMessage(), ex);
           throw new RuntimeException(ex);
        }
    }
    
    public static void outputProcessResult(String outputFile, Template template, Map<String, Object> varMap) {
        String resultString;
        ByteArrayOutputStream baos = null;
        Writer writer = null;
        
        try {
            baos = new ByteArrayOutputStream();
            writer = new OutputStreamWriter(baos, CHARSET);
            template.process(varMap, writer);
            resultString = new String(baos.toByteArray(), CHARSET);
            FileUtils.writeStringToFile(new File(outputFile), resultString, CHARSET);
        } catch (UnsupportedEncodingException ex) {
            log.error(ex.getMessage(), ex);
        } catch (IOException | TemplateException ex) {
            log.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        } finally {
            if (null != baos) {
                try {
                    baos.close();
                } catch (IOException ex) {
                    log.warn(ex.getMessage(), ex);
                }
            }
            
            if (null != writer) {
                try {
                    writer.close();
                } catch (IOException ex) {
                    log.warn(ex.getMessage(), ex);
                }
            }
        }
    }
}
