# Basic Spring and RESTEasy Integration Demo - Wildfly Deployment 

Example of using RESTEasy with:

- Spring
- Wildfly Java EE Full & Web Distribution

The module shows an example to use RESTEasy's basic Spring Framework integration.

## Building the project

```bash
$ mvn clean install
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




## Deploy the WAR package to Wildfly 

```bash
$ mvn jetty:run
```

Using the `curl` command to access this URL:

```bash
$ curl http://localhost:8080/rest/foo
```

It will fetch the value of context parameter `foo` defined in `web.xml`. This shows the injection of `ServletContext` by `@Context` annotation.

And using  the `curl` command to access another URL:

```bash
$ curl http://localhost:8080/rest/foo/hello
```

It will give the `Hello, world!` message provided by autowired bean `FooResource`.
