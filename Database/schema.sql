create table properties
(
    PropertyId  BIGSERIAL PRIMARY KEY,
    Application CHARACTER VARYING(100),
    Profile     CHARACTER varying(100),
    Label       CHARACTER varying(100),
    Key         CHARACTER VARYING(100) NOT NULL,
    Value       text                   NOT NULL,
    Description CHARACTER VARYING(1000),
    CreatedDate TIMESTAMP              NOT NULL DEFAULT NOW(),
    UpdatedDate TIMESTAMP                       DEFAULT NOW(),
    UNIQUE (Key, Application, Profile)
);
