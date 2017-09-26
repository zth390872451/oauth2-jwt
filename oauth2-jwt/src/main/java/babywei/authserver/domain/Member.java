package babywei.authserver.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Entity - 会员
 * @author umeox
 */
@Entity
@Table(name = "ux_member")
public class Member implements Serializable {
	
	private static final long serialVersionUID = 100007152668889L;
	
	public final static int PUSH_TYPE_DEFAULT = 0;
	public final static int PUSH_TYPE_JPUSH = 1;
	@JsonProperty
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/**
	 * 手机（账号：mobile/email）
	 * 其它补充：  mobile不唯一，值来源还包括： 第三方APP跳转传送的手机号，通过OpenAPI在绑定设备时传入的手机号或第三方OpenID(例 微信OpenID)
	 * 注册的手机号在APP和跳转APP及OpenAPI手机号绑定的互相影响
	 */
	private String mobile; 

	/**
	 * 密码
	 */
	private String password;

	private String authority;

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Member(Member member){
		super();
		this.mobile = member.getMobile();
		this.password = member.getPassword();
	}
	
	public Member() {
	}

	@Column(name = "mobile", nullable = false, unique = true, updatable = false)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<GrantedAuthority> getGrantedAuthority(){
		String[] authority = this.getAuthority().split(";");
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		for (String auth: authority){
			SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(auth);
			grantedAuthorities.add(grantedAuthority);
		}
		return grantedAuthorities;
	}

}
