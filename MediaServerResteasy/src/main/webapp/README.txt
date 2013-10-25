Test:
 curl -X GET -H "Accept: text/plain" http://localhost:8080/MediaServerResteasy/media/

Alla :
curl -X GET -H "Accept: text/plain" http://localhost:8080/MediaServerResteasy/media/all

Hämta en, sök på UUID:
     * Exempelvis : curl -X GET -H "Accept: application/xml" http://localhost:8080/MediaServerResteasy/media/image/63ec36db-c72e-4039-b1bf-33c5461ce6f4
     * Exempelvis : curl -X GET -H "Accept: application/xml" http://localhost:8080/MediaServerResteasy/media/image/f5547de6-f25c-4c8a-bc05-21b00aa372d3
     * -Check the validity of the uuid
     * <image>
     * <filename>appe</filename>
     * <mimetype>image/jpeg</mimetype>
     * <owner>appe</owner>
     * <uuid>72934fc6-c71d-4667-bded-ee49702b4181</uuid>
     * <visibility>private</visibility>
     * </image>
     *

Radera:
 curl -X DELETE http://localhost:8080/MediaServerResteasy/media/image/768bed28-0805-4c9f-800a-440476f2c823


 curl -X POST --data-binary "{ \"title\":\"H2G2\", \"description\":\"Scifi IT book\", \"illustrations\":\"false\",\"isbn\":\"134-234\",\"nbOfPage\":\"241\",\"price\":\"24.0\" }"  -H "Content-Type: application/json" http://localhost:8080/MediaServerResteasy/media/bubble -v





Problems with create JAXB
-> http://stackoverflow.com/questions/3630827/why-writer-for-media-type-application-json-missing

Detta löser upp :
<dependency>
            <groupId>org.jboss.resteasy</groupId>
            <artifactId>resteasy-jackson-provider</artifactId>
            <version>2.3.2.Final</version>
</dependency>

Problem ; Med ovanstående Jacksson !?
Mkyong har -> http://www.mkyong.com/webservices/jax-rs/integrate-jackson-with-resteasy/ - ej testat
måste det vara ett EJB-Projekt !?
kraschar om jag har :
 <dependency>
		<groupId>org.jboss.resteasy</groupId>
		<artifactId>resteasy-jaxrs</artifactId>
		<version>2.2.1.GA</version>
	</dependency>
