# Overview
* Build a environment to focus on development
* Only for Rest API use, If you need an template engine, you need to add template engine like thymeleaf, freemarker

<br/>

## Dev Env
Spring Boot 2.1.4.RELEASE    
Kotlin 1.3.31  
JDK 11  
JPA (Spring Data)    
H2 or Mysql  
Oauth2 (JWT)

Default Action Profile : local 


<br/>

Local
---
H2 Console Url : http://localhost:8080/h2-console  
Devtools Setting Demo Url : https://haviyj.tistory.com/11


<br/>
<br/>


## Generate jks file
~~~
keytool -genkeypair -alias jwt -keyalg RSA -keypass password -keystore jwt.jks -storepass <your password>

keytool -list -rfc --keystore jwt.jks | openssl x509 -inform pem -pubkey
~~~


<br/>
<br/>


### TO DO

* security ( session or oauth or both )
* redis(embedded, lecture)
* test code(spock? junit?)

* check @Autowired use 




reference : 
https://github.com/dzinot/spring-boot-oauth2-jwt