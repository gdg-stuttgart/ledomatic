============================================
Edition for GAE - dependencies between JAR files
============================================


Below is a list of the dependencies between Restlet libraries. You need to ensure 
that all the dependencies of the libraries that you are using are on the classpath
of your Restlet program, otherwise ClassNotFound exceptions will be thrown.

A minimal Restlet application requires the org.restlet JAR.

To configure connectors such as HTTP server or HTTP client connectors, please refer
to the Restlet User Guide: http://wiki.restlet.org/docs_2.0/

org.restlet.ext.atom (Restlet Extension - Atom)
--------------------
 - nothing beside org.restlet JAR.

org.restlet (Restlet Core)
-----------
 - J2SE 5.0

org.restlet.ext.crypto (Restlet Extension - Crypto)
----------------------
 - nothing beside org.restlet JAR.

org.restlet.ext.freemarker (Restlet Extension - FreeMarker)
--------------------------
 - org.freemarker_2.3

org.restlet.ext.gwt (Restlet Extension - GWT)
-------------------
 - com.google.gwt_2.0
 - javax.servlet_2.5

org.restlet.ext.jaas (Restlet Extension - JAAS)
--------------------
 - nothing beside org.restlet JAR.

org.restlet.ext.jackson (Restlet Extension - Jackson)
-----------------------
 - jackson-core-asl_1.4
 - jackson-mapper-asl_1.4

org.restlet.ext.jaxb (Restlet Extension - JAXB)
--------------------
 - javax.xml.bind_2.1
 - com.sun.jaxb_2.1
 - javax.xml.stream_1.0

org.restlet.ext.jaxrs (Restlet Extension - JAX-RS)
---------------------
 - javax.activation_1.1
 - org.apache.commons.fileupload_1.2
 - javax.mail_1.4
 - javax.xml.bind_2.1
 - com.sun.jaxb_2.1
 - javax.ws.rs_1.0
 - org.json_2.0
 - javax.servlet_2.5
 - javax.xml.stream_1.0

org.restlet.ext.jibx (Restlet Extension - JiBX)
--------------------
 - org.jibx_1.2

org.restlet.ext.json (Restlet Extension - JSON)
--------------------
 - org.json_2.0

org.restlet.ext.lucene (Restlet Extension - Lucene)
----------------------
 - org.apache.commons.io_1.4
 - org.apache.lucene_2.9
 - org.apache.solr_1.4
 - org.apache.solr.common_1.4
 - org.apache.tika_0.6
 - org.apache.tika.parsers_0.6

org.restlet.ext.net (Restlet Extension - Net)
-------------------
 - nothing beside org.restlet JAR.

org.restlet.ext.odata (Restlet Extension - OData service)
---------------------
 - org.freemarker_2.3

org.restlet.ext.rdf (Restlet Extension - RDF)
-------------------
 - nothing beside org.restlet JAR.

org.restlet.ext.rome (Restlet Extension - ROME)
--------------------
 - org.jdom_1.1
 - com.sun.syndication_1.0

org.restlet.ext.servlet (Restlet Extension - Servlet)
-----------------------
 - javax.servlet_2.5

org.restlet.ext.spring (Restlet Extension - Spring Framework)
----------------------
 - net.sf.cglib_2.2
 - org.apache.commons.logging_1.1
 - javax.servlet_2.5
 - org.springframework.asm_3.0
 - org.springframework.beans_3.0
 - org.springframework.context_3.0
 - org.springframework.core_3.0
 - org.springframework.expression_3.0
 - org.springframework.web_3.0
 - org.springframework.webmvc_3.0

org.restlet.ext.velocity (Restlet Extension - Velocity)
------------------------
 - org.apache.commons.collections_3.2
 - org.apache.commons.lang_2.5
 - org.apache.velocity_1.6

org.restlet.ext.wadl (Restlet Extension - WADL)
--------------------
 - nothing beside org.restlet JAR.

org.restlet.ext.xml (Restlet Extension - XML)
-------------------
 - nothing beside org.restlet JAR.
