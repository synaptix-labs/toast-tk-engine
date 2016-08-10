package io.toast.tk.adapter.constant;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.inject.Provider;

public class AdaptersConfigProvider implements Provider<AdaptersConfig> {

	private static final Logger LOG = LogManager.getLogger(AdaptersConfigProvider.class);

	private AdaptersConfig config;

	public AdaptersConfigProvider() {
		initConfig();
	}

	private void initConfig() {
		System.out.println("Initialize configuration from toast.properties");

		final Properties p = new Properties();

		InputStream resourceAsStream = this.getClass().getResourceAsStream("/toast.properties");

		try (final Reader resourceFileReader = new InputStreamReader(resourceAsStream)) {
			p.load(resourceFileReader);
		} catch (final IOException e) {
			LOG.error(e.getMessage(), e);
		}
		this.config = new AdaptersConfig(
				p.getProperty("web.driver", "Chrome"),
				p.getProperty("web.driver.path"),
				p.getProperty("browser.path"),
				Boolean.getBoolean("web.driver.ssl"),
				p.getProperty("reports.folder.path"));
	}

	@Override
	public AdaptersConfig get() {
		return config;
	}
}