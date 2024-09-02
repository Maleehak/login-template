# login-template
Registration and Login with React and Springboot

<details>
  <summary>Table of Contents</summary>
  <ol>
    <li><a href="#features">Features</a></li>
    <li><a href="#demo">Quick Demo</a></li>
    <li><a href="#architecture">Architecture</a></li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li>
          <a href="#before-you-start">Before you start</a>
          <ul><li><a href="#backend">Backend</a></li></ul>
          <ul><li><a href="#frontend">Frontend</a></li></ul>
        </li>
      </ul>
      <ul>
        <li>
          <a href="#installation-guide">Installation guide</a>
          <ul><li><a href="#google-signin">Google Signin</a></li></ul>
          <ul><li><a href="#twilio-account">Twilio Account</a></li></ul>
          <ul><li><a href="#sending-email">Sending an Email using your Google account</a></li></ul>
          <ul><li><a href="#generating-jwt-secret-key">Generating JWT Secret Key</a></li></ul>
        </li>
      </ul>
    </li> 
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
  </ol>
</details>

## Features
1. Custom Login and Registraion
2. OAuth2 Login with Google
3. Email Notification Generation on Custom Signup/Login using Java Mail Sender
4. Customizable Email Template
5. Phone notification on custom Signup/Login using Twilio
   
## Demo
1. Login with Google
<p align="center">
  <img width="500" alt="Screenshot 2024-09-03 at 1 05 43 AM" src="https://github.com/user-attachments/assets/474fd44e-e488-4f57-980a-3b36a89bfe34">
</p>

2. Email Notification
<p align="center">
<img width="500" alt="Screenshot 2024-09-02 at 11 55 30 PM" src="https://github.com/user-attachments/assets/9ff9e7df-5e40-4a55-8859-e4a09d7a81a3">
</p>

3. Phone Notification
<p align="center">
<img width="500" alt="Screenshot 2024-09-02 at 11 55 30 PM" src="https://github.com/user-attachments/assets/2e8354a5-1d0b-49e7-9be8-ac9cde5fb84f">

</p>

## Architechture
Backend is built using Java Springboot with Postgres DB. And frontend is built using React

### Key Architechture decisions

1. Strategy Design Pattern to implement different login strategies i.e. Custom Login/ Login using Google
2. Event driven programming/Observer pattern is used to generate async email and mobile notifications without slowing down the main flow
3. Liquibase is for Postgres DB change management to easily sync the changes if there are multiple db involved(i.e. dev, qa, prod)
4. OAuth2 flow is used for Login via Google 

#### Low Level Backend System Design

If you want to understand how different components are communication with each other, you can view the detailed low level diagram below:
<p align="center">
  <img width="614" alt="Screenshot 2024-09-03 at 12 37 54 AM" src="https://github.com/user-attachments/assets/bbbc8b2b-8a91-41c4-9a7a-b1c2a46d38a8">
</p>

#### Google OAuth2 Flow

1. Frontend requests the ID token from Google
2. Google returns the ID token in the callback
3. Frontend sends the ID token to the backend
4. Backend validates the ID token received from the client
5. Backend creates an account in the databse 
7. Backend generates a custom JWT
8. Backend sends the custom JWT in the header to the client
9. Client includes the JWT in the request header for API requests
10. JWT filter validates the JWT in each client request header

Here's how OAuth2 flow works:
<p align="center">
<img width="500" alt="Screenshot 2024-09-03 at 1 05 43 AM" src="https://github.com/user-attachments/assets/36b2f510-6a06-4772-9b22-ef14f109d931">
</p>


## Getting Started
_Below is an example of how you can setup the project locally._

### Before you start
This project contains third party integrations. You need to gnerate the relevant API keys and add them in the project 

#### Google Signin

If you want to use this module, first you have to generate the Google OAuth2 credentials. Refer to this guide for assistance: [Google OAuth Credentials](https://docs.retool.com/data-sources/guides/authentication/google-oauth). While configuring 'Authorized JavaScript origins' in the Google Developer Console's Credential settings, remember to add both http://localhost and http://localhost:3000.
After generating the client id:
1. Update <CLIENT_ID> in GoogleLogin.js with your Google client ID in the React app
2. Also replace <GOOGLE-CLIENT-ID> with your client id in the application.properties in the Springboot app
`app.googleClientId=<GOOGLE-CLIENT-ID>`

#### Twilio Account
To send messages on phone numbers, you need to configure Twilio account. Follow this guide for reference: [Twilio SMS](https://www.twilio.com/en-us/messaging/channels/sms)
After creating the account, you will get a Twilio number, sid and token, add them to the application.properties in the Springboot app

```
twilio.sms.sender=<TWILIO-GENERATED-NUMBER>
twilio.sid=<TWILIO-SID>
twilio.token=<TWILIO-TOKEN>
```

#### Sending an Email using your Google account
If you want to send email notifications using your Google account (i.e. [maleehakdev@gmail.com](mailto:maleehakdev@gmail.com)), you need to enable 2 factor authentication on your Gmail account and create a app password. For more information, visit [Google docs](https://support.google.com/accounts/answer/185833?hl=en). Once you get the app password, place it in the application.properties in the Springboot app 

```
spring.mail.password=<YOUR_PASSWORD>
```

#### Generating JWT Secret Key
Use [this](https://www.devglan.com/online-tools/hmac-sha256-online?ref=blog.tericcabrel.com) website to generate the JWT secret key and place it in the application.properties in the Springboot app 

```
security.jwt.secret-key=<SECRET-KEY>
```

### Installation guide

#### Backend
1. Install and setup Java 11 on your system
2. Clone the repo
   ```sh
   git clone https://github.com/Maleehak/login-template.git
   ```
3. Run the below command to start up the backend application:
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```
   
#### Frontend
1. Install NPM packages
   ```sh
   npm install
   ```
2. Point the application to local backend server to call the APIs  

<!-- CONTRIBUTING -->

## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request


<!-- LICENSE -->

## License
Distributed under the MIT License. See `LICENSE.txt` for more information.


