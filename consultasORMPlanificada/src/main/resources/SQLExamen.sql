CREATE DATABASE IF NOT EXISTS AdrianHibernateExamen;

CREATE USER IF NOT EXISTS 'usuario2'@'%' IDENTIFIED BY 'usuario';

GRANT ALL PRIVILEGES ON AdrianHibernateExamen.* TO 'usuario2'@'%';

FLUSH PRIVILEGES;
