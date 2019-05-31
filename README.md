
## Framework

The app is build using spring-boot 2.0

## Exposed URL

the application respond on POST on `${host}/${context}/passthrough`

it expects a `SAMLResponse` param to be posted
it also expects a `localPort` query param to be able on the referer of the request. 
If it's not present, it'll default to `8080`

The name of the parameter can be overridden by setting the `passthrough.localPortParamName` property
The default port can be overridden by setting the `passthrough.defaultPort` property

## Health check

The application will report its status on `${host}/${context}/actuator/health`

## Run locally

run 
```
./mvnw spring-boot:run
```

## Build

```
./mvnw clean package

```
will produce a war file in `cli-login-passthrough-${version}.war`