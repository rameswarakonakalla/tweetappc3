CREATE KEYSPACE tweetapp with replication = {'class':'SimpleStrategy','replication_factor':'1'};
USE tweetapp;
CREATE ColumnFamily IF NOT EXISTS tweetapp.user_details(firstname text,lastname text,username text,loginId text unique,password text,contactNo bigint,primary key(username));
