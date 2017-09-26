package babywei.authserver.auth;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Locale;
import java.util.Map;

/**
 * @creator ZTH
 * @modifier ZTH
 * @date 2017-09-26
 */
@Component
public class ApplicationSupport implements DisposableBean, ApplicationContextAware {

	private static ApplicationContext appContext;

	// 获取配置文件参数值
	public static String getParamVal(String paramKey){
		return appContext.getEnvironment().getProperty(paramKey);
	}

	private static boolean hasProfile(String profile) {
		if (!appContext.getEnvironment().getProperty("spring.profiles.active").equals(profile)) {
			return false;
		}
		return true;
	}


	// 获取bean对象
	public static Object getBean(String name) {
		Assert.hasText(name);
		return appContext.getBean(name);
	}

	public static <T> T getBean(Class<T> clazz) {
		return appContext.getBean(clazz);
	}

	public static <T> Map<String, T> getBeansOfType(Class<T> type) {
		return appContext.getBeansOfType(type);
	}


	public static String getMessage(String code, Locale locale) {
		return getMessage(code, null, locale);
	}

	// 占位符
	public static String getMessage(String code, Object[] args, Locale locale) {
		String result = appContext.getMessage(code, args, null, locale);
		return result;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		appContext = applicationContext;
	}

	@Override
	public void destroy() throws Exception {
		appContext = null;
	}

}
