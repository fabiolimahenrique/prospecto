CREATE TABLE oportunidade (
    id integer NOT NULL,
    nome_prospecto character varying(80) NOT NULL,
    descricao character varying(200) NOT NULL,
    tipo_oferta character varying(200) NOT NULL,
    ano character varying(4) NOT NULL,
    etapa character varying(200) NOT NULL,
    empresa character varying(200) NOT NULL,
    setor character varying(200) NOT NULL,
    valor numeric(19,2)
);



CREATE SEQUENCE oportunidade_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
    
ALTER TABLE ONLY oportunidade ALTER COLUMN id SET DEFAULT nextval('oportunidade_id_seq'::regclass);


ALTER TABLE ONLY oportunidade
    ADD CONSTRAINT oportunidade_pkey PRIMARY KEY (id); 