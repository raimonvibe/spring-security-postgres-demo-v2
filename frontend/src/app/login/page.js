// src/app/login/page.js

'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';

/**
 * Login page component.
 * Submits form to backend /login.
 * On success, redirects to home (/).
 * Handles errors for bad creds.
 */
export default function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState(null);
  const router = useRouter();

  const handleSubmit = async (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append('username', username);
    formData.append('password', password);

    const res = await fetch('http://localhost:8080/perform_login', {
      method: 'POST',
      body: formData,
      credentials: 'include',  // For session cookies
      redirect: 'manual',  // Handle 302 redirect manually
    });

    if (res.status === 200 || res.status === 302) {
      router.push('/');  // Success: Go to home
    } else {
      setError('Invalid credentials');
    }
  };

  return (
    <div className="container mx-auto p-4">
      <h1 className="text-2xl font-bold">Login</h1>
      <form onSubmit={handleSubmit} className="mt-4">
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          className="border p-2 mr-2"
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          className="border p-2 mr-2"
        />
        <button type="submit" className="bg-blue-500 text-white px-4 py-2">
          Login
        </button>
      </form>
      {error && <p className="text-red-500 mt-2">{error}</p>}
    </div>
  );
}
