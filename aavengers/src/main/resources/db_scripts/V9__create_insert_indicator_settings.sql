CREATE TABLE INDICATOR_SETTINGS (
     indicator_name   varchar (64) NOT NULL,
     indicator_value  varchar (64) NOT NULL
);

INSERT INTO INDICATOR_SETTINGS (indicator_name, indicator_value) values ('Corruption','Poor');
INSERT INTO INDICATOR_SETTINGS (indicator_name, indicator_value) values ('Environment','Fair ');
INSERT INTO INDICATOR_SETTINGS (indicator_name, indicator_value) values ('Conflict','Good ');
INSERT INTO INDICATOR_SETTINGS (indicator_name, indicator_value) values ('Freedom','VeryGood');
INSERT INTO INDICATOR_SETTINGS (indicator_name, indicator_value) values ('ReputationRisk','Excellent ');