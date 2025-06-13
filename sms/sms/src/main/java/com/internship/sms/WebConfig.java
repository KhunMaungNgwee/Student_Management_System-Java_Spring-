/**
 * 
 */
package com.internship.sms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Value("${staff.file.path.mapping}")
	private String staffFileMapping;

	@Value("${staff.file.path.configPath}")
	private String staffActualFileConfigPath;

	@Value("${student.file.path.mapping}")
	private String studentFileMapping;

	@Value("${student.file.path.configPath}")
	private String studentActualFileConfigPath;

	@Value("${notice.file.path.mapping}")
	private String noticeFileMapping;

	@Value("${notice.file.path.configPath}")
	private String noticeActualFileConfigPath;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler(staffFileMapping).addResourceLocations(staffActualFileConfigPath)
				.setCachePeriod(3600);

		registry.addResourceHandler(studentFileMapping).addResourceLocations(studentActualFileConfigPath)
				.setCachePeriod(3600);

		registry.addResourceHandler(noticeFileMapping).addResourceLocations(noticeActualFileConfigPath)
				.setCachePeriod(3600);
	}

}
