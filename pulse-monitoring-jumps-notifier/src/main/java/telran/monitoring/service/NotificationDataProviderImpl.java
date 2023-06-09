package telran.monitoring.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import telran.monitoring.model.NotificationData;
@Service
public class NotificationDataProviderImpl implements NotificationDataProvider {
 @Autowired
	private RestTemplate restTemplate;
 @Value("${app.data.provider.mapping.url:data}")
 String mappingUrl;
 
 @Value("${app.data.provider.host:localhost}")
 String host;
 @Value("${app.data.provider.port:8080}")
 int port;
	@Override
	public NotificationData getData(long patientId) {
		ResponseEntity<NotificationData> response =
				restTemplate.exchange(getFullUrl(patientId),
						HttpMethod.GET, null, NotificationData.class);
		return response.getBody();
	}
	private String getFullUrl(long patientId) {
		
		return String.format("http://%s:%d/%s/%d", host,port,mappingUrl,patientId);
	}
	

}
