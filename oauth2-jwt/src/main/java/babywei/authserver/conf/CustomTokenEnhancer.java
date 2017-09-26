package babywei.authserver.conf;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @creator ZTH
 * @modifier ZTH
 * @date 2017-09-18
 */
public class CustomTokenEnhancer implements TokenEnhancer {
	@Override
	public OAuth2AccessToken enhance(
			OAuth2AccessToken accessToken,
			OAuth2Authentication authentication) {
		Map<String, Object> additionalInfo = new HashMap<String, Object>();
//		additionalInfo.put("organization", authentication.getName() + randomAlphabetic(4));
		additionalInfo.put("createDate",new Date().getTime());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
		return accessToken;
	}
}