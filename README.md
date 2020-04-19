# Basic Spring and RESTEasy Integration Demo - Wildfly Deployment 

Example of using RESTEasy with:

- Spring
- Wildfly Java EE Full & Web Distribution

The module shows an example to use RESTEasy's basic Spring Framework integration.

## Building the project

```bash
$ mvn package
...
[INFO] Building war: works/jboss-deployment-struct-demo/target/jboss-deployment-demo.war
...
[INFO] BUILD SUCCESS
...
```

## Start Wildfly Server

```bash
$ pwd
works/wildfly-19.0.0.Final/bin
```

```bash
$ ./standalone.sh
...
18:17:37,530 INFO  [org.jboss.as] (Controller Boot Thread) WFLYSRV0025: WildFly Full 19.0.0.Final (WildFly Core 11.0.0.Final) started in 5462ms - Started 641 of 868 services (379 services are lazy, passive or on-demand)
```

## Connect to Wildfly Server

```bash
$ pwd
works/wildfly-19.0.0.Final/bin
```

```bash
$ ./jboss-cli.sh
You are disconnected at the moment. Type 'connect' to connect to the server or 'help' for the list of supported commands.
[disconnected /] connect localhost
[standalone@localhost:9990 /]
```

## Deploy the project WAR file into server

```bash
[standalone@localhost:9990 /] deploy works/jboss-deployment-struct-demo/target/jboss-deployment-demo.war --force
```

Server log output:

```bash
06:26:45,643 INFO  [org.wildfly.extension.undertow] (ServerService Thread Pool -- 117) WFLYUT0021: Registered web context: '/jboss-deployment-demo' for server 'default-server'
```

## Access the service

Using the `curl` command to access this URL:

```bash
$ curl -v http://localhost:8080/jboss-deployment-demo/rest/foo
```

It will fetch the value of context parameter `foo` defined in `web.xml`. This shows the injection of `ServletContext` by `@Context` annotation.

The output:

```bash
curl -v http://localhost:8080/jboss-deployment-demo/rest/foo
*   Trying ::1...
* TCP_NODELAY set
* Connection failed
* connect to ::1 port 8080 failed: Connection refused
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to localhost (127.0.0.1) port 8080 (#0)
> GET /jboss-deployment-demo/rest/foo HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.64.1
> Accept: */*
> 
< HTTP/1.1 200 OK
< Connection: keep-alive
< Content-Type: application/octet-stream
< Content-Length: 3
< Date: Sun, 19 Apr 2020 22:29:18 GMT
< 
* Connection #0 to host localhost left intact
bar* Closing connection 0
$ 
```

The above URL shows the `bar` output, which is fetched from `context-param` in `web.xml`.

Then test this URL:

```bash
$ curl http://localhost:8080/jboss-deployment-demo/rest/foo/hello
Hello, world!
```

It will give the `Hello, world!` message provided by autowired bean `FooResource`.

Access this URL to test client invocation:

```bash
$ curl http://localhost:8080/jboss-deployment-demo/rest/client   
bar                        
```
