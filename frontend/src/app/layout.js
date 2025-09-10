// src/app/layout.js

import './globals.css';  // Import global styles

/**
 * Root layout component that wraps all pages.
 * Includes metadata and common HTML structure.
 */
export const metadata = {
  title: 'Spring Security Frontend Demo',
  description: 'Next.js frontend for Spring Security auth',
};

export default function RootLayout({ children }) {
  return (
    <html lang="en">
      <body>{children}</body>
    </html>
  );
}