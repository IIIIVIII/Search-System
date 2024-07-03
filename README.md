# Search-System
Search System
Project Description
This project simulates a simple search server and client interaction. The project includes functionalities for starting the server, sending search queries from the client, and returning search results.

File Structure
Main.java: The main entry point of the project, which starts the search server and performs a search query using the client.
SearchClient.java: Represents the search client that sends search queries to the server.
SearchServer.java: Represents the search server that handles search queries and returns results.
Prerequisites
Java Version: JDK 8 or higher
How to Run
Place all .java files (Main.java, SearchClient.java, SearchServer.java) in the same directory.
Compile the Java files using the following command:
sh
Copy code
javac Main.java SearchClient.java SearchServer.java
Run the program using the following command:
sh
Copy code
java Main
Code Overview
Main.java
This file is the entry point of the program. It demonstrates the interaction between the search server and client by:

Creating an instance of SearchServer.
Creating an instance of SearchClient with the server.
Starting the server and performing a search query using the client.
SearchClient.java
This file represents the search client. Its main functions include:

Sending search queries to the server.
Printing the search results returned by the server.
SearchServer.java
This file represents the search server. Its main functions include:

Starting the server.
Handling search queries and returning results based on predefined data.
