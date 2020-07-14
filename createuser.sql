-- create new user
alter session set "_ORACLE_SCRIPT"=true;
CREATE USER seguritoproject IDENTIFIED BY seguritoproject;

-- grant priviledges
GRANT CONNECT, RESOURCE, DBA TO seguritoproject;

