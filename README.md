# OTP Manager 📱  

An Android application built with **Kotlin** and **Jetpack Compose** that demonstrates **OTP-based authentication** using the **Twilio API**. The app also includes **contact management (CRUD)** features with a modern UI and a clean MVVM architecture.  

---

## ✨ Features  

- 📲 **Send OTP via SMS** using **Twilio API**  
- 👥 **Contact Management**: Add, edit, delete, and list contacts using **Room** database  
- 🎨 **Modern UI** with **Jetpack Compose**  
- 🏗️ **MVVM + Repository architecture** separating UI, data, and network layers  
- 🌐 **Retrofit** integration for API communication  
- 🔐 Secure handling of credentials using **BuildConfig fields**  

---

## 📸 Screenshots  

 <p align="center">
  <img src="screenshots/1 contact list.png" width="250" style="margin: 10px;"/>
  <img src="screenshots/2 contact detail.png" width="250" style="margin: 10px;"/>
  <img src="screenshots/3 create contact.png" width="250" style="margin: 10px;"/>
   <img src="screenshots/4 no internet.png" width="250" style="margin: 10px;"/>
  <img src="screenshots/5 invalid phone num.png" width="250" style="margin: 10px;"/>
  <img src="screenshots/6 send otp.png" width="250" style="margin: 10px;"/>
   <img src="screenshots/7 otp received.png" width="250" style="margin: 10px;"/>
</p>

---

## 🎥 Video Demo  

[![Watch the demo](https://img.youtube.com/vi/ED4h344_pQk/0.jpg)](https://youtu.be/ED4h344_pQk)

---

## 🛠️ Tech Stack  

- **Kotlin**  
- **Jetpack Compose**  
- **Room Database**  
- **Retrofit**  
- **Coroutines**  
- **Navigation Component**  
- **MVVM + Repository Pattern**  

---

## ⚙️ Setup  

1. Clone this repo  
   ```bash
   git clone https://github.com/yourusername/otp-manager.git

2. Add your Twilio credentials in local.properties

  ```bash
  TWILIO_ACCOUNT_SID=your_sid_here
  TWILIO_AUTH_TOKEN=your_auth_token_here
  TWILIO_PHONE_NUMBER=your_twilio_number_here
  ```

3. Build & run the app 🚀

- ⚠️ Note: This project uses the Twilio trial version — phone numbers must be verified in Twilio console before sending SMS.

## 📚 Architecture

- The app follows **MVVM + Repository pattern**:
- **UI Layer (Compose + NavGraph)** → interacts with ViewModel
- **ViewModel** → handles business logic, state, and exposes data/events
- **Repository** → bridges Room (local DB) + Retrofit (network)
- **Data Layer** → Room for persistence, Retrofit for Twilio API

## 🚀 Future Improvements

- OTP **verification screen** (enter OTP and validate)
- Migration to a **backend service** for storing Twilio credentials securely
