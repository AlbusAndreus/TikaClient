# TikaClient
Tika Client to extract metadata from file and push to server for cataloging

This program requires the server program counterpart to send metadata to. The server program will receive the files and will put them into an SQLite database for storage. 
This program could be implemented on a client computer while the server counterpart could be used on a server computer to store the database on a server with more storage
The program is scaleable and it can be used to send multiple files one after the other.
perhaps in a later version, I will make the IP Address changeable for the user to be able to connect to any ip address the server program is running on. 
