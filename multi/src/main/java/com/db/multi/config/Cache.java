package com.db.multi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;
@Data
@Component
public class Cache {
	@Value("${site.url}")
	private String SITE_URL;
	@Value("${evict.cache.varnish.desktop.site.url}")
	private String EVICT_VARNISH_DESKTOP_SITE_URL;

	@Value("${evict.cache.varnish.mobile.site.url}")
	private String EVICT_VARNISH_MOBILE_SITE_URL;
}
