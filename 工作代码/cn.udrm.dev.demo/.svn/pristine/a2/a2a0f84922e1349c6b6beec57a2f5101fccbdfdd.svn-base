package cn.udrm.dev.demo.util;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import net.risesoft.soa.framework.service.config.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigUtil {
	
	private static final Logger log = LoggerFactory.getLogger(ConfigUtil.class);

	private static Properties   props = new Properties();
	
	private static final String CONFIG_FILE = "demo/demo.properties";
	
	static {
        URL url = Config.getConfigFileAsURL(CONFIG_FILE);
        try {
            props.load(url.openStream());
        } catch (IOException ex) {
            log.error("不能加载" + CONFIG_FILE + "配置文件.", ex);
        }
    }
	
    public static String getProperty(String key) {
        return props.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }
	
}
