package cn.et;




import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;



@Configuration
public class Config {
	
	
	
	@Value("${password}")
	private String password;
	
	@Value("${driverClass}")
	private String driverClass;
	
	@Value("${url}")
	private String url;
	
	@Value("${username1}")
	private String username1;
	
	@Bean
	public DataSource myDataSource(){
		DruidDataSource dds=new DruidDataSource();
		dds.setUrl(url);
		dds.setDriverClassName(driverClass);
		dds.setUsername(username1);
		dds.setPassword(password);
		return dds;
	}
}
