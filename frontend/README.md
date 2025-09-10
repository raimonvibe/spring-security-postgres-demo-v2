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
