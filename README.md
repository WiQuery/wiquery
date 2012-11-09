WiQuery
======

WiQuery is a project to simply Wicket integration with jQuery and jQuery UI.


WiQuery Core
======

The core project does not contain much:
 * some interfaces and classes which were used with Wicket before 6.0 and are now deprecated.
 * a basic classes to create events, mostly for jQuery UI.
 * a basic API to create javascript functions and options to be used in behaviors or events.

The jQuery javascript is not included in WiQuery but in Wicket. In order to use a specific version one can add the following code in their Application#init():

```java
getJavaScriptLibrarySettings().setJQueryReference(...);
```

where ... is the instance of your custom jquery resource reference.
Alternatively you can register a resource replacement.


```java
addResourceReplacement(WiQueryCoreThemeResourceReference.get(), new WiQueryCoreThemeResourceReference("foo"));
```

WiQuery JQuery UI
======

Here is where the fun starts, this project contains components and behaviors for enriching your application with jQuery UI.

One can override the default theme with a custom theme (for example 'foo') by creating a package called org.odlabs.wiquery.themes.foo and adding the following code in their Application#init():

```java
addResourceReplacement(WiQueryCoreThemeResourceReference.get(), new WiQueryCoreThemeResourceReference("foo"));
```

