package io.github.lowering;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

/**
 * Created by CrazyCode on 2017/5/21.
 */
@Configuration
@EnableJpaRepositories(
		basePackages = {"io.github.lowering"}
)
public class RepositoryConfiguration {

	private static final Log logger = LogFactory.getLog(RepositoryConfiguration.class);

	@PostConstruct
	public void init(){
		logger.info("初始化Repository配置");
	}
}
