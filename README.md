fluent-remoting
===============

More convienent and useful interface to manage remote (Java RMI) endpoints


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