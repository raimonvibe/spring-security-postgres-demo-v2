// src/app/page.js

'use client';  // Enable client-side rendering for hooks

import { useEffect, useState } from 'react';
import { useRouter } from 'next/navigation';

/**
 * Protected home page component.
 * Fetches data from backend's /home endpoint.
 * If not authenticated (401), redirects to /login.
 * Includes logout button.
 */
export default function Home() {
  const [message, setMessage] = useState('Loading...');
  const [error, setError] = useState(null);
  const router = useRouter();

  useEffect(() => {
    // Fetch protected data from backend
    fetch('http://localhost:8080/home', {
      credentials: 'include',  // Send cookies for session auth
    })
      .then((res) => {
        if (!res.ok) {
          if (res.status === 401 || res.status === 403) {
            router.push('/login');  // Redirect if unauthorized
          }
          throw new Error('Failed to fetch');
        }
        return res.text();
      })
      .then((data) => setMessage(data))
      .catch((err) => setError(err.message));
  }, [router]);

  const handleLogout = async () => {
    // POST to logout endpoint
    await fetch('http://localhost:8080/logout', {
      method: 'POST',
      credentials: 'include',
    });
    router.push('/login');  // Redirect after logout
  };

  if (error) return <div>Error: {error}</div>;

  return (
    <div className="container mx-auto p-4">
      <h1 className="text-2xl font-bold">Protected Home Page</h1>
      <p>{message}</p>
      <button
        onClick={handleLogout}
        className="bg-red-500 text-white px-4 py-2 mt-4"
      >
        Logout
      </button>
    </div>
  );
}