CREATE SCHEMA iDefine;

use idefine;

CREATE TABLE idefine_master_table( 
keyword VARCHAR(50) NOT NULL , 
definition TEXT(65535) NOT NULL , 
related_keys TEXT(65535) NOT NULL , 
PRIMARY KEY (keyword)) ENGINE = MyISAM

