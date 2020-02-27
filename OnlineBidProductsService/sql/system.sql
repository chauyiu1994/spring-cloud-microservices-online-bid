CREATE DATABASE online_bid
CREATE USER  'root'@'172.17.0.1' IDENTIFIED BY 'guest'
GRANT ALL PRIVILEGES ON * . * TO 'root'@'172.17.0.1';
set global time_zone
= "+8:00";