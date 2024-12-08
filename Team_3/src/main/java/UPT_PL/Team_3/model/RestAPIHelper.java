package UPT_PL.Team_3.model;

import org.springframework.web.client.RestTemplate;

public class RestAPIHelper {
	// Static variable
	private static String rootAPIURL = "http://localhost:8080/api/";
	private static RestTemplate restTemplate = new RestTemplate();
	
	

	/**
	 * Constructor
	 */
	public RestAPIHelper() {
	}

	/**
	 * @return the rootAPIURL
	 */
	public String getRootAPIURL() {
		return rootAPIURL;
	}

	/**
	 * @param rootAPIURL the rootAPIURL to set
	 */
	public void setRootAPIURL(String rootAPIURL) {
		this.rootAPIURL = rootAPIURL;
	}

	/**
	 * @return the restTemplate
	 */
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	/**
	 * @param restTemplate the restTemplate to set
	 */
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
}
