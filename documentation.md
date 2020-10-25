# Documentation for the 'Witch Crutch' web application
App for using the services of the witch Crutch.

## Technology stack
- ### Frontend 
  - HTML / JS / CSS
  - Bootstrap v.4.5.2
  - JQuery v.3.5.1
  
- ### Backend
  - Java Servlets v.4.0.1 
  - Freemarker template engine v.2.3.30
  - JDBS PostgreSQL v.42.2.16
 
## Concepts and patterns
- ### Concept of development
  - MVC
- ### Design pattern
  - Repository
- ### Database design technology
  - ORM
  
## Business requirements
- The goal is: 
   - to create a high-quality app;
   - increase sales of witch Crutch services;
   - increase the number of regular customers.
- Problem: clients who want to use the services of the witch Crutch are located in another city and can't always come to her office.
- Our solution: selection and recording of services via the app, the ability to conduct services online via chat.

## Functional requirements
- User authorization and registration, with the option to "remember me".
- User profile. Edit data: profile photo, first name, last name, email, and additional information.
Service history and view the status of active services. Button to go to chat With the witch Crutch.
- Online chat. Designed for online consulting, for users who don't have the opportunity 
to come to the office to the witch Crutch.
- View services. Search by keywords. Filtering by popularity, cost and new products.
- Division of functionality between registered and unregistered users:
    - An unregistered user cannot use the online chat and pay for services.
    - An unregistered user can view the main page and the page with the services provided.
    
## List of pages
- Home page
- Personal account
- Service catalog
- Detailed description of the service
- Authorization
- Registration
- Online chat with a witch
- An order call
     
## System requirements
 - Using sessions in authorization.
 - Using cookies to remember me.
 - At least 3 forms with different widgets-text, textarea, select, check, file-field.
 - Password hashing.
 - Full implementation of the MVC concept.
 - Validation of data (on the client and server) entered by the user.
 - Using ajax requests for search/verification.
 - Workability of all pages.
 - User-friendly interface for working with tracks.
 - Fast operation and loading of the site.
 - Notifying the user about errors when entering information on the site.
