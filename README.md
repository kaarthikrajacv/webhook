# webhook
Test Basic Webhook Authentication

Every time we had to depend on third party service like Pipedream to test Webhoks out. 
Pipedream doesn't provide implementation to test out "Basic authentication" & "Token"  authentication out of the box. 
We had to rely on the header value received in Webhook call to test it out.
To automate this, I had written a "Spring App" to test out authentication. By calling this service as end-points inside Webhooks, authentication information is automatically verified and returns a response.
We have endpoints configured as:
"https://web-hooktest.herokuapp.com/api/noauth/{post, put, delete}" - for No Authentication
"https://web-hooktest.herokuapp.com/api/basic/{post, put, delete}" - Basic Authentication
"https://web-hooktest.herokuapp.com/api/token/{post, put}" - Token Auth
We can have Post (or) Put (or) Delete at the end of the URI based on the API Method call available in Webhooks screen.
Basic Authentication details - username: admin, password: pass123
Auth Token - marketer
For Example if I want to test "PUT" method in Webhooks with Token based authentication
I can give endpoint in URL as
"https://web-hooktest.herokuapp.com/api/token/put"
API Method call as "PUT"
and Token as "marketer" in the respective section in Webhook screen
Additionally if we have custom header with key "externalhook" and value as any URI end point, our Webhook will be posted to that endpoint.
All the security filters for "Basic Authentication" and "Token auth "are configured with "Spring Security"
