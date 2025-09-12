# 🛡️ Fullstack Security Demo

![Security Banner](security.png)

This file combines **Frontend** (Next.js) and **Backend** (Spring Boot + PostgreSQL + Spring Security) readme information for a complete secure app demo.

---

## 🌐 Frontend Documentation
# Simple Next.js Frontend for Spring Security Postgres Auth Demo

🚀 **Welcome to the Next.js Frontend Demo!**  
This is a basic Next.js application that serves as a frontend for the Spring Boot backend with Spring Security and PostgreSQL authentication. It includes a login page to authenticate users and a protected home page that fetches data from the backend's secured endpoint (/home). We'll use React hooks for state management, fetch API for HTTP requests, and handle session-based authentication (cookies managed by the browser after login).  

The frontend assumes the backend is running on http://localhost:8080. It demonstrates:  
- Form-based login that submits to the backend's /login endpoint.  
- Accessing a protected API endpoint after successful authentication.  
- Logout functionality by calling the backend's /logout.  
- Simple error handling for failed logins.  

🔒 **Key Concepts Explained (Next.js and Frontend Auth Basics):**  
Next.js is a React framework for server-side rendering, static sites, and API routes. Here's a quick breakdown for this context:  
- **Pages**: Next.js uses file-based routing (e.g., pages/login.js becomes /login).  
- **API Calls**: Use `fetch` to interact with the backend (POST for login, GET for protected resources).  
- **Authentication**: Since the backend uses session cookies (set after successful login), the browser handles auth for subsequent requests—no need for JWT storage in frontend.  
- **State Management**: Basic use of React's `useState` and `useEffect` for login status and data fetching.  
- **Routing**: Use Next.js `useRouter` for navigation after login/logout.  
- **CSRF**: If enabled in backend, you'd need to handle CSRF tokens; but since we disabled it in the demo backend, it's skipped here.  

This frontend uses **Next.js 14+** (with App Router), Node.js 18+, and assumes you have the backend running.  

📊 **Project File Structure Drawing** (ASCII Art Tree):  
Here's a visual representation of the frontend project's directory structure. Imagine this as your root folder named `spring-security-frontend-demo`.  

```
spring-security-frontend-demo/
├── package.json             📄 (Dependencies and scripts)
├── next.config.mjs          ⚙️ (Next.js configuration, if needed)
├── src/
│   ├── app/
│   │   ├── globals.css      🎨 (Global styles)
│   │   ├── layout.js        🏗️ (Root layout component)
│   │   ├── page.js          🏠 (Home page - protected)
│   │   └── login/
│   │       └── page.js      🔑 (Login page)
├── public/                  📂 (Static assets, if any)
└── README.md                📖 (This file you're reading!)
```

That's minimal—focuses on login and protected page. No extra libraries like Redux or Axios (uses built-in fetch).  

🛠️ **Step-by-Step Instructions: How to Create and Run This Frontend**  

1. **Prerequisites** (Get These Ready First):  
   - Node.js 18+ installed (download from nodejs.org).  
   - Yarn or npm (comes with Node.js).  
   - The backend project running: Clone https://github.com/raimonvibe/spring-security-postgres-demo, follow its README to start it on http://localhost:8080.  
   - An IDE like VS Code (recommended).  

2. **Set Up the Project Directory**:  
   - Create a new folder: `mkdir spring-security-frontend-demo && cd spring-security-frontend-demo`.  
   - Initialize Next.js: Run `npx create-next-app@latest .` (use defaults: Yes to TypeScript? No; App Router? Yes; Tailwind? Optional but say Yes for simple styling).  
   - Clean up: Remove unnecessary files like /app/api (we don't need API routes here).  

3. **Add Dependencies to package.json**:  
   - No extra deps needed beyond create-next-app defaults. If using Tailwind, it's already included.  
   - Install if needed: `npm install` or `yarn`.  

4. **Create the Files**:  
   - Set up global styles in globals.css (basic resets).  
   - Root layout in layout.js (wraps all pages).  
   - Login page (login/page.js): Form to submit username/password to backend /login.  
   - Home page (page.js): Fetches from backend /home if authenticated, shows logout button.  
   - Add inline comments explaining each part, especially auth handling.  

5. **Handle Authentication Flow**:  
   - On login: POST to http://localhost:8080/login with form data. If success (302 redirect or check status), navigate to /.  
   - On home: GET to http://localhost:8080/home; if 401/403, redirect to /login.  
   - Logout: POST to http://localhost:8080/logout, then redirect to /login.  

6. **Build and Run**:  
   - Dev mode: `npm run dev` or `yarn dev` (runs on http://localhost:3000).  
   - Access: Open http://localhost:3000/login to start.  
   - Test: Use backend sample creds (user/user or admin/admin). After login, see welcome message from backend.  

7. **Testing the Frontend**:  
   - Visit /login, enter creds → Redirect to / with protected content.  
   - Direct access to / without login → Should show loading or redirect (handled via fetch error).  
   - Logout → Back to /login.  

⚠️ **Security Notes**:  
- In production, use HTTPS and same-site cookies.  
- Proxy API calls via Next.js API routes for CORS if needed (backend might need CORS config).  
- This is basic—add token-based auth (JWT) for more advanced setups.  
- Demo only; secure properly for real apps.  

🎉 **Next Steps**: In the following responses, I'll provide the full code for each file, starting with package.json, then layout.js, and so on. Let me know when you're ready for the next part! If you have questions, ask.  

Made with ❤️ by Grok. Happy coding! 🚀


---

## ⚙️ Backend Documentation
# Simple Spring Security with PostgreSQL Authentication Project

🚀 **Welcome to the Spring Security Postgres Auth Demo!**  
This is a minimal example project demonstrating how to set up user authentication in a Java Spring Boot application using Spring Security, with user details stored in a PostgreSQL database. We'll use JPA (Hibernate) for database interactions, and Spring Security for handling login/logout with form-based authentication.  

The project includes:  
- A simple REST endpoint that's protected (only accessible after login).  
- User registration? No, keeping it basic: Pre-populated users in the DB via a data.sql script.  
- Inline comments in all Java files to explain what's happening.  
- Connection to PostgreSQL for storing/retrieving user credentials (username, password, roles).  

🔒 **Key Concepts Explained (Java Spring Security Basics):**  
Spring Security is a powerful framework for securing Java applications. Here's a quick breakdown:  
- **Authentication**: Verifying who the user is (e.g., username/password check against the DB).  
- **Authorization**: What the user can do (e.g., roles like USER or ADMIN).  
- **UserDetailsService**: A core interface we implement to load user data from PostgreSQL.  
- **PasswordEncoder**: We use BCrypt to hash passwords securely—never store plain text!  
- **SecurityFilterChain**: Configures HTTP security, like which URLs require login.  
- **Form Login**: Default login page provided by Spring Security (customizable).  
- **CSRF Protection**: Enabled by default to prevent attacks.  

This project uses **Spring Boot 3.x** (compatible with Java 17+), Maven for builds, and PostgreSQL 14+ as the DB.  

📊 **Project File Structure Drawing** (ASCII Art Tree):  
Here's a visual representation of the project's directory structure. Imagine this as your root folder named `spring-security-postgres-demo`.  

'''
spring-security-postgres-demo/
├── pom.xml 📄 (Maven build file with dependencies)
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── demo/
│   │   │               ├── DemoApplication.java 🏠 (Main Spring Boot app entry point)
│   │   │               ├── config/
│   │   │               │   └── SecurityConfig.java 🔑 (Spring Security configuration)
│   │   │               ├── controller/
│   │   │               │   └── HomeController.java 🌐 (Simple protected REST controller)
│   │   │               ├── entity/
│   │   │               │   └── User.java 👤 (JPA Entity for users)
│   │   │               ├── repository/
│   │   │               │   └── UserRepository.java 📚 (JPA Repository for user queries)
│   │   │               └── service/
│   │   │                   └── UserService.java 🛡️ (Implements UserDetailsService for auth)
│   │   └── resources/
│   │       ├── application.properties ⚙️ (Config for DB connection, etc.)
│   │       └── data.sql 💾 (SQL script to insert initial users)
│   └── test/ (Optional, but we skip tests for simplicity)
└── README.md 📖 (This file you're reading!)
'''


That's all the files needed—kept minimal to focus on Spring Security + Postgres integration. No extra bloat!  

🛠️ **Step-by-Step Instructions: How to Create and Run This Project**  

1. **Prerequisites** (Get These Ready First):  
   - Java JDK 17+ installed (download from Oracle or Adoptium).  
   - Maven 3.8+ (comes with most IDEs or download from Apache).  
   - PostgreSQL installed and running (e.g., via Docker: `docker run -p 5432:5432 -e POSTGRES_PASSWORD=password -d postgres`).  
   - An IDE like IntelliJ IDEA, Eclipse, or VS Code with Java extensions (optional but recommended).  
   - Create a PostgreSQL database: Connect to Postgres (e.g., via `psql` or pgAdmin) and run `CREATE DATABASE auth_demo;`.  

2. **Set Up the Project Directory**:  
   - Create a new folder: `mkdir spring-security-postgres-demo && cd spring-security-postgres-demo`.  
   - Initialize as a Maven project: Run `mvn archetype:generate -DgroupId=com.example.demo -DartifactId=spring-security-postgres-demo -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false`. (This gives a basic skeleton; we'll edit it.)  
   - Or, use Spring Initializr online: Go to https://start.spring.io, add dependencies (Spring Web, Spring Security, Spring Data JPA, PostgreSQL Driver), generate, and unzip into your folder.  

3. **Add Dependencies to pom.xml**:  
   Open `pom.xml` and ensure it includes these (copy-paste the full file from later responses if needed):  
   - Spring Boot Starter Web  
   - Spring Boot Starter Security  
   - Spring Boot Starter Data JPA  
   - PostgreSQL Driver  
   - (Optional) Lombok for less boilerplate.  

4. **Configure application.properties**:  
   Set up your DB connection details (e.g., URL, username, password). Enable Hibernate to create tables automatically.  

5. **Create the Java Classes**:  
   - Follow the package structure: `src/main/java/com/example/demo/...`.  
   - Implement the User entity with JPA annotations.  
   - Set up the repository, service, config, and a simple controller.  
   - Add inline comments explaining each part (especially SecurityConfig for Spring Security explanations).  

6. **Add data.sql for Initial Users**:  
   Insert sample users with hashed passwords (use BCrypt). Example users: admin/admin (role ADMIN), user/user (role USER).  

7. **Build and Run**:  
   - Build: `mvn clean install`.  
   - Run: `mvn spring-boot:run` or from IDE.  
   - Access: Open http://localhost:8080/home (it should redirect to login).  
   - Login with sample creds. Logout via /logout.  

8. **Testing Authentication**:  
   - Try accessing /home without login → Redirect to /login.  
   - Login as 'user' → Access granted.  
   - For production: Customize login page, add HTTPS, etc. (Not covered here).  

⚠️ **Security Notes**:  
- Always hash passwords!  
- In real apps, use HTTPS and handle exceptions properly.  
- This is for learning—don't use in production without audits.  

🎉 **Next Steps**: In the following responses, I'll provide the full code for each file, starting with pom.xml, then application.properties, and so on. Let me know when you're ready for the next part! If you have questions, ask.  

Made with ❤️ by Grok. Happy coding! 🚀


---

🔒 **Note**: Always keep security in mind when deploying to production. Use HTTPS, environment variables for secrets, and audit dependencies regularly.

Made with ❤️ and 🔐
