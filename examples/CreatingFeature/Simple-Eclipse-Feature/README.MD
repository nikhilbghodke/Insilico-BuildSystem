# Building a Eclipse Feature Example Project

This is an example project about how to build an Eclipse Feature using the `com.github.niikhilghodke.featureBuilder` plugin.
Eclipse Feature are nothing but collection of Eclipse plugins and other Eclipse Features which all together provide a functionality.
Which plugins and features will a Eclipse feature include is described inside the `feature.xml` file. An Eclipse feature is nothing but a simple 
archive containing `feature.xml` file which describes the feature.

## How to build the project

Inorder to build this example project follow these steps
1) Open this this example project on your computer.
2) Open command prompt and type `./gradlew jar`
These two simple steps will build your Eclipse feature. You can find the built OSGi bundle in `build/libs` directory.
Also just the `feature.xml` in `build/features` directory. 

## Explaining the example project in detail

1) The following part of `build.gradle` file is important for building OSGi bundle.

```groovy

        jar{

        feature{
            
                label="this is beatutiful"

            description{
                url="httpsasss"
                context="nikhil is gooodss"
            }

            copyright{
                url="copy"
                context="copy is good"
            }

            license{
                url="license"
                context="license"
            }


            includes("org.eclipse.egit.mylyn","1.2.2"){
                os="linux,macosx"
                ws="wpf"
                arch="x86_64"
                nl="ar"

            }
            includes("org.eclipse.e4.rcp.source","1.2.2"){
                os="linux,macosx"
                ws="wpf"
                arch="x86_64"
                nl="ar"

            }

            plugin("org.eclipse.egit.mylyn","1.2.2"){
                os="linux,macosx"
                ws="wpf"
                arch="x86_64"
                nl="ar"

            }

require{
                importPlugin("org.eclipse.egit.mylyn"){
                    version="1.2.2"
                    match="perfect"
                }
                importFeature("org.eclipse.something"){
                    version="1.2.3"
                    match="perfect"
                    patch=true
                }
               
            }

        }
}

```

1) Writing Description for feature
```groovy
            description{
                url="httpsasss"
                context="some description"
            }
 ```
 one can easily specify any description for feature inside the `description` block. use `url` property if you want to reference any other 
 documentation and use `context` property if you want to write some description inside the features itself.
 
 2) Writing Copyright information
 ```groovy
            copyright{
                url="httpsasss"
                context="some copyright info"
            }
 ```
 one can easily specify any copyright info for feature inside the `copyright` block. use `url` property if you want to reference any other 
 documentation and use `context` property if you want to write some description inside the features itself.
 
3) Writing License information
 ```groovy
            license{
                url="httpsasss"
                context="some license info"
            }
 ```
 one can easily specify any license info for feature inside the `license` block. use `url` property if you want to reference any other 
 documentation and use `context` property if you want to write some description related to license inside the features itself.
 
 4) Including another Feature
 ```groovy
 includes("org.eclipse.egit.mylyn","1.2.2"){
                os="linux,macosx"
                ws="wpf"
                arch="x86_64"
                nl="ar"
            }
 ```
 in order to include another feature in your the feature use the above dsl, `includes` is a function which takes two parameters and a closure to gather information related for required feature.The first parametr is the `id` of feature, second parameter is `version` of required feature and the closure is used to configure optional parameters.
 
 ### Information regarding optional Parameters is as folows
 a)optional - it is possible to include a feature as optional when this attribute is "true". Users are allowed to not install optional  
 features, disable them if they are installed, and install them later. A missing optional feature is not treated as an error.
 
 b)name - if an optional feature is missing, Eclipse cannot render its name properly. This attribute can be used as a 'place holder' to allow Eclipse to render the name of the optional feature when it is not installed.
 
 c)os - optional operating system specification. A comma-separated list of operating system designators defined by Eclipse (see Javadoc for org.eclipse.core.runtime.Platform). Indicates this entry should only be installed on one of the specified OS systems. If this attribute is not specified, the entry can be installed on all systems (portable implementation). This information is used as a hint by the installation and update support (user can force installation of entry regardless of this setting).
 
 d)arch - optional machine architecture specification. A comma-separated list of architecture designators defined by Eclipse (see Javadoc for org.eclipse.core.runtime.Platform). Indicates this feature should only be installed on one of the specified systems. If this attribute is not specified, the feature can be installed on all systems (portable implementation). This information is used as a hint by the installation and update support (user can force installation of feature regardless of this setting).
 
 e)ws - optional windowing system specification. A comma-separated list of window system designators defined by Eclipse (see Javadoc for org.eclipse.core.runtime.Platform). Indicates this entry should only be installed on one of the specified WS's. If this attribute is not specified, the entry can be installed on all systems (portable implementation). This information is used as a hint by the installation and update support (user can force installation of entry regardless of this setting).
 
 f)nl - optional locale specification. A comma-separated list of locale designators defined by Java. Indicates this entry should only be installed on a system running with a compatible locale (using Java locale-matching rules). If this attribute is not specified, the entry can be installed on all systems (language-neutral implementation). This information is used as a hint by the installation and update support (user can force installation of entry regardless of this setting).
 
 5) Including another feature
```groovy
plugin("org.eclipse.egit.mylyn","1.2.2"){
                os="linux,macosx"
                ws="wpf"
                arch="x86_64"
                nl="ar"

            }
```
  in order to include another plugin in your the feature use the above dsl, `includes` is a function which takes two parameters and a closure to gather information related for required feature.The first parametr is the `id` of plugin, second parameter is `version` of required plugin and the closure is used to configure optional parameters.
  
  ### Optional parameters for Including Plugins are as follows
  
  a) fragment - optional specification indicating if this entry is a plug-in fragment. Default is "false"
  
  b) os - optional operating system specification. A comma-separated list of operating system designators defined by Eclipse (see Javadoc for org.eclipse.core.runtime.Platform). Indicates this entry should only be installed on one of the specified os systems. If this attribute is not specified, the entry can be installed on all systems (portable implementation). This information is used as a hint by the installation and update support (user can force installation of entry regardless of this setting).
  
  c) arch - optional machine architecture specification. A comma-separated list of architecture designators defined by Eclipse (see Javadoc for org.eclipse.core.runtime.Platform). Indicates this feature should only be installed on one of the specified systems. If this attribute is not specified, the feature can be installed on all systems (portable implementation). This information is used as a hint by the installation and update support (user can force installation of feature regardless of this setting).
  
  d) ws - optional windowing system specification. A comma-separated list of window system designators defined by Eclipse (see Javadoc for org.eclipse.core.runtime.Platform). Indicates this entry should only be installed on one of the specified WS's. If this attribute is not specified, the entry can be installed on all systems (portable implementation). This information is used as a hint by the installation and update support (user can force installation of entry regardless of this setting).
  
  e) nl - optional locale specification. A comma-separated list of locale designators defined by Java. Indicates this entry should only be installed on a system running with a compatible locale (using Java locale-matching rules). If this attribute is not specified, the entry can be installed on all systems (language-neutral implementation). This information is used as a hint by the installation and update support (user can force installation of entry regardless of this setting).
  
  f) download-size - optional hint supplied by the feature packager, indicating the download size in KBytes of the referenced plug-in archive. If not specified, the download size is not known (Implementation Note: the implementation needs to distinguish between "not known" and 0 size)
  
  g) install-size - optional hint supplied by the feature packager, indicating the install size in KBytes of the referenced plug-in archive. If not specified, the install size is not known (Implementation Note: the implementation needs to distinguish between "not known" and 0 size)
  
  h) unpack - optional specification supplied by the feature packager, indicating that plugin is capable of running from a jar, and contents of plug-in jar should not be unpacked into a directory. Default is "true".
 
 
 
 
