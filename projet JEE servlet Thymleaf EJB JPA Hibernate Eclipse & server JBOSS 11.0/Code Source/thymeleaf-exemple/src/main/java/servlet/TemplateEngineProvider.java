package servlet;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

public abstract class TemplateEngineProvider {
	private static TemplateEngine templateEngine;

	static {
		initializeTemplateEngine();
	}

	private static void initializeTemplateEngine() {

		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setTemplateMode("XHTML");
		templateResolver.setPrefix("/WEB-INF/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setCacheTTLMs(3600000L);

		templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
	}

	public static TemplateEngine getTemplateEngine() {
		return templateEngine;
	}
}
