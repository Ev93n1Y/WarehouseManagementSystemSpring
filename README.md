# WarehouseManagementSystemSpring

Product Application
You need to create a web application that allows you to:
    Perform user registration
    Perform user authorization
    Perform CRUD operations on the following objects:
==============================================================================
Product: 
	UUID; 
	String name; 
	BigDecimal price; 
	Producer of the product;
==============================================================================
Producer: 
	UUID; 
	String name; 
	List of products;
==============================================================================
Role 
	UUID; 
	String name;
==============================================================================
user
	UUID; 
	String email address; 
	String password; 
	String firstName; 
	String lastName; 
	List of roles;
==============================================================================
User roles:
    Admin - perform all CRUD operations
    User - read-only access

The database stores data about producers and products. 
Each product has one producer, and each producer has a list of products.

Pages:
    Producers (list of manufacturers the ability to create new, edit and delete created producers)
    Products (list of products, the ability to create new, edit and delete created products)
    Users (a list of all users of the application, the ability to create new, 
	edit and delete created users) - ONLY for ADMIN (and reading and editing)
==============================================================================
Used technologies: Java, SQL, Spring (MVC, Data, Security, Spring Boot), 
JSP or (HTML and CSS and JS or React Angular), Maven, Tomcat, Git
==============================================================================


Instructions:
-To start application locally:
    clone repository
    open project in your IDE
    start SQL DB
    configure application.poperties acoording to your DB configurations
    start the application
    use link: http://localhost:5000/ to reach the application
    use login: 'admin' password: 'pass' to reach all the functionality
    use login: 'user'  password: '1234' read only functionality
-To start test application from AWS use link: 
http://springwarehousemanagementapp-env.eba-wmc2imbn.us-east-1.elasticbeanstalk.com/

