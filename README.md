fluent-remoting
===============

More convienent and useful interface to manage remote (Java RMI) endpoints.

Why would you want to use this technology (currently ancient RMI) - well in my cases I needed fast
and easy to use interface to access my server side components to make assertions in client tests... Perfectly valid use
case and I hope others will find it useful too.

If you are looking for something more sophisticated take a look at
http://static.springsource.org/spring/docs/current/spring-framework-reference/html/remoting.html


===
See example:
```java
FluentRmi.config().setPort(1299);
FluentRmi.config().setHostname("localhost");

FluentRmi.server().bind(object).to("someInterface");

SomeInterface remoteRmi = FluentRmi.client().get("someInterface").as(SomeInterface.class);
```

or

```java
FluentRmi.server().bind(object).to("someInterface");

SomeInterface remoteRmi = FluentRmi.client().get("someInterface").as(SomeInterface.class);
```