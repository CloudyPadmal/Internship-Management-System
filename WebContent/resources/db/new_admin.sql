INSERT INTO admin_table (username, password, user_type) VALUES ('root', '$2a$10$lgvZiWuSgPzSyVfP5bpKuOLAMz4uPKueRfNyNer5ZudvkLxyKPozC', 'ROLE_ADMIN');

int Result=s.executeUpdate("CREATE DATABASE databasename");