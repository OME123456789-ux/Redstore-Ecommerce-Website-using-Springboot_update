## RedStore E‑Commerce Website (Spring Boot)

RedStore is a full‑stack e‑commerce application built with **Spring Boot** and **MySQL** on the backend and a modern **HTML/CSS/JavaScript** frontend.  
It demonstrates a complete shopping flow: browsing products, managing a cart, placing orders, tracking orders, managing addresses, and updating user profiles.

---

### Screenshots

**Home page**

![Home](assests/Screenshot%202026-02-18%20204141.png)

**Products page**

![Products](assests/Screenshot%202026-02-18%20204159.png)

**Account page**

![Account](assests/Screenshot%202026-02-18%20204303.png)

**Cart page**

![Cart](assests/Screenshot%202026-02-18%20204330.png)

---

### Features

- **User accounts**
  - Sign‑up and login.
  - Profile page with name, email, phone, gender, and date of birth.
  - Account overview and quick stats (total orders, total spent, saved addresses).

- **Product catalogue**
  - Rich product listing page (`products.html`) with categories, price filters, sorting, and search suggestions.
  - Sample data auto‑inserted into the database on first run by a Spring `DataInitializer`.

- **Cart & checkout**
  - Add products to cart from the home and products pages.
  - View, increase/decrease quantity, or remove items in `cart.html`.
  - Apply simple coupon codes.
  - Multi‑step checkout with payment method selection (card / UPI / net‑banking / COD).
  - Places an order in the backend and then clears the active cart.

- **Orders & tracking**
  - Orders stored in the database with basic status (e.g. `PENDING`, `DELIVERED`).
  - Order history and summary on the **Account → Order History** tab.
  - Track order view that shows a timeline of the order’s progress.

- **Addresses**
  - Manage multiple addresses from **Account → Addresses**.
  - Mark a default address; this is used as the delivery address during checkout.

- **Contact & support**
  - Contact form on the home page that posts messages to the backend.
  - Simple AI‑style chatbot on shopping and products pages for demo UX.

---

### Project structure

- `src/Backend/Shopping-website`
  - `ShoppingWebsiteApplication` – main Spring Boot entry point.
  - `Entity` package – JPA entities:
    - `users`, `products`, `cart`, `Orders`, `Address`, `ContactMessage`.
  - `Repositry` package – Spring Data JPA repositories.
  - `Service` package – business logic for users, products, cart, orders, addresses and contact messages.
  - `Controller` package – REST controllers under `/api/*`.
  - `resources/application.properties` – MySQL connection and JPA configuration.

- `src/Frontend`
  - `shopping.html` – main landing/home page.
  - `products.html` – product listing page.
  - `Login.html`, `signup.html` – authentication pages.
  - `cart.html` – cart and checkout UI.
  - `Account.html` – account overview, profile, addresses, orders, and track‑order.
  - `*.css` – styles corresponding to each page.

- `assests`
  - All images used by the frontend (product photos, logos, banners, and the screenshots shown above).

- `DataBase/E commerce website.sql`
  - Helper SQL to create and quickly inspect/drop tables in the `website` schema (mainly for local development).

---

### Tech stack

- **Backend**
  - Spring Boot (REST API)
  - Spring Web, Spring Data JPA, Hibernate
  - MySQL
  - Java (configured for recent LTS)

- **Frontend**
  - HTML5, CSS3, vanilla JavaScript
  - Font Awesome icons

- **Build & tooling**
  - Maven (`pom.xml` in `src/Backend/Shopping-website`)

---

### Backend – configuration & running

- **Database**
  - Make sure MySQL is running and create a database:
    - Name: `website`
  - Update the username/password in:
    - `src/Backend/Shopping-website/src/main/resources/application.properties`

- **Run the Spring Boot app**
  1. Open the project in your IDE.
  2. Import the Maven project from `src/Backend/Shopping-website`.
  3. Run the main class:
     - `com.example.Shopping.website.ShoppingWebsiteApplication`
  4. The backend will start on `http://localhost:8080`.

- **Important endpoints (prefix: `/api`)**
  - `POST /api/users` – register a new user.
  - `POST /api/users/login` – login with username and password.
  - `GET /api/products` – list all products.
  - `GET /api/cart/{username}` – get active cart items for a user.
  - `POST /api/cart` – add item to cart.
  - `PUT /api/cart/{id}` – update quantity.
  - `DELETE /api/cart/{id}` / `DELETE /api/cart/clear/{username}` – remove/clear cart.
  - `POST /api/orders` – place an order.
  - `GET /api/orders/{username}` – get orders for a user.
  - `GET /api/addresses/{username}` – list addresses for a user.
  - `POST /api/addresses` – create address.
  - `POST /api/contact-messages` – send a contact message.

---

### Frontend – running and behaviour

- The frontend is plain static files under `src/Frontend`.  
  You can open them in a browser in two ways:

- **Option 1 – using a simple static server (recommended)**
  - Serve the `src/Frontend` directory with any static server (VS Code Live Server, `http-server`, etc.).
  - Ensure the backend is running at `http://localhost:8080` so all `fetch` calls to `/api/*` work.

- **Option 2 – opening HTML files directly**
  - You can double‑click `shopping.html` / `Login.html` etc.
  - Some browsers restrict cookies with `file:///` URLs; for a smoother login/cart experience, prefer Option 1.

- **Authentication on the frontend**
  - When you login successfully, the frontend stores:
    - `auth_username`
    - `auth_name`
  - in both **cookies** and **localStorage**.
  - All major pages (`shopping.html`, `products.html`, `cart.html`, `Account.html`) read these values to:
    - Show the **Account** menu instead of **Login / Sign Up**.
    - Load the correct user cart and orders from the backend.

---

### Notes & limitations

- Passwords are currently stored in plain text in the database and checked directly in the login controller.  
  This is acceptable for a demo but **not safe for production**. For real use, introduce Spring Security and hash passwords (e.g., BCrypt).
- CORS is open to all origins for development convenience.
- Database schema evolution is handled by Hibernate (`ddl-auto=update`) instead of a migration tool.

---

### How to extend

- Secure authentication and authorization with Spring Security.
- Add proper order statuses and admin features for managing products and orders.
- Replace static product lists with dynamic data entry through an admin UI.
- Move from plain HTML/JS to a SPA framework (React, Angular, Vue) if needed.
