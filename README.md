# serverclient

default port: 8082

To change port navigate to resources/application.properties
Change the server.port value.

The following project is a clientserver application implemented using spring boot and H2 Database.
It is built using Spring MVC architecture

The following are the clasess implemented <br />

1. Question Class - Which is entity<br />
2. QuestionController Class -  Which accepts the incoming requests to the server<br />
  
    i. getQuestion Method - When the client requests a question it redirects the request service class and get a question to send it to the client<br />
    ii. checkAnswer Method - When the client responds with an answer for the question it redirects to service class for checking the answer<br />
    
3. QuestionService class - Which accepts the incoming requests from controller class and responds to them <br/>
    
    i. randomQuestion - It generates a random three variable question and inserts the question into he database along with the answer</br>
    ii. checkAnswer - It receives the client response to the question and finds the corresponding question in h2 database</br> 
      a. if the response from databse is null or the answer the client returns is wrong we sendd bad response with 400 status</br>
      b. if the response is true it sends 200 response to the client</br>
      
4. QuestionRepository class -  Which implements JpaRepositoryto map the entity to h2database and perform CURD operations</br>





