CREATE DATABASE security;
\c security
CREATE SCHEMA security;

CREATE USER security_user PASSWORD 'security';
GRANT ALL PRIVILEGES ON SCHEMA security TO security_user;