-- 
-- TABLE: sobrante 
--

CREATE TABLE sobrante(
    id_sobrante    BIGINT         AUTO_INCREMENT,
    rotulo         VARCHAR(20),
    descripcion    TEXT           NOT NULL,
    responsable    BIGINT         NOT NULL,
    local          BIGINT         NOT NULL,
    estado         BIGINT         NOT NULL,
    PRIMARY KEY (id_sobrante)
)ENGINE=INNODB
;



-- 
-- TABLE: sobrante 
--

ALTER TABLE sobrante ADD CONSTRAINT Refresponsable31 
    FOREIGN KEY (responsable)
    REFERENCES responsable(id_responsable)
;

ALTER TABLE sobrante ADD CONSTRAINT Reflocal32 
    FOREIGN KEY (local)
    REFERENCES local(id_local)
;

ALTER TABLE sobrante ADD CONSTRAINT Refestado33 
    FOREIGN KEY (estado)
    REFERENCES estado(id_estado)
;