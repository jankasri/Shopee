CREATE TABLE forex (
	id bigint NOT NULL PRIMARY KEY,
	f_from varchar(10) NOT NULL,
	f_to varchar(10) NOT NULL,
	created_date timestamp,
	created_by varchar(10)
)

CREATE TABLE rate_daily (
	id bigint NOT NULL PRIMARY KEY,
	forex_id bigint NOT NULL,
	f_rate numeric(15,5), 
	f_date timestamp,
	CONSTRAINT fk_rate_forex FOREIGN KEY (forex_id) REFERENCES forex (id)
)



CREATE SEQUENCE forex_id_seq
CREATE SEQUENCE rate_id_seq