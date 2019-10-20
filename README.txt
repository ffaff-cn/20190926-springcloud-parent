springcloud:
	1.实现简单的消费者和生产者
		springcloud使用的http协议进行传输数据，也就是说springcloud仍然使用的是RESTFUL的风格

		1.1.搭建工程(！！！springboot的项目是否需要运行都打jar包！！！(springboot工程中不需要war包))
			搭建父级工程
				20190926-springcloud-parent(pom):只做jar包的管理

			搭建常规工程
				20190926-springcloud-management(pom):只做jar包的管理
					--20190926-springcloud-model(jar)
					--20190926-springcloud-mapper(jar)--->依赖于model
					--20190926-springcloud-service(jar)--->依赖于mapper
						@Service:是spring框架所提供的

			搭建服务生产者工程
				20190926-springcloud-provider-management(pom):只做jar包的管理
					20190926-springcloud-provider-8081(jar):需要提供数据，也就是说必须要连接数据库，对数据库做增删改查(springboot-web-starter，mysql驱动包，mybatis包，druid包...)--->依赖于service

			搭建服务消费者工程
				20190926-springcloud-consumer-management(pom):只做jar包的管理
					20190926-springcloud-consumer-6081(jar):只需要和客户端打交道(只需要页面)，不再需要数据的支持，所有的数据来源都来源于provider--->依赖于model

		1.2.添加jar包
			springcloud GreenWich.RS3
			springboot 2.1.8

	2.eureka
		什么是eureka？
		eureka是一个注册中心，实现了dubbo中zookeeper的效果！

		实现eureka工程的搭建
		2.1.单节点版
			2.1.1.创建eureka工程
				20190926-springcloud-eureka-management(pom):只管理jar包
					--20190926-springcloud-eureka-7081(jar):运行了eureka

			2.1.2.jar包的添加
				所有的eureka都只会用到一个jar包
					eureka并不是springcloud公司的，是Netflix公司的产品，目前springcloud正在开发完善的springcloud架构来代替Netflix所提供的组件
					虽然eureka的工程构建仍然需要使用springboot，但是不再需要springboot-web-starter的jar包，因为eureka的jar包中已经有了！！！！！如果添加则出现jar包冲突

				eureka的jar包添加只需要在父工程中添加即可，所有的子工程都可以继承父工程的jar包
				springcloud官网中提供的springcloud2.x版本所规定的jar包格式:
					<dependencies>
				        <dependency>
				            <groupId>org.springframework.cloud</groupId>
				            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
				        </dependency>
				    </dependencies>

			2.1.3.eureka整个开发中，不涉及任何与业务有关的代码
				application.properties
				ApplicationRun.java
					@SpringBootApplication
					@EnableEurekaServer

			2.1.4.把provider-8081注册进eureka中
				在配置zookeeper的时候，无论是服务消费者还是服务生产者，都引入zookeeper的jar包(zkClinet)
				在配置eureka的时候，需要引入么？
					！！很需要！！
					<dependency>
			            <groupId>org.springframework.cloud</groupId>
			            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
			        </dependency>

			    修改application.properties配置文件
			    	在讲zookeeper的时候，zk.address=zookeeper的ip地址
			    	现在使用eureka的时候，也需要eureka的地址(eureka的项目地址)
			    	eureka.client.service-url.defaultZone=http://localhost:7081/eureka

			    在provider-8081项目的主启动类上添加注解
			    	@EnableDiscoveryClient:
			    		springcloud2.x的版本注解
			    	@EnableEurekaClient:
			    		springcloud1.x的版本注解

			2.1.5.配置eureka服务的信息(actuator-info)
				为什么要配置？
					虽然目前eureka注册中心中只有一个服务，但是在真实开发环境中可能有很多个(35个)
					每一个团队/team是有多个人组成的，每一个开发人员开发的功能是不同的，
						eg:
							电商
							cart微服务:zhangsan开发
							order微服务:lisi开发
							portal微服务:wangwu开发
							这些微服务最终都需要注册进eureka中
							假设zhangsan所开发的cart微服务出现问题了，这个问题是lisi发现的
					如果其中有一个服务出现问题，这个服务正好是自己开发的，但是最终lisi看不到主要信息，直接描述不清楚，这种情况是绝对不允许在团队中出现！

					在provider-8081项目的application.properties中进行配置
						配置eureka的实例名(Status下面所显示的名字)
						不允许出现任何的重复！
						eureka.instance.instance-id=虽然可以随意起，但是要求描述出整个微服务的作用

					当鼠标移动到服务名的时候，只会显示localhost，但是根据localhost并不能精准的定位到哪一台服务器，需要把localhost改为IP地址
					# 在eureka中显示IP地址
					eureka.instance.prefer-ip-adderss=true
					配置完毕后显示并不是本机的ip地址
						provider的ip地址显示是相对于eureka做的内网映射
						在eureka的整个注册中心里会指定内部的ip地址，必须要使provider和eureka在同一个内网中，一旦provider成功的注册进了eureka，那么eureka就会给provider分配一个自己的内网ip
					每一台电脑是有两个IP的，一个是公网IP，一个是内网IP

					实现点击服务名显示具体的服务信息
						先添加jar包
							<dependency>
					            <groupId>org.springframework.boot</groupId>
					            <artifactId>spring-boot-starter-actuator</artifactId>
					        </dependency>

						在provider-8081项目的application.properties配置文件中添加:
							info.author.name=Seven Lee
							info.company.name=AAA SOFTWARE EDU
							info.project.description=This is Demo


			2.1.6.eureka的自我保护机制
				如果长时间不连接(不激活)eureka的时候，会出现自我保护机制，在eureka页面上会显示:
					EMERGENCY! EUREKA MAY BE INCORRECTLY CLAIMING INSTANCES ARE UP WHEN THEY'RE NOT. RENEWALS ARE LESSER THAN THRESHOLD AND HENCE THE INSTANCES ARE NOT BEING EXPIRED JUST TO BE SAFE.
				为什么会出现自我保护:
					有没有可能网络阻塞(网络非常卡)，如果发生后，eureka就检测不到provider的心跳
					eureka中有200个provider,如果只有3个provider没有心跳，eureka就会直接把这三台直接剔除
					但是如果有198个provider都没有心跳，则eureka就不会再剔除，也就是说直接会保留这198个provider

				在eureka中注册过的服务会定时向eureka发送心跳
				当网络故障/网络延迟/服务宕机就不会再向eureka发送心跳，eureka从接不到心跳的那一秒开始算起，(90秒)之后会直接把这个服务给剔除
				因为provider的机房停电了，大面积的provider都无法向eureka发送心跳，90秒之后还是没有接收到心跳，eureka就不会再剔除任何一个服务(也就是说会把全部的服务都保留下来)(这个就是eureka的AP性)

				为什么eureka不会剔除大量的服务？
					AP:只保证了服务的可用性，不保证数据的一致性
					CP：只保证了数据的一致性，不保证服务的可用性
					如果eureka把所有的服务全部剔除，当consumer进行访问调用的发现eureka中并没有任何服务了，整个项目都会处于瘫痪状态，整个客户端的体验就会非常差，也就是说相当于报错500！
					如果当大面积服务都没有心跳，eureka不剔除的情况下，consumer进行访问调用的时候依然可以找到所注册服务，然后就可以直接获取到数据，这些数据可能不是最新的数据！

				关闭eureka的自我保护机制:
					！！可以关闭！！但是不能这么做！！
					在eureka-7081项目的application.properties配置文件中
					eureka.server.enable-self-preservation=false(！！关闭eureka的自我保护机制！！)
					提示一下信息:
						THE SELF PRESERVATION MODE IS TURNED OFF. THIS MAY NOT PROTECT INSTANCE EXPIRY IN CASE OF NETWORK/OTHER PROBLEMS.

				现在某些微服务就是不需要eureka的自我保护，就想办法把自我保护机制失效
				provider告诉eureka，我每个5秒会向你发送一次心跳，当最后一次检测我的时间之后过了8秒还没有接收到心跳直接把我剔除！
				在eureka-7081的application.properties配置文件中添加
					# eureka自己检测服务的心跳时间(90秒)
					# 单位是毫秒，先把eureka检测心跳的时间缩短为10秒
					# 也就是说每个10秒就会检测一次服务的心跳
					eureka.server.eviction-interval-timer-in-ms=10000
				在provider-8081的application.properties配置文件中添加
					# 规定自己向eureka发送心跳的时间
					# 单位是秒
					eureka.instance.lease-renewal-interval-in-seconds=5
					# 当eureka最后一次检测到心跳的时间间隔(单位是秒)
					# eg:15:05:20是最后一次检测到心跳-->检测8秒之后还是无法检测心跳的时候直接剔除
					eureka.instance.lease-expiration-duration-in-seconds=8


		2.2.集群版(才是真正的eureka)
			2.2.1.配置eureka的集群之前首先先配置HOSTNAME和IP的映射
				在windows系统中修改hosts文件
				127.0.0.1(localhost) eureka01
				127.0.0.1(localhost) eureka02
				127.0.0.1(localhost) eureka03

			2.2.2.创建7082和7083项目

			2.2.3.修改7081的application.properties配置文件
				eureka.instance.hostname=eureka03
				eureka.client.service-url.defaultZone=http://eureka01:7081/eureka,http://eureka02:7082/eureka

			2.2.4.修改provider-8081项目的application.properties配置文件
				eureka.client.service-url.defaultZone=http://eureka01:7081/eureka,http://eureka02:7082/eureka,http://eureka03:7083/eureka

	3.ribbon
		负载均衡
		nginx也是负载均衡
		!!!!ribbon和nginx的区别是什么？
			nginx:
				正向代理(和客户端连在一起)
				反向代理(和服务器端连在一起)，nginx的负载均衡其实使用的就是反向代理
			ribbon:
				负载均衡是和客户端连在一起的，也就是说ribbon是客户端层面的负载均衡
				负载均衡放在客户端的好处是什么？
					可以让客户端非常直观的看到所有服务器的负载情况，那么客户端一般情况下会选择负载比较少的服务器进行连接(选择游戏大区)
		3.1.ribbon默认自带的有eureka的jar包，也就是如果需要使用ribbon的时候就必须要配置eureka，也就是说需要把自己托管给eureka，ribbon是客户端层面的负载均衡，那么ribbon是配置在consumer中还是provider中？必须要配置在consumer上

		3.2.添加jar包
			添加eureka的client端，ribbon的jar包
	        <dependency>
	            <groupId>org.springframework.cloud</groupId>
	            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
	        </dependency>
	        <dependency>
	            <groupId>org.springframework.cloud</groupId>
	            <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
	        </dependency>

	    3.3.真正的负载均衡
	    	需要创建provider-8082和8083项目

	    3.4.进行配置
	    	8081,8082,8083三个项目中的所有的controller都必须要一致(@RequestMapping("/userAll"))
	    	8081,8082,8083项目的application.properties配置文件必须要更改
	    		eureka的实例id:因为eureka的实例id是不能重复的，所以必须更改
	    			eureka.instance.instance-id=user-provider-8082
	    		spring.application.name不能改变，也就是说8081,8082,8083三个项目的application.name都必须要一样

	    3.5.启动顺序
	    	首先启动三台eureka(7081,7082,7083)
	    	再启动三台provider(8081,8082,8083)
	    	最后启动consumer(6082)

	    3.6.ribbon的负载均衡算法
	    	如果不指定ribbon的算法，则ribbon使用的是轮询！！！
	    	如何指定ribbon的算法？

	    3.7.负载均衡源码解析
	    	IRule:接口
	    	ILoadBalancer:接口
	    	AbstractLoadBalancer(抽象类) --> 实现了ILoadBalancer接口
	    	AbstractLoadBalancerRule(抽象类) --> 实现了IRule接口
	    		有一个属性:ILoadBalance-->getter/setter方法
	    	RandomRule(最终所实现随机算法的地方)--->继承了AbstractLoadBalancerRule
	    		-->实现了IRule接口
	    	RoundRobbinRule--->继承了AbstractLoadBalancerRule
	    		-->实现了IRule接口

	    	IRule:
	    		public Server choose(Object key):选择出一个服务-->提供给consumer使用
	    		public void setLoadBalancer(ILoadBalancer lb):ILoadBalancer-->实现类(AbstractLoadBalancer)
	    		public ILoadBalancer getLoadBalancer()
	    	ILoadBalancer:
	    		！！其实就是和每一个服务打交道！！
	    		getServer(Object key):所有的获取服务都必须要通过key来进行获取


测试提交

