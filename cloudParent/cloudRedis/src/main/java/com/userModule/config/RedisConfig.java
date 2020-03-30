package com.userModule.config;
import com.userModule.message.MessageReceive;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 描述：Redis Config 注册
 * @author lytao123
 */
@Configuration
public class RedisConfig {
	/**
	 * 1.创建 JedisPoolConfig 对象。在该对象中完成一些连接池的配置
	 */
	@Bean
	@ConfigurationProperties(prefix="spring.redis.pool")
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		
		return jedisPoolConfig;
	}

	/**
	 * 2.创建 JedisConnectionFactory：配置 redis 连接信息
	 */
	@Bean
	@ConfigurationProperties(prefix="spring.redis")
	public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {

		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);
		
		return jedisConnectionFactory;
	}

	/**
	 * 3.创建 RedisTemplate:用于执行 Redis 操作的方法
	 */
	@Bean
	public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
		
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		// 关联
		redisTemplate.setConnectionFactory(jedisConnectionFactory);
		// 为 key 设置序列化器
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		// 为 value 设置序列化器
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		
		return redisTemplate;
	}

	/**
	 * redis消息监听器容器
	 * 可以添加多个监听不同话题的redis监听器，只需要把消息监听器和相应的消息订阅处理器绑定，该消息监听器
	 * 通过反射技术调用消息订阅处理器的相关方法进行一些业务处理
	 * @param connectionFactory
	 * @param listenerAdapter
	 * @return
	 */
	@Bean //相当于xml中的bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
											MessageListenerAdapter listenerAdapter) {

		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		//订阅了一个叫chat 的通道
		container.addMessageListener(listenerAdapter, new PatternTopic("chat"));
		//这个container 可以添加多个 messageListener
		return container;
	}

	/**
	 * 消息监听器适配器，绑定消息处理器，利用反射技术调用消息处理器的业务方法
	 * @param receiver
	 * @return
	 */
	@Bean
	MessageListenerAdapter listenerAdapter(MessageReceive receiver) {
		//这个地方 是给messageListenerAdapter 传入一个消息接受的处理器，利用反射的方法调用“receiveMessage”
		//也有好几个重载方法，这边默认调用处理器的方法 叫handleMessage 可以自己到源码里面看
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

	/**redis 读取内容的template */
	@Bean
	StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
		return new StringRedisTemplate(connectionFactory);
	}



}

