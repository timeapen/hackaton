CREATE TABLE INDICATOR_SETTINGS (
     indicator_name   varchar (64) NOT NULL,
     indicator_value  varchar (64) NOT NULL
);

INSERT INTO INDICATOR_SETTINGS (indicator_name, indicator_value) values ('Corruption','Good');
INSERT INTO INDICATOR_SETTINGS (indicator_name, indicator_value) values ('Environment','Good');
INSERT INTO INDICATOR_SETTINGS (indicator_name, indicator_value) values ('Conflict','Good');
INSERT INTO INDICATOR_SETTINGS (indicator_name, indicator_value) values ('BusinessFreedom','VeryGood');
INSERT INTO INDICATOR_SETTINGS (indicator_name, indicator_value) values ('ReputationRisk','Excellent');