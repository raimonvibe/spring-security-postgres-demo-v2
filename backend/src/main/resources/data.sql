-- This script runs automatically on startup to insert initial users.
-- Passwords are pre-hashed with BCrypt (cost 10).
-- Use tools like online BCrypt generators or code to create these hashes.
-- Example: For plain password 'admin', a sample hash is used below.
-- In real apps, hash passwords dynamically during user creation.

-- Insert admin user
INSERT INTO users (username, password, role)
VALUES ('admin', '$2a$10$WvvTPHKwdBJNz78hA4VJ3.6Cpa/PR3K8j9jIM5uekzd4alREjyf9y', 'ADMIN')
ON CONFLICT (username) DO NOTHING;  -- Avoid duplicates if script runs multiple times

-- Insert regular user
INSERT INTO users (username, password, role)
VALUES ('user', '$2a$10$3euPcmQleNYZwAWCYPRYPe1vf5mUaIyIqo4uz5RywD.5YKFznHH36', 'USER')
ON CONFLICT (username) DO NOTHING;