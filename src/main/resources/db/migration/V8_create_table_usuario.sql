CREATE TABLE usuario_role(
    'id' BIGINT NOT NULL AUTO_INCREMENT,
     'usuario_id' BIGINT NOT NULL,
     'role_id' BIGINT NOT NULL,
    PRIMARY KEY ('id'),
    FOREIGN KEy ('usuario_id') REFERENCES 'usuario'('id')
    FOREIGN KEy ('role_id') REFERENCES 'role'('id')
);